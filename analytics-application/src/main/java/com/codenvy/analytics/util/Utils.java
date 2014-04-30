/*
 *
 * CODENVY CONFIDENTIAL
 * ________________
 *
 * [2012] - [2014] Codenvy, S.A.
 * All Rights Reserved.
 * NOTICE: All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any. The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.analytics.util;

import com.codenvy.analytics.datamodel.ListValueData;
import com.codenvy.analytics.datamodel.MapValueData;
import com.codenvy.analytics.datamodel.ValueData;
import com.codenvy.analytics.datamodel.ValueDataUtil;
import com.codenvy.analytics.metrics.*;
import com.codenvy.analytics.metrics.accounts.AbstractAccountMetric;
import com.codenvy.analytics.metrics.accounts.AccountWorkspacesList;
import com.codenvy.api.analytics.shared.dto.MetricInfoDTO;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** @author Anatoliy Bazko */
public class Utils {

    private static final Pattern ADMIN_ROLE_EMAIL_PATTERN = Pattern.compile("@codenvy[.]com$");

    public static Map<String, String> extractParams(UriInfo info,
                                                    String page,
                                                    String perPage,
                                                    SecurityContext securityContext) {

        MultivaluedMap<String, String> parameters = info.getQueryParameters();
        Map<String, String> context = new HashMap<>(parameters.size());

        putQueryParameters(parameters, context);
        putPaginationParameters(page, perPage, context);
        putCurrentUserAsFilter(context, securityContext);
        putAvailableWorkspacesAsFilter(context, securityContext);
        putDefaultValueIfAbsent(context, Parameters.FROM_DATE);
        putDefaultValueIfAbsent(context, Parameters.TO_DATE);

        return context;
    }

    public static Map<String, String> extractParams(UriInfo info, SecurityContext securityContext) {
        return extractParams(info, null, null, securityContext);
    }

    public static boolean isRolesAllowed(MetricInfoDTO metricInfoDTO, SecurityContext securityContext) {
        Principal principal = securityContext.getUserPrincipal();
        List<String> rolesAllowed = metricInfoDTO.getRolesAllowed();

        if (principal == null || rolesAllowed.isEmpty()) {
            return false;
        }

        if (isSystemUser(securityContext) || rolesAllowed.contains("any")) {
            return true;
        }

        for (String role : rolesAllowed) {
            if (securityContext.isUserInRole(role)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isSystemUser(String email) {
        Matcher matcher = ADMIN_ROLE_EMAIL_PATTERN.matcher(email);
        return matcher.find();
    }

    public static boolean isSystemUser(SecurityContext securityContext) {
        Principal userPrincipal = securityContext.getUserPrincipal();
        if (userPrincipal == null) {
            return false;
        }

        Matcher matcher = ADMIN_ROLE_EMAIL_PATTERN.matcher(userPrincipal.getName());
        return matcher.find()
               || securityContext.isUserInRole("system/admin")
               || securityContext.isUserInRole("system/manager");
    }

    private static void putDefaultValueIfAbsent(Map<String, String> context, Parameters param) {
        if (!context.containsKey(param.toString())) {
            context.put(param.toString(), param.getDefaultValue());
        }
    }


    private static void putCurrentUserAsFilter(Map<String, String> context, SecurityContext securityContext) {
        if (!isSystemUser(securityContext)) {
            context.put("USER", securityContext.getUserPrincipal().getName());
        }
    }

    private static void putAvailableWorkspacesAsFilter(Map<String, String> context, SecurityContext securityContext) {
        if (!isSystemUser(securityContext)) {
            Set<String> workspaces = getAvailableWorkspacesForCurrentUser(context);
            if (context.containsKey("WS")) {
                if (!workspaces.contains(context.get("WS"))) {
                    throw new IllegalStateException("Security violation. Probably user hasn't access to data");
                }
            } else {
                context.put("WS", getFilterAsString(workspaces));
            }
        }
    }

    private static String getFilterAsString(Set<String> workspaces) {
        StringBuilder result = new StringBuilder();
        for (String workspace : workspaces) {
            if (result.length() > 0) {
                result.append(ReadBasedMetric.SEPARATOR);
            }

            result.append(workspace);
        }

        return result.toString();
    }

    private static Set<String> getAvailableWorkspacesForCurrentUser(Map<String, String> context) {
        try {
            String role = context.get(MetricFilter.DATA_UNIVERSE.toString());

            Metric accountsMetric = MetricFactory.getMetric(MetricType.ACCOUNTS_LIST);
            ListValueData accounts = ValueDataUtil.getAsList(accountsMetric, Context.EMPTY);

            if (isAccountsRoleExists(role)) {
                accounts = doFilterAccountsByRole(role, accounts);
            }

            return getWorkspacesByRole(role, accounts);
        } catch (IOException e) {
            throw new RuntimeException("Can not get workspaces for current user", e);
        }

    }

    private static Set<String> getWorkspacesByRole(String role, ListValueData accounts) throws IOException {
        Set<String> result = new HashSet<>();
        Metric accountWorkspaces = MetricFactory.getMetric(MetricType.ACCOUNT_WORKSPACES_LIST);

        for (ValueData account : accounts.getAll()) {
            Map<String, ValueData> accountData = ((MapValueData)account).getAll();

            Context.Builder builder = new Context.Builder();
            builder.put(MetricFilter.ACCOUNT_ID, accountData.get(AccountWorkspacesList.ACCOUNT_ID).getAsString());

            ListValueData workspaces = ValueDataUtil.getAsList(accountWorkspaces, builder.build());
            if (isWorkspacesRoleExists(role)) {
                workspaces = doWorkspaceFilterByWorkspaceRole(role, workspaces);
            }

            for (ValueData workspace : workspaces.getAll()) {
                Map<String, ValueData> workspaceData = ((MapValueData)workspace).getAll();
                result.add(workspaceData.get(AccountWorkspacesList.WORKSPACE_NAME).getAsString());
            }
        }

        return result;
    }

    private static boolean isWorkspacesRoleExists(String role) {
        return role != null && (role.equalsIgnoreCase(AbstractAccountMetric.ROLE_WORKSPACE_DEVELOPER)
                                || role.equalsIgnoreCase(AbstractAccountMetric.ROLE_WORKSPACE_ADMIN));
    }

    private static boolean isAccountsRoleExists(String role) {
        return role != null && (role.equalsIgnoreCase(AbstractAccountMetric.ROLE_ACCOUNT_MEMBER)
                                || role.equalsIgnoreCase(AbstractAccountMetric.ROLE_ACCOUNT_OWNER));
    }

    private static ListValueData doWorkspaceFilterByWorkspaceRole(String role, ListValueData workspaces) {
        List<ValueData> list2Return = new ArrayList<>();
        for (ValueData workspace : workspaces.getAll()) {
            Map<String, ValueData> map = ((MapValueData)workspace).getAll();

            String roles = map.get(AbstractAccountMetric.WORKSPACE_ROLES).getAsString();
            if (roles.contains(role)) {
                list2Return.add(workspace);
            }
        }
        return new ListValueData(list2Return);
    }

    private static ListValueData doFilterAccountsByRole(String role, ListValueData accounts) {
        List<ValueData> list2Return = new ArrayList<>();
        for (ValueData account : accounts.getAll()) {
            Map<String, ValueData> map = ((MapValueData)account).getAll();

            String roles = map.get(AbstractAccountMetric.ACCOUNT_ROLES).getAsString();
            if (roles.contains(role)) {
                list2Return.add(account);
            }
        }
        return new ListValueData(list2Return);
    }


    private static void putQueryParameters(MultivaluedMap<String, String> parameters, Map<String, String> context) {
        for (String key : parameters.keySet()) {
            context.put(key.toUpperCase(), parameters.getFirst(key));
        }
    }

    private static void putPaginationParameters(String page, String perPage, Map<String, String> context) {
        if (page != null && perPage != null) {
            context.put("PAGE", page);
            context.put("PER_PAGE", perPage);
        }
    }
}
