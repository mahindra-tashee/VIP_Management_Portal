package com.example.vipReferenceMgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class VipManagementPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(VipManagementPortalApplication.class, args);
	}

}
