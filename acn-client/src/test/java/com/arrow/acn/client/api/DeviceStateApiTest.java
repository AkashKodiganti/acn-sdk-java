package com.arrow.acn.client.api;

import com.arrow.acn.client.websocket.MessageListener;
import com.arrow.acs.client.api.ApiConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class DeviceStateApiTest {

    private AcnClient acnClient;

    private MessageListener messageListener = new MessageListener() {
        @Override
        public void onMessage(String message) {
            System.out.println(message);
        }
    };

    @Before
    public void init() {
        ApiConfig apiConfig = new ApiConfig()
                .withBaseUrl("http://localhost:8080")
                .withApiKey("apiKey")
                .withSecretkey("secretKey");

        acnClient = new AcnClient(apiConfig);
    }

    @Test
    public void deleteDeviceStates() throws Exception {
        final ArrayList<String> deviceStatesNames = new ArrayList<>();
        deviceStatesNames.add("testStateName");
        boolean removeStatesDefinitionToo = true;
        acnClient.getDeviceStateApi()
                .deleteDeviceStates(deviceStatesNames, "deviceHID", removeStatesDefinitionToo);
    }
}