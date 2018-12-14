package com.wangchao.seller.service;

import com.wangchao.api.ProductRpc;
import com.wangchao.api.domain.ProductRpcReq;
import com.wangchao.api.events.ProductStatusEvent;
import com.wangchao.entity.Product;
import com.wangchao.entity.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品服务相关
 */
@Service
public class ProductRpcService implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger logger = LoggerFactory.getLogger(ProductRpcService.class);

    static final String MQ_DESTINATION = "Consumer.cache.VirtualTopic.PRODUCT_STATUS";

    @Autowired
    private ProductRpc productRpc;

    @Autowired
    private ProductCache productCache;

    /**
     * 查询全部产品
     *
     * @return
     */
    public List<Product> findAll() {
        return productCache.readAllCache();
    }

    /**
     * 查询单个商品
     * @param id
     * @return
     */
    public Product findOne(String id){
        Product product = productCache.readCache(id);
        if(product == null){
            productCache.removeCache(id);
//            Product result = productRpc.findOne(id);
//            productCache.putCache(result);
        }
        return product;
    }

//    @PostConstruct
    public void init() {
        findAll();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Product> products = findAll();
        products.forEach(product -> {
            productCache.putCache(product);
        });
    }

    @JmsListener(destination = MQ_DESTINATION)
    public void updateCache(ProductStatusEvent event){
        logger.info("recerve event: {}" + event);
        productCache.removeCache(event.getId());
        if(ProductStatus.IN_SELL.equals(event.getStatus())){
            productCache.readCache(event.getId());
        }
    }
}
