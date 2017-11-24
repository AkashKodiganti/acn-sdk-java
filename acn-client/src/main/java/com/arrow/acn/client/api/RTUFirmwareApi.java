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
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acn.client.model.RTUFirmwareModels.RTUFirmwareModel;
import com.arrow.acn.client.model.RTUFirmwareModels.RTURequestedFirmwareModel;
import com.arrow.acn.client.search.RTUAvailableSearchCriteria;
import com.arrow.acn.client.search.RTURequestSearchCriteria;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.PagingResultModel;
import com.arrow.acs.client.model.StatusModel;
import com.fasterxml.jackson.core.type.TypeReference;

public class RTUFirmwareApi extends ApiAbstract {

	private static final String RTU_BASE_URL = API_BASE + "/rtu";
	private static final String REQUEST_URL = RTU_BASE_URL+ "/request/{softwareReleaseHid}";
	private static final String FIND_REQUESTED = RTU_BASE_URL + "/find";
	private static final String FIND_AVAILABLE = FIND_REQUESTED + "/available";
	

	private static final TypeReference<List<RTUFirmwareModel>> RTU_FIRMWARE_MODEL = 
			new TypeReference<List<RTUFirmwareModel>>() {};
	private static final TypeReference<PagingResultModel<RTURequestedFirmwareModel>> RTU_REQUESTED_FIRMWARE_MODEL = 
			new TypeReference<PagingResultModel<RTURequestedFirmwareModel>>() {};

	RTUFirmwareApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public StatusModel requestRTU(String softwareReleaseHid) {
		String method = "requestRTU";
		
		try {
			URI uri = buildUri(REQUEST_URL.replace("{softwareReleaseHid}", softwareReleaseHid));
			StatusModel result = execute(new HttpPut(uri),
			        StatusModel.class);
			log(method, result);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public List<RTUFirmwareModel> findAvailableFirmware(RTUAvailableSearchCriteria criteria) {
		String method = "findAvailableFirmware";
		try {
			URI uri = buildUri(FIND_AVAILABLE, criteria);
			List<RTUFirmwareModel> result = execute(new HttpGet(uri),
					RTU_FIRMWARE_MODEL);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}

	public PagingResultModel<RTURequestedFirmwareModel> findRequestedFirmware(RTURequestSearchCriteria criteria) {
		String method = "findRequestedFirmware";
		try {
			URI uri = buildUri(FIND_REQUESTED, criteria);
			PagingResultModel<RTURequestedFirmwareModel> result = execute(new HttpGet(uri),
					RTU_REQUESTED_FIRMWARE_MODEL);
			return result;
		} catch (Throwable e) {
			logError(method, e);
			throw new AcnClientException(method, e);
		}
	}
}
