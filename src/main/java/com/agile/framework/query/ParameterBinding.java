package com.agile.framework.query;

public class ParameterBinding {

	public int position;
	
	public String name = null;
	
	public Object value = null;
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		String str = "";
		if (value != null)
			str = value.toString();
		return str;
	}
}
