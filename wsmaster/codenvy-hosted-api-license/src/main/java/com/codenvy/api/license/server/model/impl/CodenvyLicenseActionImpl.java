/*
 *  [2012] - [2016] Codenvy, S.A.
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
package com.codenvy.api.license.server.model.impl;

import com.codenvy.api.license.model.CodenvyLicenseAction;
import com.codenvy.api.license.model.Constants;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Data object of {@link CodenvyLicenseAction}.
 *
 * @author Anatolii Bazko
 */
@Entity(name = "License")
@NamedQueries(
        {
                @NamedQuery(name = "License.getByTypeAndAction",
                            query = "SELECT l " +
                                    "FROM License l " +
                                    "WHERE :type = l.licenseType AND :action = l.actionType"),
                @NamedQuery(name = "License.getByType",
                            query = "SELECT l " +
                                    "FROM License l " +
                                    "WHERE :type = l.licenseType")
        }
)
@Table(name = "license")
public class CodenvyLicenseActionImpl implements CodenvyLicenseAction {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "license_type")
    private Constants.License licenseType;

    @Column(name = "license_qualifier")
    private String licenseQualifier;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_type")
    private Constants.Action actionType;

    @Column(name = "action_timestamp")
    private long actionTimestamp;

    @ElementCollection
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    @CollectionTable(name = "license_attributes", joinColumns = @JoinColumn(name = "id"))
    private Map<String, String> attributes;

    public CodenvyLicenseActionImpl() { }

    public CodenvyLicenseActionImpl(Constants.License licenseType,
                                    Constants.Action actionType,
                                    long actionTimestamp,
                                    String licenseQualifier,
                                    Map<String, String> attributes) {
        this.licenseType = licenseType;
        this.licenseQualifier = licenseQualifier;
        this.actionType = actionType;
        this.actionTimestamp = actionTimestamp;
        this.attributes = attributes == null ? new HashMap<>() : new HashMap<>(attributes);
    }

    @Override
    public Constants.License getLicenseType() {
        return licenseType;
    }

    @Override
    public String getLicenseQualifier() {
        return licenseQualifier;
    }

    @Override
    public Constants.Action getActionType() {
        return actionType;
    }

    @Override
    public long getActionTimestamp() {
        return actionTimestamp;
    }

    @Override
    public Map<String, String> getAttributes() {
        return attributes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CodenvyLicenseActionImpl)) {
            return false;
        }
        final CodenvyLicenseActionImpl that = (CodenvyLicenseActionImpl)obj;
        return Objects.equals(licenseType, that.licenseType)
               && Objects.equals(licenseQualifier, that.licenseQualifier)
               && Objects.equals(actionType, that.actionType)
               && Objects.equals(actionTimestamp, that.actionTimestamp)
               && getAttributes().equals(that.getAttributes());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(licenseType);
        hash = 31 * hash + Objects.hashCode(licenseQualifier);
        hash = 31 * hash + Objects.hashCode(actionType);
        hash = 31 * hash + Objects.hashCode(actionTimestamp);
        hash = 31 * hash + getAttributes().hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "CodenvyLicenseActionImpl{" +
               "licenseType=" + licenseType +
               ", licenseQualifier='" + licenseQualifier + '\'' +
               ", actionType=" + actionType +
               ", actionTimestamp=" + actionTimestamp +
               ", attributes=" + attributes +
               '}';
    }
}
