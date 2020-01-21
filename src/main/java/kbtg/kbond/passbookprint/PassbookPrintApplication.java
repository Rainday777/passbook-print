package kbtg.kbond.passbookprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import kbtg.kbond.passbookprint.config.WebSocketProperties;


@SpringBootApplication
@EnableConfigurationProperties(WebSocketProperties.class)
public class PassbookPrintApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassbookPrintApplication.class, args);
	}

}
