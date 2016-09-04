package club.iwalker.activemq.release.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class ReleaseConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReleaseConsumerApplication.class, args);
	}
}
