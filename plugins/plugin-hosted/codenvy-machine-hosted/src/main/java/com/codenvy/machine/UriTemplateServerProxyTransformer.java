/*
 *  [2012] - [2017] Codenvy, S.A.
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.machine;

import com.google.common.base.Strings;

import org.eclipse.che.api.machine.server.model.impl.ServerImpl;
import org.eclipse.che.api.machine.server.model.impl.ServerPropertiesImpl;
import org.slf4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Modifies machine server attributes according to provided template of URI of the server.
 *
 * @author Alexander Garagatyi
 */
public abstract class UriTemplateServerProxyTransformer implements MachineServerProxyTransformer {
    private static final Logger LOG = getLogger(RemoteDockerNode.class);

    private final String serverUrlTemplate;
    private final String codenvyHost;
    private final String externalAddressProperty;

    /**
     * Template URI is used in {@link String#format(String, Object...)} with such arguments:
     * <ul>
     * <li>Template URI</li>
     * <li>Server reference</li>
     * <li>Server location hostname</li>
     * <li>Server location external port</li>
     * <li>Server path (without leading slash if present)</li>
     * </ul>
     * Template should satisfy that invocation. Not all arguments have to be used.<br>
     * Modified server components will be retrieved from URI created by this operation.<br>
     * To avoid changing of server use template:http://%2$s:%3$s/%4$s
     */
    public UriTemplateServerProxyTransformer(String serverUrlTemplate, String codenvyHost, String externalAddressProperty) {
        this.serverUrlTemplate = serverUrlTemplate;
        this.codenvyHost = codenvyHost;
        this.externalAddressProperty = externalAddressProperty;
    }

    @Override
    public ServerImpl transform(ServerImpl server) {
        final String serverAddress = server.getAddress();
        final int colonIndex = serverAddress.indexOf(':');
        final String serverHost = serverAddress.substring(0, colonIndex);
        final String serverPort = serverAddress.substring(colonIndex + 1);
        String serverPath = "";
        if (server.getProperties() != null && server.getProperties().getPath() != null) {
            serverPath = server.getProperties().getPath();
        }
        if (serverPath.startsWith("/")) {
            serverPath = serverPath.substring(1);
        }
        final String externalAddress;
        final String externalServerHost;
        final String internalServerHost;
        if (!Strings.isNullOrEmpty(externalAddressProperty) && !Strings.isNullOrEmpty(codenvyHost)) {
            externalAddress = externalAddressProperty;
            externalServerHost = codenvyHost;
            internalServerHost = codenvyHost;
        } else if (!Strings.isNullOrEmpty(codenvyHost)) {
            externalAddress = codenvyHost;
            externalServerHost = serverHost;
            internalServerHost = codenvyHost;
        } else {
            externalAddress = serverHost;
            externalServerHost = serverHost;
            internalServerHost = serverHost;
        }
        try {
            URI serverUriExternal = new URI(format(serverUrlTemplate,
                                                   server.getRef(),
                                                   externalServerHost,
                                                   serverPort,
                                                   serverPath,
                                                   externalAddress));
            URI serverUri = new URI(format(serverUrlTemplate,
                                           server.getRef(),
                                           internalServerHost,
                                           serverPort,
                                           serverPath,
                                           internalServerHost));

            String newExternalServerAddress = serverUriExternal.getHost() +
                                      (serverUriExternal.getPort() == -1 ? "" : ":" + serverUriExternal.getPort());
            String newServerAddress = serverUri.getHost() +
                                      (serverUri.getPort() == -1 ? "" : ":" + serverUri.getPort());

            return new ServerImpl(server.getRef(),
                                  serverUriExternal.getScheme(),
                                  newExternalServerAddress,
                                  serverUriExternal.toString(),
                                  new ServerPropertiesImpl(serverUriExternal.getPath(), newServerAddress, serverUri.toString()));
        } catch (URISyntaxException e) {
            LOG.error(format("Server uri created from template taken from configuration is invalid. Template:%s. Origin server:%s",
                             serverUrlTemplate,
                             server),
                      e);
            return server;
        }
    }
}
