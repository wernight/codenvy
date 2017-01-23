package com.codenvy.ldap.auth;

import com.codenvy.api.dao.authentication.*;
import com.codenvy.api.dao.authentication.SSHAPasswordEncryptor;
import com.codenvy.ldap.EmbeddedLdapServer;
import com.codenvy.ldap.LdapUserIdNormalizer;

import org.apache.directory.shared.ldap.entry.ServerEntry;
import org.ldaptive.auth.Authenticator;
import org.ldaptive.auth.EntryResolver;
import org.ldaptive.pool.PooledConnectionFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

/**
 * Tests authentication of type AD.
 *
 * @author Yevhenii Voevodin
 */
public class ADAuthenticationTest {

    private PasswordEncryptor encryptor = new SSHAPasswordEncryptor();
    private EmbeddedLdapServer server;

    @BeforeTest
    public void startServer() throws Exception {
        server = EmbeddedLdapServer.builder()
                                   .setPartitionId("codenvy")
                                   .setPartitionDn("dc=codenvy,dc=com")
                                   .useTmpWorkingDir()
                                   .setMaxSizeLimit(1000)
                                   .build();
        server.start();
    }

    @Test
    public void adAuthentication() throws Exception {
        // developers
        ServerEntry ouDevelopers = server.newEntry("ou", "developers");
        ouDevelopers.add("objectClass", "organizationalUnit");
        ouDevelopers.add("ou", "developers");
        server.addEntry(ouDevelopers);

        ServerEntry mike = server.newEntry("cn", "mike", ouDevelopers);
        mike.add("objectClass", "inetOrgPerson");
        mike.add("uid", "user1");
        mike.add("cn", "mike");
        mike.add("sn", "mike");
        mike.add("userPassword", encryptor.encrypt("mike".getBytes(StandardCharsets.UTF_8)));
        server.addEntry(mike);

        ServerEntry john = server.newEntry("cn", "john", ouDevelopers);
        john.add("objectClass", "inetOrgPerson");
        john.add("uid", "user2");
        john.add("cn", "john");
        john.add("sn", "john");
        john.add("userPassword", encryptor.encrypt("john".getBytes(StandardCharsets.UTF_8)));
        server.addEntry(john);

        // managers
        ServerEntry ouManagers = server.newEntry("ou", "managers");
        ouManagers.add("objectClass", "organizationalUnit");
        ouManagers.add("ou", "managers");
        server.addEntry(ouManagers);

        ServerEntry brad = server.newEntry("cn", "brad", ouManagers);
        brad.add("objectClass", "inetOrgPerson");
        brad.add("uid", "user3");
        brad.add("cn", "brad");
        brad.add("sn", "brad");
        brad.add("userPassword", encryptor.encrypt("brad".getBytes(StandardCharsets.UTF_8)));
        server.addEntry(brad);

        LdapAuthenticationHandler handler = createHandler();
        handler.authenticate("mike", "mike");
    }

    private LdapAuthenticationHandler createHandler() {
        PooledConnectionFactory connFactory = server.getConnectionFactory();
        EntryResolver entryResolver = new EntryResolverProvider(connFactory,
                                                                "ou=developers,dc=codenvy,dc=com",
                                                                null ,
                                                                "true").get();
        Authenticator authenticator = new AuthenticatorProvider(connFactory,
                                                                entryResolver,
                                                                "ou=developers,dc=codenvy,dc=com",
                                                                "AD",
                                                                "cn=%s,ou=developers,dc=codenvy,dc=com",
                                                                null,
                                                                null,
                                                                null,
                                                                "true").get();
        return new LdapAuthenticationHandler(authenticator, new LdapUserIdNormalizer("cn"));
    }

    @AfterMethod
    public void shutdown() {
        server.shutdown();
    }
}


