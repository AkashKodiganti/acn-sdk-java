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
package com.arrow.acn.client.api;

import java.net.URI;
import java.util.regex.Matcher;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acn.client.model.DeviceModel;
import com.arrow.acn.client.search.DeviceSearchCriteria;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.CloudMqttRequestParams;
import com.arrow.acs.client.model.CloudRequestMethodName;
import com.arrow.acs.client.model.CloudResponseModel;
import com.arrow.acs.client.model.PagingResultModel;
import com.fasterxml.jackson.core.type.TypeReference;

public class DeviceMqttApi extends MqttApiAbstract {

	DeviceMqttApi(ApiConfig apiConfig, CouldResponseProcessorApi couldResponseProcessorApi) {
		super(apiConfig, couldResponseProcessorApi);
	}

	public void findAllBy(String requestId, DeviceSearchCriteria criteria) {
		String method = "findAllBy";
		try {
			// build the same URI as for http call
			URI uri = buildUri(DeviceApi.FIND_ALL_BY_URL, criteria);
			// get string path
			String strPath = uri.getPath();
			// prepare message params
			// NOTE: encrypted is false by default
			CloudMqttRequestParams params = new CloudMqttRequestParams();
			params.setRequestId(requestId);
			params.setHttpMethod(CloudRequestMethodName.GET);
			params.setUrl(strPath);
			params.setCriteria(criteria);
			send(params);
			logDebug(method, "message sent with requestId: " + requestId);
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public void findByHid(String requestId, String hid) {
		String method = "findByHid";
		try {
			// build the same URI as for http call
			URI uri = buildUri(
					DeviceApi.PATTERN.matcher(DeviceApi.FIND_BY_HID_URL).replaceAll(Matcher.quoteReplacement(hid)));
			// get string path
			String strPath = uri.getPath();
			// prepare message params
			// NOTE: encrypted is false by default
			CloudMqttRequestParams params = new CloudMqttRequestParams();
			params.setRequestId(requestId);
			params.setHttpMethod(CloudRequestMethodName.GET);
			params.setUrl(strPath);
			send(params);
			logDebug(method, "message sent with requestId: " + requestId);
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public static PagingResultModel<DeviceModel> parseFindAllByResponse(CloudResponseModel responseModel) {
		verifyCloudResponseResponse(responseModel);

		try {
			PagingResultModel<DeviceModel> result = JsonUtils.fromJson(
					responseModel.getParameters().get(CloudResponseModel.PAYLOAD_PARAMETER_NAME),
					new TypeReference<PagingResultModel<DeviceModel>>() {
					});
			return result;
		} catch (Throwable e) {
			// TODO: handle exception
		}
		return null;
	}

	public static DeviceModel parseFindByHidResponse(CloudResponseModel responseModel) {
		verifyCloudResponseResponse(responseModel);

		try {
			DeviceModel result = JsonUtils.fromJson(
					responseModel.getParameters().get(CloudResponseModel.PAYLOAD_PARAMETER_NAME), DeviceModel.class);
			return result;
		} catch (Throwable e) {
			// TODO: handle exception
		}
		return null;
	}
}
