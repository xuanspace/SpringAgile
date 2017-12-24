package com.agile.framework.controller;

public class StringValue {
	
	String vstring = null;
	
	public StringValue(String val) {
		this.vstring = val;
	}

	public String toString() {
		return vstring;
	}

	public Integer toInt() {
		Integer value = null;
		if (vstring != null) {
			value = Integer.parseInt(vstring);	
		}
		return value;
	}

	public Long toLong() {
		Long value = null;
		if (vstring != null) {
			value = Long.parseLong(vstring);	
		}
		return value;
	}

	public Float toFloat() {
		Float value = null;
		if (vstring != null) {
			value = Float.parseFloat(vstring);	
		}
		return value;
	}

}
