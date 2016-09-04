package club.iwalker.activemq.release.producer.service;

import club.iwalker.activemq.release.producer.entity.ProductInfo;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;


/**
 * Created by wangchen on 2016/9/4.
 */
@Component
public class ProducerService {

    private Logger logger = LoggerFactory.getLogger(ProducerService.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ActiveMQQueue releaseQueue;

    /**
     * 发送文本消息
     *
     * @param message
     */
    public void sendTextMessage(final String message) {
        jmsTemplate.send(releaseQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

    /**
     * 发送消息并处理消息返回值
     * @param message
     * @return
     * @throws JMSException
     */
    public String sendTextMessageAndReceive(final String message) throws JMSException {
        Message replyMessage = jmsTemplate.sendAndReceive(releaseQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
        TextMessage textMessage = (TextMessage) replyMessage;
        logger.info("sendTextAndReceive：{}", textMessage.toString());
        return textMessage.getText();
    }

    /**
     * 发送对象消息
     * @param productInfo
     * @return
     * @throws JMSException
     */
    public String sendObjectQueueMessage(final ProductInfo productInfo) throws JMSException {
        Message replyMessage = jmsTemplate.sendAndReceive(releaseQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(productInfo);
            }
        });
        TextMessage textMessage = (TextMessage) replyMessage;
        logger.info("sendObjectAndReceive：{}", textMessage.toString());
        return textMessage.getText();
    }

}
