package com.wangchao.seller;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class HazelcastMapTest {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @PostConstruct
    public void put(){
        Map map = hazelcastInstance.getMap("wangchao");
        map.put("name","wangchao");
        map.put("age",16);
    }
}
