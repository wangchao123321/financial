package com.wangchao.seller.controller;

import com.wangchao.entity.Order;
import com.wangchao.seller.params.OrderParam;
import com.wangchao.seller.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单相关
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping("/apply")
    public Order apply(@RequestHeader String authId,@RequestHeader String sign,@RequestBody OrderParam param){
        logger.info("申购请求: {}" + param);
        Order order = new Order();
        BeanUtils.copyProperties(param,order);
        order = orderService.apply(order);
        logger.info("申购结果: {}" + order);
        return order;
    }


}
