package com.mongodb.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class MongodbDao {
    /**
     * 未授权的情况下连接MongoDB
     */
    @Test
    public void lianjie(){
        //创建连接
        MongoClient mc=new MongoClient("192.168.0.124",27017);
        //查看是否连接成功
        System.out.println(mc.toString());
    }

    /**
     * 第一种授权方法
     */
    @Test
    public void lianjie2(){
        MongoClientURI mcuri=new MongoClientURI("mongodb://xianyaoji:123456@192.168.0.110:27017/admin");
        MongoClient mc=new MongoClient(mcuri);
        MongoDatabase md=mc.getDatabase("test1");
        MongoCollection mct=md.getCollection("3test");
        FindIterable<Document> fi=mct.find();
        for(Document d:fi){
            System.out.println(d.toString());
        }
//        Document document=new Document();
//        document.append("name","超哥");
//        mct.insertOne(document);
    }
    /**
     * 第二种授权方法
     */
    @Test
    public void lianjie3(){
        //获取服务器地址、端口
        ServerAddress sa=new ServerAddress("192.168.0.110",27017);
        //创建MongoDB的安全证书
        MongoCredential mctl=MongoCredential.createCredential("xiaoyaoji","admin","123456".toCharArray());
        List<MongoCredential> list=new ArrayList<MongoCredential>();
        list.add(mctl);
        MongoClient mct=new MongoClient(sa,list);
        System.out.println(mct.toString());
    }
}
