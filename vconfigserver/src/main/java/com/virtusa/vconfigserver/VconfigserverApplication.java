package com.virtusa.vconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class VconfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(VconfigserverApplication.class, args);
	}

}
