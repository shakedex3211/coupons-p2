package com.project.couponsp2;

import com.project.couponsp2.beans.Company;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication // @Configuration + @ComponentScan(basePackages="com.project.couponsp2" + @EnableAutoConfiguration
@EnableScheduling
public class CouponsP2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =
				SpringApplication.run(CouponsP2Application.class, args);
		System.out.println("Library is Running...");

	}

}
