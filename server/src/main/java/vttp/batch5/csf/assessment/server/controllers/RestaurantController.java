package vttp.batch5.csf.assessment.server.controllers;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import vttp.batch5.csf.assessment.server.models.MenuItem;
import vttp.batch5.csf.assessment.server.models.Order;
import vttp.batch5.csf.assessment.server.models.OrderItem;
import vttp.batch5.csf.assessment.server.models.Payment;
import vttp.batch5.csf.assessment.server.models.Receipt;
import vttp.batch5.csf.assessment.server.services.RestaurantService;

@RestController
@RequestMapping("/api")
public class RestaurantController {

  @Autowired
  private RestaurantService restaurantSvc;

  // TODO: Task 2.2
  // You may change the method's signature
  @GetMapping("/menu")
  public ResponseEntity<String> getMenus() {
    List<MenuItem> menuList = restaurantSvc.getMenu();

    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    for (MenuItem m : menuList) {
      
      arrayBuilder.add(m.toJson());
    }

    JsonArray menuItemArray = arrayBuilder.build();
    System.out.println(menuItemArray.toString());

    return ResponseEntity.ok(menuItemArray.toString());
  }

  // TODO: Task 4
  // Do not change the method's signature
  @PostMapping("/food_order")
  public ResponseEntity<?> postFoodOrder(@RequestBody String payload) {

    JsonReader reader = Json.createReader(new StringReader(payload));
    JsonObject orderObj = reader.readObject();
    String username = orderObj.getString("username");
    String password = orderObj.getString("password");
    System.out.println(username);
    System.out.println(password);

    boolean isValidUser = restaurantSvc.validateCreditential(username, password);

    // Validate User Creditential
    if(!isValidUser) {
      JsonObjectBuilder builder = Json.createObjectBuilder()
                                  .add("message", "Invalid username and/or password");
      JsonObject errorMessage = builder.build();

      return ResponseEntity.status(401).body(errorMessage.toString());
    }

    //Place order
    String orderId = UUID.randomUUID().toString().substring(0,8);
    Order newOrder = new Order();
    newOrder.setOrderId(orderId);
    newOrder.setUsername(username);
    double totalAmount = 0;
    JsonArray itemArray = orderObj.getJsonArray("items");
    List<OrderItem> orderItems = new LinkedList<>();
    
    for (int i = 0; i < itemArray.size(); i++) {
      OrderItem orderItem = new OrderItem();

      JsonObject item = itemArray.getJsonObject(i);
      orderItem.setId(item.getString("id"));

      double itemPrice = item.getJsonNumber("price").doubleValue();
      orderItem.setPrice(itemPrice);

      int quantity = item.getInt("quantity");
      orderItem.setQuantity(quantity);

      totalAmount += itemPrice * quantity;
      orderItems.add(orderItem);
    }

    newOrder.setTotal(totalAmount);
    newOrder.setItems(orderItems);
    
    
    Payment payment = new Payment();
    payment.setOrderId(orderId);
    payment.setPayer(username);
    payment.setPayee("Tay Wenqian");
    payment.setPayment(totalAmount);
  
    // Get receipt from API
    Receipt receipt = this.restaurantSvc.postPayment(payment, newOrder);
    receipt.setTotal(totalAmount);
    System.out.println(receipt.toString());

    return ResponseEntity.ok(receipt);
  }
}
