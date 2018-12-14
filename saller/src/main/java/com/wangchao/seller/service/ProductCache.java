package com.wangchao.seller.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.wangchao.api.ProductRpc;
import com.wangchao.api.domain.ProductRpcReq;
import com.wangchao.entity.Product;
import com.wangchao.entity.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ProductCache {

    private static Logger logger = LoggerFactory.getLogger(ProductCache.class);

    public static final String CACHE_NAME = "wangchao_product" ;

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Autowired
    private ProductRpc productRpc;

    @Cacheable(cacheNames = CACHE_NAME)
    public Product readCache(String id){
        logger.info("rpc 查询单个商品 请求" + id);
        Product result = productRpc.findOne(id);
        logger.info("rpc 查询单个商品 结果" + result);
        return result;
    }

    @CachePut(cacheNames = CACHE_NAME,key = "#product.id")
    public Product putCache(Product product){
        return product;
    }

    @CacheEvict(cacheNames = CACHE_NAME)
    public void removeCache(String id){}

    public List<Product> readAllCache(){
        Map map = hazelcastInstance.getMap(CACHE_NAME);
        List<Product> products = null;
        if(map.size() > 0){
            products = new ArrayList<>();
            products.addAll(map.values());
        }else {
            products = findAll();
        }
        return products;
    }


    /**
     * 查询全部产品
     *
     * @return
     */
    public List<Product> findAll() {
        ProductRpcReq req = new ProductRpcReq();
        List<String> status = new ArrayList<>();
        status.add(ProductStatus.IN_SELL.name());
        req.setStatusList(status);

        logger.info("rpc查询全部产品请求: {}", req);
        List<Product> result = productRpc.query(req);
        logger.info("rpc查询全部产品结果: {}", result);
        return result;
    }
}
