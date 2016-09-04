package club.iwalker.activemq.spring.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by wangchen on 2016/9/4.
 */
@Component
public class Receiver {
    @JmsListener(destination = "spring-destination")
    public void receiveMessage(String message) {
        System.out.println("接收到：<"+message+">");
    }
}
