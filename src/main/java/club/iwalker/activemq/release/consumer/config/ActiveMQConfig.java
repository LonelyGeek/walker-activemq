package club.iwalker.activemq.release.consumer.config;

import com.sun.org.apache.regexp.internal.RE;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.Queue;

/**
 * Created by wangchen on 2016/9/5.
 */
@Configuration
@Description(value = "ActiveMQ Configuration")
public class ActiveMQConfig {

    public static final String QUEUE_RELEASE = "queue.release";//定义队列名称

    //消息队列bean
    @Bean(name = "releaseQueue")
    public Queue getReleaseQueue() {
        return new ActiveMQQueue(QUEUE_RELEASE);
    }

    /**
     * 消息监听适配器
     * @return
     */
    @Bean(name = "messageListenerAdapter")
    public MessageListenerAdapter getMessageListenerAdapter() {
        MessageListenerAdapter adapter = new MessageListenerAdapter();
        adapter.setMessageConverter(new SimpleMessageConverter());
        adapter.setDelegate(getConsumerListener());
        return  adapter;
    }

    /**
     * 获取缓存连接工厂
     * @param connectionFactory
     * @return
     */
    public CachingConnectionFactory getCachingConnectionFactory(ActiveMQConnectionFactory connectionFactory) {
        CachingConnectionFactory factory = new CachingConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public MessageListenerContainer getMessageListenerContainer(ActiveMQConnectionFactory connectionFactory) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(getCachingConnectionFactory(connectionFactory));
        container.setDestination(getReleaseQueue());
        container.setMessageListener(getMessageListenerAdapter());
        container.setConcurrency("10-50");
        return container;
    }


    /**
     * 消费者监听
     * @return
     */
    @Bean
    public ConsumerListener getConsumerListener() {
        return new ConsumerListener();
    }
}
