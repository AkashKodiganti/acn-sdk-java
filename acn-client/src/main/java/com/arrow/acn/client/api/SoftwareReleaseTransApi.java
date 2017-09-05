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

import java.net.URI;
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acn.client.model.SoftwareReleaseTransRegistrationModel;
import com.arrow.acn.client.model.SoftwareReleaseUpgradeModel;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.HidModel;
import com.arrow.acs.client.model.StatusModel;

public class SoftwareReleaseTransApi extends ApiAbstract {
	private static final String SOFTWARE_RELEASE_TRANS_BASE_URL = API_BASE + "/software/releases/transactions";
	private static final String RECEIVED_URL = SOFTWARE_RELEASE_TRANS_BASE_URL + "/%s/received";
	private static final String SUCCEEDED_URL = SOFTWARE_RELEASE_TRANS_BASE_URL + "/%s/succeeded";
	private static final String FAILED_URL = SOFTWARE_RELEASE_TRANS_BASE_URL + "/%s/failed";
	private static final String CREATE_URL = SOFTWARE_RELEASE_TRANS_BASE_URL;
	private static final String UPGRADE_GATEWAY_URL = SOFTWARE_RELEASE_TRANS_BASE_URL + "/gateways/upgrade";
	private static final String UPGRADE_DEVICE_URL = SOFTWARE_RELEASE_TRANS_BASE_URL + "/devices/upgrade";
	private static final String START_URL = SOFTWARE_RELEASE_TRANS_BASE_URL + "/%s/start";

	public SoftwareReleaseTransApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public StatusModel received(String hid) {
		String method = "received";
		try {
			URI uri = buildUri(String.format(RECEIVED_URL, hid));
			StatusModel result = execute(new HttpPut(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public StatusModel succeeded(String hid) {
		String method = "succeeded";
		try {
			URI uri = buildUri(String.format(SUCCEEDED_URL, hid));
			StatusModel result = execute(new HttpPut(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public StatusModel failed(String hid, String error) {
		String method = "failed";
		try {
			URI uri = buildUri(String.format(FAILED_URL, hid));
			StatusModel result = execute(new HttpPut(uri),
			        JsonUtils.toJson(Collections.singletonMap("error", StringUtils.trimToEmpty(error))),
			        StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public HidModel create(SoftwareReleaseTransRegistrationModel model) {
		String method = "create";
		try {
			URI uri = buildUri(CREATE_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public StatusModel start(String hid) {
		String method = "start";
		try {
			URI uri = buildUri(String.format(START_URL, hid));
			StatusModel result = execute(new HttpPost(uri), StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public HidModel upgradeGateway(SoftwareReleaseUpgradeModel model) {
		String method = "upgradeGateway";
		try {
			URI uri = buildUri(UPGRADE_GATEWAY_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public HidModel upgradeDevice(SoftwareReleaseUpgradeModel model) {
		String method = "upgradeDevice";
		try {
			URI uri = buildUri(UPGRADE_DEVICE_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
}
