package pidev.afarshop;

import com.vader.sentiment.analyzer.SentimentAnalyzer;
import com.vader.sentiment.analyzer.SentimentPolarities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;


import org.springframework.context.event.EventListener;


import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@EnableScheduling
@EnableAspectJAutoProxy
@EnableSpringConfigured
@SpringBootApplication
@ComponentScan({"pidev.afarshop.*","pidev.afarshop.Config"})
@EntityScan(basePackages = {"pidev.afarshop.Entity"})
public class AfarshopApplication {
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	public static void main(String[] args) {
		SpringApplication.run(AfarshopApplication.class, args);
		/*final SentimentPolarities sentimentPolarities =
				SentimentAnalyzer.getScoresFor("nice and i don't think it's perfect");
		System.out.println(sentimentPolarities);*/
	}



	//0 .0.25
	//0.25  0.5
	//0.5 0.75
	//0.75  1
}

