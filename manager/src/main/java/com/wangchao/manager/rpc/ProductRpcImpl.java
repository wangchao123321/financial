package com.wangchao.manager.rpc;


import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.wangchao.api.ProductRpc;
import com.wangchao.api.domain.ProductRpcReq;
import com.wangchao.entity.Product;
import com.wangchao.manager.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@AutoJsonRpcServiceImpl
@Service
public class ProductRpcImpl implements ProductRpc {

    private static Logger logger = LoggerFactory.getLogger(ProductRpcImpl.class);

    @Autowired
    private ProductService productService;

    @Override
    public List<Product> query(ProductRpcReq req) {
        logger.info("查询多个产品: {}",req);
        Pageable pageable = new PageRequest(0,1000,Sort.Direction.DESC,"rewardRate");
        Page<Product> result = productService.query(req.getIdList(),
                req.getMinRewardRate(), req.getMaxRewardRate(), req.getStatusList(), pageable);
        logger.info("查询多个产品: {}",result);
        return result.getContent();
    }

    @Override
    public Product findOne(String id) {
        logger.info("查询产品详情: {}",id);
        Product result = productService.findOne(id);
        logger.info("查询产品详情: {}",result);
        return result;
    }
}
