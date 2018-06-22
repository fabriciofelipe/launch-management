package com.launch.management.launchmanagement;

import com.launch.management.launchmanagement.application.stream.Channels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(Channels.class)
public class LaunchManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaunchManagementApplication.class, args);
	}
}
