/**
 * Created by Bogoslovskiy Denis 2020
 */
package com.example.Library;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {

		SpringApplication.run(LibraryApplication.class, args);
	}

//	@Bean
//	public LayoutDialect layoutDialect() {
//		return new LayoutDialect();
//	}

}
