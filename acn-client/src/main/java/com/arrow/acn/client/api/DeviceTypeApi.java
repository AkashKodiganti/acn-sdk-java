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

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acn.client.model.DeviceTypeModel;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.HidModel;
import com.arrow.acs.client.model.ListResultModel;
import com.fasterxml.jackson.core.type.TypeReference;

public class DeviceTypeApi extends ApiAbstract {
	private static final String DEVICES_BASE_URL = API_BASE + "/devices";
	private static final String DEVICE_TYPES_URL = DEVICES_BASE_URL + "/types";
	private static final String SPECIFIC_DEVICE_TYPE_URL = DEVICE_TYPES_URL + "/{hid}";

	private static final TypeReference<ListResultModel<DeviceTypeModel>> DEVICE_TYPE_MODEL_TYPE_REF = new TypeReference<ListResultModel<DeviceTypeModel>>() {
	};

	DeviceTypeApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	/**
	 * Sends GET request to obtain parameters of all available device types
	 *
	 * @return list of {@link DeviceTypeModel} containing type parameters
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public ListResultModel<DeviceTypeModel> listExistingDeviceTypes() {
		String method = "listExistingDeviceTypes";
		try {
			URI uri = buildUri(DEVICE_TYPES_URL);
			ListResultModel<DeviceTypeModel> result = execute(new HttpGet(uri), DEVICE_TYPE_MODEL_TYPE_REF);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends POST request to create new device type according to {@code model}
	 * passed
	 * 
	 * @param model
	 *            {@link DeviceTypeModel} representing parameters of device type
	 *            to be created
	 *
	 * @return {@link HidModel} containing {@code hid} of device type created
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public HidModel createNewDeviceType(DeviceTypeModel model) {
		String method = "createNewDeviceType";
		try {
			URI uri = buildUri(DEVICE_TYPES_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends PUT request to update existing device type according to
	 * {@code model} passed
	 *
	 * @param hid
	 *            {@link String} representing {@code hid} of device type to be
	 *            updated
	 * @param model
	 *            {@link DeviceTypeModel} representing device type parameters to
	 *            be updated
	 *
	 * @return {@link HidModel} containing {@code hid} of device type updated
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public HidModel updateDeviceType(String hid, DeviceTypeModel model) {
		String method = "updateDeviceAction";
		try {
			URI uri = buildUri(SPECIFIC_DEVICE_TYPE_URL.replace("{hid}", hid));
			HidModel result = execute(new HttpPut(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
}
