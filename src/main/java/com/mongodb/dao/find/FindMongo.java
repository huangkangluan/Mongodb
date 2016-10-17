package com.mongodb.dao.find;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.QueryOperators;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class FindMongo {
    private MongoClientURI mongoClientURI;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<BasicDBObject> mongoCollection;
    private FindIterable<BasicDBObject> findIterable;
    /**
     * 以上相关对象的初始化
     */
//    @Before
//    public void init(){
//        //创建一个连接
//        mongoClientURI=new MongoClientURI("mongodb://xianyaoji:123456@192.168.0.110:27017/admin");
//        mongoClient=new MongoClient(mongoClientURI);
//        mongoDatabase=mongoClient.getDatabase("test1");
//        mongoCollection=mongoDatabase.getCollection("3test",BasicDBObject.class);
//    }
    @Before
    public void open(){
        //创建连接
        mongoClient=new MongoClient("127.0.0.1",27017);
        mongoDatabase=mongoClient.getDatabase("test");
        mongoCollection=mongoDatabase.getCollection("goods",BasicDBObject.class);
    }
    private void show(){
        for(BasicDBObject basicDBObject:findIterable){
            System.out.println(basicDBObject.toJson());
        }
    }
    //1.1:主键为32的商品
    @Test
    public void goods_id32(){
        BasicDBObject bd=new BasicDBObject("goods_id",32);
        findIterable=mongoCollection.find(bd);
        show();
    }
    //1.2:不属第3栏目的所有商品($ne)
    @Test
    public void cat_id3(){
        findIterable= mongoCollection.find(ne("cat_id",3));
        show();
    }
    //1.3:本店价格高于3000元的商品{$gt}
    @Test
    public void shop_price3000(){
        findIterable=mongoCollection.find(gt("shop_price",3000));
        show();
    }
    //1.4:本店价格低于或等于100元的商品($lte)
    @Test
    public void shop_price100(){
        findIterable=mongoCollection.find(lte("shop_price", 100));
        show();
    }
    //1.5:取出第4栏目或第11栏目的商品($in)
    @Test
    public void cat_id4_11(){
        //第一种方法
//        BasicDBObject queryObject = new BasicDBObject().append("cat_id", new BasicDBObject(
//                QueryOperators.IN, new int[]{4, 11}));
//        findIterable=mongoCollection.find(queryObject);
//        show();
        //第二种方法
        findIterable=mongoCollection.find(in("cat_id", 4, 11));
        show();
    }
    //1.6:取出100>=价格<=500的商品($and)
    @Test
    public void shop_price100_500(){
        findIterable=mongoCollection.find(and(gte("shop_price", 100), lte("shop_price",500)));
        show();
    }
    //1.7:取出不属于第3栏目且不属于第11栏目的商品($and $nin和$nor分别实现)
    @Test
    public void and_cat_id(){
        findIterable=mongoCollection.find(and(ne("cat_id", 3), ne("cat_id", 11)));
        show();
    }
    //1.7:取出不属于第3栏目且不属于第11栏目的商品用 $nin实现
    @Test
    public void nin_cat_id(){
        //第一种方法
//        BasicDBObject queryObject = new BasicDBObject().append("cat_id", new BasicDBObject(
//                QueryOperators.NIN, new int[]{3, 11}));
//        findIterable=mongoCollection.find(queryObject);
//        show();
        //第二种方法
        findIterable=mongoCollection.find(nin("cat_id", 3, 11));
        show();
    }
    //1.7:取出不属于第3栏目且不属于第11栏目的商品使用 $nor分别实现
    @Test
    public void nor_cat_id(){
        //第一种方法
//        BasicDBObject queryObject = new BasicDBObject().append("cat_id", new BasicDBObject(
//                QueryOperators.NOR, new int[]{3, 11}));
//        findIterable=mongoCollection.find(queryObject);
//        show();
        //第二种方法
        BasicDBObject bd=new BasicDBObject("cat_id",3);
        BasicDBObject bd2=new BasicDBObject("cat_id",11);
        findIterable=mongoCollection.find(nor(bd,bd2));
        show();
    }
    //1.8:取出价格大于100且小于300,或者大于4000且小于5000的商品
    @Test
    public void price100_5000(){
        findIterable=mongoCollection.find(
                or(and(gt("shop_price", 100), lt("shop_price", 300)),
                   and(gt("shop_price", 4000), lt("shop_price", 5000))
                ));
        show();
    }
    //1.9:取出goods_id%5 == 1, 即,1,6,11,..这样的商品
    @Test
    public void mod_goods(){
        //第一种方法
//        BasicDBObject queryObject = new BasicDBObject().append("goods_id", new BasicDBObject(
//                QueryOperators.MOD, new int[]{5, 1}));
//        findIterable=mongoCollection.find(queryObject);
//        show();
        findIterable=mongoCollection.find(mod("goods_id", 5, 1));
        show();
    }

}
