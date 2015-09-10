/*
 * CODENVY CONFIDENTIAL
 * __________________
 *
 *  [2012] - [2015] Codenvy, S.A.
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
package com.codenvy.analytics.metrics;

/**
 * Predefined metrics.
 *
 * @author Anatoliy Bazko
 */
public enum MetricType {
    WORKSPACES,
    WORKSPACES_PROFILES,
    WORKSPACES_PROFILES_LIST,
    CREATED_WORKSPACES,
    CREATED_WORKSPACES_SET,
    DESTROYED_WORKSPACES,
    TOTAL_WORKSPACES,
    ACTIVE_WORKSPACES,
    NON_ACTIVE_WORKSPACES,
    NEW_ACTIVE_WORKSPACES,
    RETURNING_ACTIVE_WORKSPACES,
    ACTIVE_WORKSPACES_SET,
    WORKSPACES_STATISTICS_LIST,
    WORKSPACES_STATISTICS_LIST_PRECOMPUTED,
    WORKSPACES_STATISTICS,
    WORKSPACES_STATISTICS_PRECOMPUTED,

    CREATED_USERS,
    CREATED_USERS_SET,
    CREATED_USERS_LIST,
    CREATED_USERS_FROM_AUTH,
    REMOVED_USERS,
    TOTAL_USERS,
    ACTIVE_USERS,
    ACTIVE_USERS_FROM_BEGINNING,
    ACTIVE_USERS_SET,
    NON_ACTIVE_USERS,
    RETURNING_ACTIVE_USERS,
    USERS_GB_HOURS_LIST,

    USERS_LOGGED_IN_TYPES,
    USERS_LOGGED_IN_WITH_GITHUB,
    USERS_LOGGED_IN_WITH_GOOGLE,
    USERS_LOGGED_IN_WITH_FORM,
    USERS_LOGGED_IN_WITH_SYSLDAP,
    USERS_LOGGED_IN_TOTAL,
    USERS_LOGGED_IN_WITH_GITHUB_PERCENT,
    USERS_LOGGED_IN_WITH_GOOGLE_PERCENT,
    USERS_LOGGED_IN_WITH_FORM_PERCENT,
    USERS_LOGGED_IN_WITH_SYSLDAP_PERCENT,

    USER_INVITE,
    USERS_ACCEPTED_INVITES,
    USERS_ACCEPTED_INVITES_PERCENT,
    USERS_ADDED_TO_WORKSPACES,
    USERS_ADDED_TO_WORKSPACES_USING_INVITATION,
    COMPLETED_PROFILES,

    // <------- TESTED

    NEW_ACTIVE_USERS,
    NEW_USERS_USAGE_TIME_GREATER_1_MIN,
    NEW_USERS_USAGE_TIME_GREATER_60_MIN,
    NEW_USERS_GB_HOURS,
    NEW_USERS_BUILDS,
    NEW_USERS_RUNS,
    NEW_USERS_DEPLOYS,
    NEW_USERS_SENT_INVITES,
    NEW_USERS_ON_PREM_DOWNLOADS,
    NEW_USERS_ON_PREM_TRIAL_LICENSES,

    PROJECTS,
    PROJECTS_LIST,
    PROJECTS_STATISTICS_LIST,
    PROJECTS_STATISTICS,
    PROJECTS_STATISTICS_LIST_PRECOMPUTED,
    PROJECTS_STATISTICS_PRECOMPUTED,
    PROJECTS_ACTIVE,
    PROJECTS_ACTIVE_SET,
    CREATED_PROJECTS,
    DESTROYED_PROJECTS,
    TOTAL_PROJECTS,

    PROJECT_PAASES,

    PROJECT_TYPES,
    PROJECT_TYPE_JAR,
    PROJECT_TYPE_WAR,
    PROJECT_TYPE_JSP,
    PROJECT_TYPE_SPRING,
    PROJECT_TYPE_PHP,
    PROJECT_TYPE_GO,
    PROJECT_TYPE_ANT,
    PROJECT_TYPE_CPP,
    PROJECT_TYPE_DJANGO,
    PROJECT_TYPE_PYTHON,
    PROJECT_TYPE_JAVASCRIPT,
    PROJECT_TYPE_RUBY,
    PROJECT_TYPE_MMP,
    PROJECT_TYPE_NODEJS,
    PROJECT_TYPE_ANDROID,
    PROJECT_TYPE_OTHERS,

    USAGE,
    SESSIONS,
    SESSIONS_FROM_LOGINS,
    COLLABORATIVE_SESSIONS_STARTED,
    NON_FACTORIES_PRODUCT_USAGE_SESSIONS,
    PRODUCT_USAGE_SESSIONS,
    PRODUCT_USAGE_SESSIONS_LIST,
    PRODUCT_USAGE_SESSIONS_FAILS,
    PRODUCT_USAGE_SESSIONS_FAILS_LIST,
    PRODUCT_USAGE_CONDITION_BELOW_120_MIN,
    PRODUCT_USAGE_CONDITION_BETWEEN_120_AND_300_MIN,
    PRODUCT_USAGE_CONDITION_ABOVE_300_MIN,
    TIMELINE_PRODUCT_USAGE_CONDITION_BELOW_120_MIN,
    TIMELINE_PRODUCT_USAGE_CONDITION_BETWEEN_120_AND_300_MIN,
    TIMELINE_PRODUCT_USAGE_CONDITION_ABOVE_300_MIN,
    PRODUCT_USAGE_TIME_BELOW_1_MIN,
    PRODUCT_USAGE_TIME_BETWEEN_1_AND_10_MIN,
    PRODUCT_USAGE_TIME_BETWEEN_10_AND_60_MIN,
    PRODUCT_USAGE_TIME_ABOVE_60_MIN,
    PRODUCT_USAGE_TIME_TOTAL,
    PRODUCT_USAGE_SESSIONS_BELOW_1_MIN,
    PRODUCT_USAGE_SESSIONS_BETWEEN_1_AND_10_MIN,
    PRODUCT_USAGE_SESSIONS_BETWEEN_10_AND_60_MIN,
    PRODUCT_USAGE_SESSIONS_ABOVE_60_MIN,
    PRODUCT_USAGE_USERS_TOTAL,
    PRODUCT_USAGE_USERS_BELOW_10_MIN,
    PRODUCT_USAGE_USERS_BETWEEN_10_AND_60_MIN,
    PRODUCT_USAGE_USERS_BETWEEN_60_AND_300_MIN,
    PRODUCT_USAGE_USERS_ABOVE_300_MIN,
    PRODUCT_USERS_TIME,
    PRODUCT_DOMAINS_TIME,
    PRODUCT_COMPANIES_TIME,

    RUNS_TIME,
    BUILDS_TIME,
    DEBUGS_TIME,
    TIME_IN_RUN_QUEUE,
    TIME_IN_BUILD_QUEUE,
    DOCKER_CONFIGURATION_TIME,

    RUNS,
    RUNS_WITH_TIMEOUT,
    RUNS_WITH_ALWAYS_ON,
    RUNS_FINISHED,
    RUNS_FINISHED_BY_USER,
    RUNS_FINISHED_BY_TIMEOUT,
    RUNS_MEMORY_USAGE,
    RUNS_GIGABYTE_RAM_HOURS,
    RUN_QUEUE_TERMINATIONS,

    BUILDS,
    BUILDS_WITH_TIMEOUT,
    BUILDS_WITH_ALWAYS_ON,
    BUILDS_FINISHED,
    BUILDS_FINISHED_NORMALLY,
    BUILDS_FINISHED_BY_TIMEOUT,
    BUILDS_GIGABYTE_RAM_HOURS,
    BUILD_QUEUE_TERMINATIONS,

    DEBUGS,
    DEBUGS_WITH_TIMEOUT,
    DEBUGS_WITH_ALWAYS_ON,
    DEBUGS_FINISHED,
    DEBUGS_FINISHED_BY_USER,
    DEBUGS_FINISHED_BY_TIMEOUT,
    DEBUGS_MEMORY_USAGE,
    DEBUGS_GIGABYTE_RAM_HOURS,

    EDITS,
    EDITS_TIME,
    EDITS_GIGABYTE_RAM_HOURS,

    TASKS,
    TASKS_LIST,
    TASKS_LAUNCHED,
    TASKS_STOPPED,
    TASKS_TIME,
    TASKS_GIGABYTE_RAM_HOURS,
    TASKS_LAUNCHED_WITH_TIMEOUT,
    TASKS_LAUNCHED_WITH_ALWAYS_ON,
    TASKS_STOPPED_NORMALLY,
    TASKS_STOPPED_BY_TIMEOUT,

    DEPLOYS,
    DEPLOYS_TO_PAAS,
    CODE_REFACTORINGS,
    CODE_COMPLETIONS,
    CODE_COMPLETIONS_BASED_ON_EVENT,
    CODE_COMPLETIONS_BASED_ON_IDE_USAGES,

    TOTAL_FACTORIES,
    FACTORIES,
    ACTIVE_FACTORIES_SET,
    CREATED_FACTORIES,
    CREATED_UNIQUE_FACTORIES,
    CREATED_FACTORIES_SET,
    CREATED_FACTORIES_LIST,
    FACTORY_USERS_LIST,
    FACTORY_USERS,
    FACTORY_STATISTICS_LIST,
    FACTORY_STATISTICS_LIST_PRECOMPUTED,
    FACTORY_STATISTICS,
    FACTORY_STATISTICS_PRECOMPUTED,
    PRODUCT_USAGE_FACTORY_SESSIONS_LIST,
    PRODUCT_USAGE_FACTORY_SESSIONS,
    WORKSPACES_WHERE_USERS_HAVE_SEVERAL_FACTORY_SESSIONS,
    CREATED_USERS_FROM_FACTORY,
    TEMPORARY_WORKSPACES_CREATED,
    REFERRERS_COUNT_TO_SPECIFIC_FACTORY,
    FACTORIES_ACCEPTED_LIST,
    FACTORIES_ACCEPTED,
    FACTORY_USED,
    FACTORIES_BUILT,
    FACTORIES_RUN,
    FACTORIES_IMPORTED,
    FACTORY_SESSIONS,
    ANONYMOUS_FACTORY_SESSIONS,
    AUTHENTICATED_FACTORY_SESSIONS,
    ABANDONED_FACTORY_SESSIONS,
    CONVERTED_FACTORY_SESSIONS,
    FACTORY_SESSIONS_WITH_BUILD,
    FACTORY_SESSIONS_WITH_RUN,
    FACTORY_SESSIONS_WITH_DEPLOY,
    FACTORY_SESSIONS_WITH_BUILD_PERCENT,
    FACTORY_SESSIONS_WITH_RUN_PERCENT,
    FACTORY_SESSIONS_WITH_DEPLOY_PERCENT,
    FACTORY_SESSIONS_BELOW_10_MIN,
    FACTORY_SESSIONS_ABOVE_10_MIN,
    FACTORY_PRODUCT_USAGE_TIME_TOTAL,

    USERS_PROFILES,
    USERS_PROFILES_LIST,
    DUPLICATED_USERS_SET,
    USERS_STATISTICS,
    USERS_STATISTICS_PRECOMPUTED,
    USERS_STATISTICS_LIST,
    USERS_STATISTICS_LIST_PRECOMPUTED,
    USERS_ACTIVITY_LIST,
    USERS_ACTIVITY,

    USAGE_TIME_BY_WORKSPACES,
    USAGE_TIME_BY_WORKSPACES_LIST,
    USAGE_TIME_BY_USERS,
    USAGE_TIME_BY_USERS_LIST,

    USERS_WHO_CREATED_PROJECT,
    USERS_WHO_BUILT,
    USERS_WHO_DEPLOYED,
    USERS_WHO_DEPLOYED_TO_PAAS,
    USERS_WHO_INVITED,

    TOP_FACTORY_SESSIONS,
    TOP_FACTORIES,
    TOP_REFERRERS,
    TOP_USERS,
    TOP_DOMAINS,
    TOP_COMPANIES,

    IM_DOWNLOADS_LIST,
    IM_DOWNLOADS,
    IM_DOWNLOADS_CODENVY,
    IM_DOWNLOADS_INSTALL_SCRIPT,
    IM_ON_PREM_ISSUED_LIST,
    IM_ON_PREM_ISSUED,
    IM_INSTALLS_STARTED,
    IM_INSTALLS_STARTED_LIST,
    IM_INSTALLS_FINISHED_SUCCESSFULLY,
    IM_INSTALLS_FINISHED_SUCCESSFULLY_LIST,
    IM_INSTALLS_FINISHED_UNSUCCESSFULLY,
    IM_INSTALLS_FINISHED_UNSUCCESSFULLY_LIST,

    IDE_USAGES,
    ZERO,

    USERS_ROLES_LIST,
    USERS_ROLES,
    CURRENT_USER_ACCOUNTS_LIST,
    CURRENT_USER_WORKSPACES_LIST,
    ACCOUNT_SUBSCRIPTIONS_LIST,
    ACCOUNT_FACTORIES_LIST,
    ACCOUNT_USERS_STATISTICS_LIST,
    ACCOUNTS_LIST,
    ACCOUNT_PROJECTS,

    USERS_ACCOUNTS,
    USERS_ACCOUNTS_LIST,
    USERS_ACCOUNTS_OWNER_LIST,

    TIME_TRACKING_USERS,
    TIME_TRACKING_WORKSPACES,
    TIME_TRACKING_SESSIONS,

    PROJECT_CONFIGURATION_ACTION,
    CLOSE_PROJECT_ACTION,
    DELETE_ITEM_ACTION,
    EXPAND_EDITOR_ACTION,
    FIND_ACTION_ACTION,
    FORMATTER_ACTION,
    IMPORT_PROJECT_FROM_LOCATION_ACTION,
    NAVIGATE_TO_FILE_ACTION,
    NEW_PROJECT_ACTION,
    OPEN_PROJECT_ACTION,
    REDIRECT_TO_FEEDBACK_ACTION,
    REDIRECT_TO_FORUMS_ACTION,
    REDIRECT_TO_HELP_ACTION,
    REDO_ACTION,
    RENAME_ITEM_ACTION,
    SAVE_ACTION,
    SAVE_ALL_ACTION,
    SHOW_ABOUT_ACTION,
    SHOW_PREFERENCES_ACTION,
    UNDO_ACTION,
    UPLOAD_FILE_ACTION,
    NEW_CSS_FILE_ACTION,
    NEW_LESS_FILE_ACTION,
    NEW_HTML_FILE_ACTION,
    NEW_JAVA_SCRIPT_FILE_ACTION,
    BUILD_ACTION,
    CLEAR_BUILDER_CONSOLE_ACTION,
    CLEAR_RUNNER_CONSOLE_ACTION,
    RUN_ACTION,
    SORT_BY_STATUS_ACTION,
    DEFAULT_NEW_RESOURCE_ACTION,
    NEW_FILE_ACTION,
    NEW_FOLDER_ACTION,
    NEW_XML_FILE_ACTION,
    SHUTDOWN_ACTION,
    ADD_TO_INDEX_ACTION,
    COMMIT_ACTION,
    DELETE_REPOSITORY_ACTION,
    FETCH_ACTION,
    HISTORY_ACTION,
    INIT_REPOSITORY_ACTION,
    PULL_ACTION,
    PUSH_ACTION,
    REMOVE_FROM_INDEX_ACTION,
    RESET_FILES_ACTION,
    RESET_TO_COMMIT_ACTION,
    SHOW_BRANCHES_ACTION,
    SHOW_GIT_URL_ACTION,
    SHOW_MERGE_ACTION,
    SHOW_REMOTE_ACTION,
    SHOW_STATUS_ACTION,
    MANAGE_DATASOURCES_ACTION,
    NEW_DATASOURCE_WIZARD_ACTION,
    NEW_SQL_FILE_ACTION,
    BOWER_INSTALL_ACTION,
    EXPORT_CONFIG_ACTION,
    IMPORT_FROM_CONFIG_ACTION,
    PERMISSIONS_ACTION,
    CUSTOM_GRUNT_RUN_ACTION,
    NPM_INSTALL_ACTION,
    CUSTOM_BUILD_ACTION,
    DEBUG_ACTION,
    NEW_JAVA_SOURCE_FILE_ACTION,
    NEW_PACKAGE_ACTION,
    UPDATE_DEPENDENCY_ACTION,
    SHOW_HIDDEN_FILES_ACTION,
    OPEN_SELECTED_FILE_ACTION,
    FIND_REPLACE_ACTION,
    BROWSE_TARGET_FOLDER_ACTION,
    CREATE_SUPPORT_TICKET_ACTION,
    REDIRECT_TO_ENGINEER_CHAT_CHANNEL_ACTION,
    SUBSCRIPTION_INDICATOR_ACTION,
    REDIRECT_LINK_ACTION,
    QUEUE_TYPE_INDICATOR_ACTION,
    MEMORY_INDICATOR_ACTION,
    PERMISSIONS_INDICATOR_ACTION,
    OPEN_WELCOME_PAGE_ACTION,
    SHARE_ACTION,
    REDIRECT_TO_DASHBOARD_ACTION,
    CREATE_MAVEN_MODULE_ACTION,
    QUICK_DOCUMENTATION_ACTION,
    OPEN_DECLARATION_ACTION,

    CREDIT_CARD_ADDED,
    CREDIT_CARD_REMOVED,
    CREDIT_CARD_CHARGE_SUCCESS,
    CREDIT_CARD_CHARGE_FAILED,
    ACCOUNT_LOCKED,
    ACCOUNT_LOCKED_LIST,
    ACCOUNT_UNLOCKED,
    ACCOUNT_UNLOCKED_LIST,
    SUBSCRIPTION_ADDED,
    SUBSCRIPTION_ADDED_LIST,
    SUBSCRIPTION_REMOVED,
    SUBSCRIPTION_RENEWED,
    SUBSCRIPTION_REMOVED_LIST,
    TRIAL_STARTED,
    TRIAL_EXPIRED,
    EMAIL_VALIDATION_SEND,
    SIGNUP_VALIDATION_EMAIL_SEND,
    SIGNUP_VALIDATION_EMAIL_SEND_SET,
    SIGNUP_VALIDATION_EMAIL_CONFIRMED,
    SIGNUP_VALIDATION_EMAIL_CONFIRMED_SET,
    SIGNUP_VALIDATION_EMAIL_NOT_CONFIRMED,
    SIGNUP_VALIDATION_EMAIL_NOT_CONFIRMED_SET
}
