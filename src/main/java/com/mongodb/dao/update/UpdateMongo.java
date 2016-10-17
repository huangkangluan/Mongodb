package com.mongodb.dao.update;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.junit.Before;

/**
 * Created by Administrator on 2016/9/7.
 */
public class UpdateMongo {
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
}
