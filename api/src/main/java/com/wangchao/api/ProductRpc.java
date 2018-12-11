package com.wangchao.api;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.wangchao.api.domain.ProductRpcReq;
import com.wangchao.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;


@JsonRpcService("rpc/products")
public interface ProductRpc {

    List<Product> query(ProductRpcReq productRpcReq);

    Product findOne(String id);
}
