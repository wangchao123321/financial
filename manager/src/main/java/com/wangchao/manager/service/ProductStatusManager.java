package com.wangchao.manager.service;

import com.wangchao.api.events.ProductStatusEvent;
import com.wangchao.entity.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 管理产品状态
 */
@Component
public class ProductStatusManager {
    private static Logger logger = LoggerFactory.getLogger(ProductStatusManager.class);
    private static final String MQ_DESTINATION = "VirtualTopic.PRODUCT_STATUS";

    @Autowired
    private JmsTemplate jmsTemplate;

    public void changeStatus(String id, ProductStatus status){
        ProductStatusEvent event = new ProductStatusEvent(id,status);
        logger.info("send message: {}" + event);
        jmsTemplate.convertAndSend(MQ_DESTINATION,event);
    }

    @PostConstruct
    public void init(){
        changeStatus("001",ProductStatus.FINISHED);
    }

}
