package com.rajat.compmsys.mqtt;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTUtils {

	private static MqttClient client;

	public static MqttClient getClient() {
		return client;
	}

	public static boolean connect(String url) {
		try {
			MemoryPersistence persistance = new MemoryPersistence();
			client = new MqttClient("tcp://" + url + ":1883", "client12", persistance);
			client.connect();
			//client.subscribe("presence",1);
			return true;
		} catch (MqttException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean pub(String topic, String payload) {
		MqttMessage message = new MqttMessage(payload.getBytes());
		message.setQos(1);
		try {
			client.publish(topic, message);
			return true;
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean sub(String topic, int qos) {
		//MqttMessage message = new MqttMessage(payload.getBytes());
		try {
			client.subscribe(topic);
			client.setCallback(new MyMQTTCallback());
			return true;
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
		} catch (MqttException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static class MyMQTTCallback implements MqttCallback{

		@Override
		public void connectionLost(Throwable throwable) {

		}

		@Override
		public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
			Log.i("rajat", "Message arrived from topic" + topic);

			String msg= new String(mqttMessage.getPayload());
			Log.i("rajat", "Message: " + msg);
		}

		@Override
		public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

		}
	}
}
