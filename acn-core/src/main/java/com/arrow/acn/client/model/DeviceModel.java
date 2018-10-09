/*******************************************************************************
 * Copyright (c) 2018 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acn.client.model;

import java.util.Map;

import com.arrow.acs.client.model.AuditableDocumentModelAbstract;

public class DeviceModel extends AuditableDocumentModelAbstract<DeviceModel> {
    private static final long serialVersionUID = -8361035771470871050L;

    private String uid;
    private String name;
    private String type;
    private String userHid;
    private String gatewayHid;
    private boolean enabled;
    private String createdDate;
    private Map<String, String> info;
    private Map<String, String> properties;
    private String softwareName;
    private String softwareVersion;
    private String nodeHid;
    private String softwareReleaseHid;
    private String softwareReleaseName;
    private String softwareReleaseVersion;

    @Override
    protected DeviceModel self() {
        return this;
    }

    public DeviceModel withUid(String uid) {
        setUid(uid);
        return this;
    }

    public DeviceModel withName(String name) {
        setName(name);
        return this;
    }

    public DeviceModel withType(String type) {
        setType(type);
        return this;
    }

    public DeviceModel withUserHid(String userHid) {
        setUserHid(userHid);
        return this;
    }

    public DeviceModel withGatewayHid(String gatewayHid) {
        setGatewayHid(gatewayHid);
        return this;
    }

    public DeviceModel withEnabled(boolean enabled) {
        setEnabled(enabled);
        return this;
    }

    public DeviceModel withCreatedDate(String createdDate) {
        setCreatedDate(createdDate);
        return this;
    }

    public DeviceModel withInfo(Map<String, String> info) {
        setInfo(info);
        return this;
    }

    public DeviceModel withProperties(Map<String, String> properties) {
        setProperties(properties);
        return this;
    }

    public DeviceModel withNodeHid(String nodeHid) {
        setNodeHid(nodeHid);
        return this;
    }

    public DeviceModel withSoftwareReleaseHid(String softwareReleaseHid) {
        setSoftwareReleaseHid(softwareReleaseHid);
        return this;
    }

    public DeviceModel withSoftwareReleaseName(String softwareReleaseName) {
        setSoftwareReleaseName(softwareReleaseName);
        return this;
    }

    public DeviceModel withSoftwareReleaseVersion(String softwareReleaseVersion) {
        setSoftwareReleaseVersion(softwareReleaseVersion);
        return this;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }

    public String getUserHid() {
        return userHid;
    }

    public void setUserHid(String userHid) {
        this.userHid = userHid;
    }

    public String getGatewayHid() {
        return gatewayHid;
    }

    public void setGatewayHid(String gatewayHid) {
        this.gatewayHid = gatewayHid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getNodeHid() {
        return nodeHid;
    }

    public void setNodeHid(String nodeHid) {
        this.nodeHid = nodeHid;
    }

    public String getSoftwareReleaseHid() {
        return softwareReleaseHid;
    }

    public void setSoftwareReleaseHid(String softwareReleaseHid) {
        this.softwareReleaseHid = softwareReleaseHid;
    }

    public String getSoftwareReleaseName() {
        return softwareReleaseName;
    }

    public void setSoftwareReleaseName(String softwareReleaseName) {
        this.softwareReleaseName = softwareReleaseName;
    }

    public String getSoftwareReleaseVersion() {
        return softwareReleaseVersion;
    }

    public void setSoftwareReleaseVersion(String softwareReleaseVersion) {
        this.softwareReleaseVersion = softwareReleaseVersion;
    }
}
