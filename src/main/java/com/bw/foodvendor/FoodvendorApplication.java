package com.bw.foodvendor;


import com.bw.foodvendor.configuration.AuthorizationServerConfiguration;
import com.bw.foodvendor.configuration.ResourceServerConfiguration;
import com.bw.foodvendor.configuration.ServiceLayerConfiguration;
import com.bw.foodvendor.configuration.WebSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(exclude = {
		HibernateJpaAutoConfiguration.class,
		ValidationAutoConfiguration.class
})

@Import({
		ServiceLayerConfiguration.class,
		WebConfiguration.class,
		AuthorizationServerConfiguration.class,
		WebSecurityConfiguration.class,
		ResourceServerConfiguration.class
})

@EnableScheduling
public class FoodvendorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodvendorApplication.class, args);
	}

}
