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

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acn.client.model.DeviceActionModel;
import com.arrow.acn.client.model.DeviceActionTypeModel;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.HidModel;
import com.arrow.acs.client.model.ListResultModel;
import com.fasterxml.jackson.core.type.TypeReference;

public class DeviceActionApi extends ApiAbstract {
	private static final String DEVICES_BASE_URL = API_BASE + "/devices";
	private static final String ACTION_TYPES_URL = DEVICES_BASE_URL + "/actions/types";
	private static final String SPECIFIC_DEVICE_URL = DEVICES_BASE_URL + "/{hid}";
	private static final String SPECIFIC_DEVICE_ACTIONS_URL = SPECIFIC_DEVICE_URL + "/actions";
	private static final String SPECIFIC_ACTION_URL = SPECIFIC_DEVICE_ACTIONS_URL + "/{index}";
	private static final String DEVICE_TYPES_URL = DEVICES_BASE_URL + "/types";
	private static final String SPECIFIC_DEVICE_TYPE_ACTIONS_URL = DEVICE_TYPES_URL + "/{hid}/actions";
	private static final String SPECIFIC_DEVICE_TYPE_ACTION_URL = SPECIFIC_DEVICE_TYPE_ACTIONS_URL + "/{index}";

	private static final TypeReference<ListResultModel<DeviceActionTypeModel>> DEVICE_ACTION_TYPE_MODEL_TYPE_REF = new TypeReference<ListResultModel<DeviceActionTypeModel>>() {
	};
	private static final TypeReference<ListResultModel<DeviceActionModel>> DEVICE_ACTION_MODEL_TYPE_REF = new TypeReference<ListResultModel<DeviceActionModel>>() {
	};

	DeviceActionApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	/**
	 * Sends GET request to obtain parameters of all available device action
	 * types
	 *
	 * @return list of {@link DeviceActionTypeModel} containing action type
	 *         parameters
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public ListResultModel<DeviceActionTypeModel> listAvailableActionTypes() {
		String method = "listAvailableActionTypes";
		try {
			URI uri = buildUri(ACTION_TYPES_URL);
			ListResultModel<DeviceActionTypeModel> result = execute(new HttpGet(uri),
			        DEVICE_ACTION_TYPE_MODEL_TYPE_REF);
			logDebug(method, "size: %s", result.getSize());
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends GET request to obtain parameters of all available device actions
	 * associated with specific device
	 *
	 * @param hid
	 *            String representing specific device
	 *
	 * @return list of {@link DeviceActionModel} containing action parameters
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public ListResultModel<DeviceActionModel> listDeviceActions(String hid) {
		String method = "listDeviceActions";
		try {
			URI uri = buildUri(SPECIFIC_DEVICE_ACTIONS_URL.replace("{hid}", hid));
			ListResultModel<DeviceActionModel> result = execute(new HttpGet(uri), DEVICE_ACTION_MODEL_TYPE_REF);
			logDebug(method, "size: %s", result.getSize());
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends POST request to create new action for specific device
	 *
	 * @param hid
	 *            {@link String} representing specific device
	 * @param model
	 *            {@link DeviceActionModel} representing action parameters
	 *
	 * @return {@link HidModel} containing {@code hid} of action created
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public HidModel createNewDeviceAction(String hid, DeviceActionModel model) {
		String method = "createNewDeviceAction";
		try {
			URI uri = buildUri(SPECIFIC_DEVICE_ACTIONS_URL.replace("{hid}", hid));
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends DELETE request to remove action from specific device
	 *
	 * @param hid
	 *            {@link String} representing specific device
	 * @param index
	 *            {@link Integer} representing index of action in actions list
	 *
	 * @return {@link HidModel} containing {@code hid} of action removed
	 *
	 * @throws AcnClientException
	 *             if request failed or action with specified {@code index} does
	 *             no exist
	 */
	public HidModel deleteDeviceAction(String hid, int index) {
		String method = "deleteDeviceAction";
		try {
			URI uri = buildUri(SPECIFIC_ACTION_URL.replace("{hid}", hid).replace("{index}", Integer.toString(index)));
			HidModel result = execute(new HttpDelete(uri), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	/**
	 * Sends PUT request to update existing action for specific device
	 *
	 * @param hid
	 *            {@link String} representing specific device
	 * @param index
	 *            {@link Integer} representing index of action in actions list
	 * @param model
	 *            {@link DeviceActionModel} representing updated action
	 *            parameters
	 *
	 * @throws AcnClientException
	 *             if request failed
	 */
	public HidModel updateDeviceAction(String hid, int index, DeviceActionModel model) {
		String method = "updateDeviceAction";
		try {
			URI uri = buildUri(SPECIFIC_ACTION_URL.replace("{hid}", hid).replace("{index}", Integer.toString(index)));
			HidModel result = execute(new HttpPut(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public ListResultModel<DeviceActionModel> listDeviceTypeActions(String deviceTypeHid) {
		String method = "listDeviceTypeActions";
		try {
			URI uri = buildUri(SPECIFIC_DEVICE_TYPE_ACTIONS_URL.replace("{hid}", deviceTypeHid));
			ListResultModel<DeviceActionModel> result = execute(new HttpGet(uri), DEVICE_ACTION_MODEL_TYPE_REF);
			logDebug(method, "size: %s", result.getSize());
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public HidModel createNewDeviceTypeAction(String deviceTypeHid, DeviceActionModel model) {
		String method = "createNewDeviceTypeAction";
		try {
			URI uri = buildUri(SPECIFIC_DEVICE_TYPE_ACTIONS_URL.replace("{hid}", deviceTypeHid));
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public HidModel deleteDeviceTypeAction(String deviceTypeHid, int index) {
		String method = "deleteDeviceTypeAction";
		try {
			URI uri = buildUri(SPECIFIC_DEVICE_TYPE_ACTION_URL.replace("{hid}", deviceTypeHid).replace("{index}",
			        Integer.toString(index)));
			HidModel result = execute(new HttpDelete(uri), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public HidModel updateDeviceTypeAction(String deviceTypeHid, int index, DeviceActionModel model) {
		String method = "updateDeviceTypeAction";
		try {
			URI uri = buildUri(SPECIFIC_DEVICE_TYPE_ACTION_URL.replace("{hid}", deviceTypeHid).replace("{index}",
			        Integer.toString(index)));
			HidModel result = execute(new HttpPut(uri), JsonUtils.toJson(model), HidModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
}
