package com.mongodb.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class InsertMongo {
    private MongoClientURI mongoClientURI;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<BasicDBObject> mongoCollection;
    private FindIterable<BasicDBObject> findIterable;

    /**
     * 以上相关对象的初始化
     */
    @Before
    public void init(){
        //创建一个连接
        mongoClientURI=new MongoClientURI("mongodb://xianyaoji:123456@192.168.0.110:27017/admin");
        mongoClient=new MongoClient(mongoClientURI);
        mongoDatabase=mongoClient.getDatabase("test1");
        mongoCollection=mongoDatabase.getCollection("3test",BasicDBObject.class);
    }
    @Test
    public void insertOne(){
        List<String> hobby=new ArrayList<String>();
        hobby.add("看书");
        hobby.add("跑步");
        hobby.add("读物");
        BasicDBObject basicDBObject=new BasicDBObject("hobby",hobby).append("name","小李子")
                .append("sex","未知");
        mongoCollection.insertOne(basicDBObject);
    }
    @Test
    public void insertMany(){
        List<BasicDBObject> list=new ArrayList<BasicDBObject>();
        BasicDBObject name1=new BasicDBObject("name","隔壁老王");
        BasicDBObject name2=new BasicDBObject("name","隔壁老宋");
        BasicDBObject name3=new BasicDBObject("name","隔壁老李");
        list.add(name1);
        list.add(name2);
        list.add(name3);
        mongoCollection.insertMany(list);
    }
    @Test
    public void deleteOne(){
        BasicDBObject basicDBObject=new BasicDBObject("name","超哥1");
        DeleteResult num=mongoCollection.deleteOne(basicDBObject);
        System.out.println(num.toString());
    }
    @Test
    public void delete(){
        BasicDBObject basicDBObject=new BasicDBObject("name","超哥/i");
        DeleteResult num=mongoCollection.deleteOne(basicDBObject);
        System.out.println(num.toString());
    }

}
