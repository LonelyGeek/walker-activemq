package club.iwalker.activemq.spring;

import club.iwalker.activemq.spring.producer.SpringProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class SpringActiveMQApplication implements CommandLineRunner {

	@Autowired
	private JmsTemplate jmsTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringActiveMQApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		jmsTemplate.send("spring-destination", new SpringProducer());
	}
}
