package com.wangchao.seller.service;

import com.wangchao.api.ProductRpc;
import com.wangchao.api.domain.ProductRpcReq;
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
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品服务相关
 */
@Service
public class ProductRpcService implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger logger = LoggerFactory.getLogger(ProductRpcService.class);

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
}
