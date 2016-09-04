package club.iwalker.activemq.jms.producer;

import club.iwalker.activemq.jms.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangchen on 2016/9/4.
 */
@RestController
public class JmsController {


    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping("/sendMsg")
    public void sendMessage() {
        System.out.println("================= Sending an email message.");
        jmsTemplate.convertAndSend("mailbox", new Email("controller@example.com", "controller"));
    }
}
