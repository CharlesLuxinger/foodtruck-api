package com.charlesluxinger.foodtruck.api.notification;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("noticador.email")
public class NotificadorProperties {

	/**
	 * Host servidor de email
	 */
	private String serverHost;

	/**
	 * Porta servidor de email
	 */
	private Integer serverPort;

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}
}
