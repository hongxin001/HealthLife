package com.healthslife.pedometer.tools;

public class AirConditionData {
	
	public int temperature=0;
	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int humidity=0;
	
	public AirConditionData() {
	}
	
	public AirConditionData(int tmp,int humi) {
		setTemperature(tmp);
		setHumidity(humi);
	}

}
