package club.iwalker.activemq.release.producer.web;

import club.iwalker.activemq.release.producer.entity.ProductInfo;
import club.iwalker.activemq.release.producer.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by wangchen on 2016/9/5.
 */
@RestController
public class ProducerController {
    private Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @Autowired
    private ProducerService producerService;

    @RequestMapping("/sendReleaseMsg")
    public void sendMessage() {
        String message = "Release Queue Message. Date：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        //发送消息不返回值
//        producerService.sendTextMessage(message);

        /*//发送消息带返回值
        try {
            String result = producerService.sendTextMessageAndReceive(message);
            System.out.println("============= >>>>" + result);
            logger.info("ProducerController ====> result：{}, isSuccess：{}", result, "SUCCESS".equals(result));

        } catch (JMSException e) {
            e.printStackTrace();
            logger.error("请求加入队列失败：{}", e.getMessage());
        }*/

        //发送消息对象
        try {
            ProductInfo productInfo = new ProductInfo(new Random().nextInt(100), message, new BigDecimal(new Random().nextDouble()), new Date());
            String result = producerService.sendObjectQueueMessage(productInfo);
            System.out.println("============= >>>>" + result);
            logger.info("ProducerController ====> result：{}, isSuccess：{}", result, "SUCCESS".equals(result));
        } catch (JMSException e) {
            e.printStackTrace();
            logger.error("请求加入队列失败：{}", e.getMessage());
        }

    }
}
