package pidev.afarshop;

import com.vader.sentiment.analyzer.SentimentAnalyzer;
import com.vader.sentiment.analyzer.SentimentPolarities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EntityScan(basePackages = {"pidev.afarshop.Entity"})
@ComponentScan(basePackages = {"pidev.afarshop.*"})
@EnableScheduling
public class AfarshopApplication {
	public static void main(String[] args) {
		SpringApplication.run(AfarshopApplication.class, args);
		/*final SentimentPolarities sentimentPolarities =
				SentimentAnalyzer.getScoresFor("good");
		System.out.println(sentimentPolarities);*/
	}



	//0 .0.25
	//0.25  0.5
	//0.5 0.75
	//0.75  1
}

