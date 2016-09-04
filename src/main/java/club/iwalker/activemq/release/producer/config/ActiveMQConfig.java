package club.iwalker.activemq.release.producer.config;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.Queue;
import javax.naming.NamingException;

/**
 * Created by wangchen on 2016/9/4.
 */
@Configuration
@Description(value = "ActiveMQ Configuration")
public class ActiveMQConfig {

    public static final String QUEUE_RELEASE = "queue.release";//定义队列名称

    public static final int POOL_MAX_CONNECTION_NUM = 5;

    //消息队列bean
    @Bean(name = "releaseQueue")
    public Queue getReleaseQueue() {
        return new ActiveMQQueue(QUEUE_RELEASE);
    }

    //定义消息队列连接池
    public PooledConnectionFactory getConnectionFactory(ActiveMQConnectionFactory connectionFactory) {
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(connectionFactory);
        pooledConnectionFactory.setMaxConnections(POOL_MAX_CONNECTION_NUM);
        return pooledConnectionFactory;
    }

    //定义消息队列TEMPLATE
    @Bean(name = "jmsTemplate")
    public JmsTemplate getJmsTemplate(ActiveMQConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(getConnectionFactory(connectionFactory));
        jmsTemplate.setDefaultDestination(getReleaseQueue());
        jmsTemplate.setMessageConverter(new SimpleMessageConverter());
        return jmsTemplate;
    }
}
