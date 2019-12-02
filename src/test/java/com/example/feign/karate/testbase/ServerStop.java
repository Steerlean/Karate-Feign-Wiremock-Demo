package com.example.feign.karate.testbase;

import org.junit.Test;

public class ServerStop {

	@Test
	public void stopServer() {
		MonitorThread.stop(8081);
	}
	
}
