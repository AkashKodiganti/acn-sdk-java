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
package com.arrow.acn.client.cloud;

import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import com.arrow.acn.client.AcnClientException;
import com.arrow.acn.client.IotParameters;
import com.arrow.acn.client.api.AcnClient;
import com.arrow.acn.client.model.AwsConfigModel;
import com.arrow.acn.client.utils.SslUtil;

/**
 * Connector allows to interact with Amazon Web Services (AWS) MQTT topics
 */
public class AwsConnector extends MqttConnectorAbstract {
	private final AwsConfigModel model;

	public AwsConnector(AwsConfigModel model, AcnClient acnClient) {
		super(String.format("ssl://%s", model.getHost()), acnClient);
		this.model = model;
		// TODO revisit
		// setSubscriber(false);
	}

	@Override
	protected MqttConnectOptions mqttConnectOptions() {
		try {
			MqttConnectOptions options = super.mqttConnectOptions();
			options.setSocketFactory(
			        SslUtil.getSocketFactory(model.getCaCert(), model.getClientCert(), model.getPrivateKey()));
			return options;
		} catch (Exception e) {
			throw new AcnClientException("error creating mqtt options", e);
		}
	}

	@Override
	protected String publisherTopic(IotParameters payload) {
		return String.format("telemetries/devices/%s", payload.getDeviceHid());
	}

	@Override
	protected String publisherBatchTopic(List<IotParameters> payload) {
		throw new AcnClientException("batch mode is not supported for AWS integration at this time");
	}

	@Override
	protected String publisherGzipBatchTopic(List<IotParameters> payload) {
		throw new AcnClientException("GzipBatch mode is not supported for AWS integration at this time");
	}

	
	
	@Override
	protected String publisherCloudRequestTopic() {
		throw new AcnClientException("Cloud Request is not supported for AWS integration at this time");
	}

	@Override
	protected String subscriberTopic() {
		return null;
	}

	@Override
	protected String subscriberCloudResponseTopic() {
		return null;
	}
	
	
}
