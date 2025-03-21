package vttp.batch5.csf.assessment.server.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp.batch5.csf.assessment.server.models.MenuItem;
import vttp.batch5.csf.assessment.server.models.Order;


@Repository
public class OrdersRepository {

  @Autowired
  private MongoTemplate mongoTemplate;
  private final String COLLECTION_NAME="menus";

  // TODO: Task 2.2
  // You may change the method's signature
  // Write the native MongoDB query in the comment below
  //
  //  Native MongoDB query here
  // db.menus.find().sort({name:1})
  public List<MenuItem> getMenu() {
    Query q = new Query().with(Sort.by(Sort.Direction.ASC, "name"));
    List<MenuItem> menuItems = mongoTemplate.find(q, MenuItem.class, COLLECTION_NAME);
    return menuItems;
  }

  // TODO: Task 4
  // Write the native MongoDB query for your access methods in the comment below
  //
  //  Native MongoDB query here
  // db.orders.insert({
  //    "_id" : "abcd1234",
  //    "order_id": "abccd1234",
  //    "username": "fred", "total": 23.10,
  //    "timestamp" : <date>,
  //    "items" : [ 
  //        {"id": "XXX", "price": "xxx", "quantity": 2},
  //        {"id": "XXX", "price": "xxx", "quantity": 2}
  //    ]
  // })
  public void insertOrder(Order order){

    mongoTemplate.insert(order, "orders");
  }
}
