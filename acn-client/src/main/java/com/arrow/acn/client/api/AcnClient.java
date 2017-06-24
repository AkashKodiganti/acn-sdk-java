/*******************************************************************************
 * Copyright (c) 2017 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acn.client.api;

import org.apache.commons.lang3.Validate;

import com.arrow.acs.client.api.ApiConfig;

public final class AcnClient {
	private ApiConfig apiConfig;
	private final AccountApi accountApi;
	private final CoreEventApi coreEventApi;
	private final CoreUserApi coreUserApi;
	private final DeviceActionApi deviceActionApi;
	private final DeviceApi deviceApi;
	private final DeviceStateApi deviceStateApi;
	private final GatewayApi gatewayApi;
	private final NodeApi nodeApi;
	private final NodeTypeApi nodeTypeApi;
	private final SoftwareReleaseScheduleApi softwareReleaseScheduleApi;
	private final SoftwareReleaseTransApi softwareReleaseTransApi;
	private final TelemetryApi telemetryApi;

	public AcnClient(ApiConfig apiConfig) {
		Validate.notNull(apiConfig, "apiConfig is not set");
		this.apiConfig = apiConfig;
		accountApi = new AccountApi(apiConfig);
		coreEventApi = new CoreEventApi(apiConfig);
		coreUserApi = new CoreUserApi(apiConfig);
		deviceActionApi = new DeviceActionApi(apiConfig);
		deviceApi = new DeviceApi(apiConfig);
		deviceStateApi = new DeviceStateApi(apiConfig);
		gatewayApi = new GatewayApi(apiConfig);
		nodeApi = new NodeApi(apiConfig);
		nodeTypeApi = new NodeTypeApi(apiConfig);
		softwareReleaseScheduleApi = new SoftwareReleaseScheduleApi(apiConfig);
		softwareReleaseTransApi = new SoftwareReleaseTransApi(apiConfig);
		telemetryApi = new TelemetryApi(apiConfig);
	}

	public void setApiConfig(ApiConfig apiConfig) {
		this.apiConfig = apiConfig;
		accountApi.setApiConfig(apiConfig);
		coreEventApi.setApiConfig(apiConfig);
		coreUserApi.setApiConfig(apiConfig);
		deviceActionApi.setApiConfig(apiConfig);
		deviceApi.setApiConfig(apiConfig);
		gatewayApi.setApiConfig(apiConfig);
		nodeApi.setApiConfig(apiConfig);
		softwareReleaseScheduleApi.setApiConfig(apiConfig);
		telemetryApi.setApiConfig(apiConfig);
		deviceActionApi.setApiConfig(apiConfig);
	}

	public ApiConfig getApiConfig() {
		return apiConfig;
	}

	public AccountApi getAccountApi() {
		return accountApi;
	}

	public CoreEventApi getCoreEventApi() {
		return coreEventApi;
	}

	public CoreUserApi getCoreUserApi() {
		return coreUserApi;
	}

	public DeviceActionApi getDeviceActionApi() {
		return deviceActionApi;
	}

	public DeviceApi getDeviceApi() {
		return deviceApi;
	}

	public DeviceStateApi getDeviceStateApi() {
		return deviceStateApi;
	}

	public GatewayApi getGatewayApi() {
		return gatewayApi;
	}

	public NodeApi getNodeApi() {
		return nodeApi;
	}

	public NodeTypeApi getNodeTypeApi() {
		return nodeTypeApi;
	}

	public SoftwareReleaseScheduleApi getSoftwareReleaseScheduleApi() {
		return softwareReleaseScheduleApi;
	}

	public SoftwareReleaseTransApi getSoftwareReleaseTransApi() {
		return softwareReleaseTransApi;
	}

	public TelemetryApi getTelemetryApi() {
		return telemetryApi;
	}
}
