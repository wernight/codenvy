/*
 *
 * CODENVY CONFIDENTIAL
 * ________________
 *
 * [2012] - [2013] Codenvy, S.A.
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

package com.codenvy.analytics;

import com.codenvy.analytics.services.AnalyticsService;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

/** @author <a href="mailto:abazko@codenvy.com">Anatoliy Bazko</a> */
public class AnalyticsApplication extends Application {
    private final Set<Class<?>> classes;
    private final Set<Object> singletons;

    /** {@link AnalyticsApplication} constructor. */
    public AnalyticsApplication() {
        singletons = new HashSet<Object>();
        classes = new HashSet<Class<?>>();
        classes.add(AnalyticsService.class);
    }

    /** {@inheritDoc} */
    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
