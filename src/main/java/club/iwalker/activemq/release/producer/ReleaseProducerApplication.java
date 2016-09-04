package club.iwalker.activemq.release.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class ReleaseProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReleaseProducerApplication.class, args);
	}
}
