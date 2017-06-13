package com.arrow.acn.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class TestProcedureRegistrationModel implements Serializable {
	private static final long serialVersionUID = 8527833325939436585L;
	
	private String name;
	private String description;
	private boolean enabled = true;
	private String deviceTypeHid;
	private List<TestProcedureStepModel> steps = new ArrayList<>();

	public void trim() {
		name = StringUtils.trimToNull(name);
		description = StringUtils.trimToNull(description);
		deviceTypeHid = StringUtils.trimToNull(deviceTypeHid);
	}

	public List<TestProcedureStepModel> getSteps() {
		return steps;
	}

	public void setSteps(List<TestProcedureStepModel> testProcedureSteps) {
		this.steps = testProcedureSteps;
	}

	public String getDeviceTypeHid() {
		return deviceTypeHid;
	}

	public void setDeviceTypeHid(String deviceTypeHid) {
		this.deviceTypeHid = deviceTypeHid;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
}
