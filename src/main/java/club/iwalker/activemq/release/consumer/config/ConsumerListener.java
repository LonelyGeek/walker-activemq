package club.iwalker.activemq.release.consumer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by wangchen on 2016/9/5.
 */
@Component("consumerListener")
public class ConsumerListener {
    private Logger logger = LoggerFactory.getLogger(ConsumerListener.class);

    public void handleMessage(String message) {
        System.out.println("============= >>>>" + message);
        logger.info("TextMessage: {}", message);
    }
}
