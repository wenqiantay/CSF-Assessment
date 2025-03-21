package vttp.batch5.csf.assessment.server.services;

import java.io.StringReader;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.batch5.csf.assessment.server.models.MenuItem;
import vttp.batch5.csf.assessment.server.models.Order;
import vttp.batch5.csf.assessment.server.models.Payment;
import vttp.batch5.csf.assessment.server.models.Receipt;
import vttp.batch5.csf.assessment.server.repositories.OrdersRepository;
import vttp.batch5.csf.assessment.server.repositories.RestaurantRepository;

@Service
public class RestaurantService {

  @Autowired
  private OrdersRepository ordersRepo;

  @Autowired
  private RestaurantRepository restaurantRepo;

  private static final String PAYMENT_URL="https://payment-service-production-a75a.up.railway.app/api/payment";

  // TODO: Task 2.2
  // You may change the method's signature
  public List<MenuItem> getMenu() {

    return ordersRepo.getMenu();
  }
  
  // TODO: Task 4
  public boolean validateCreditential(String username, String password) {

    return restaurantRepo.validateCreditential(username, password);
  }

  public Receipt postPayment(Payment payment, Order order) {

    JsonObject paymentObject = Json.createObjectBuilder()
                              .add("order_id", payment.getOrderId())
                              .add("payer", payment.getPayer())
                              .add("payee", payment.getPayee())
                              .add("payment", payment.getPayment())
                              .build();
    
    RequestEntity<String> req = RequestEntity
                                .post(PAYMENT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("X-Authenticate", payment.getPayer())
                                .body(paymentObject.toString(), String.class);
    
    RestTemplate template = new RestTemplate();

    ResponseEntity<String> resp = template.exchange(req, String.class);

    String payload = resp.getBody();

    JsonReader reader = Json.createReader(new StringReader(payload));
    JsonObject receiptJson = reader.readObject();

    Receipt receipt = new Receipt();
    receipt.setPaymentId(receiptJson.getString("payment_id"));
    receipt.setOrderId(receiptJson.getString("order_id"));
    receipt.setTimestamp(receiptJson.getJsonNumber("timestamp").longValue());

    order.setTimeStamp(new Date(receipt.getTimestamp()));

    //Insert into SQL
    restaurantRepo.insertOrder(receipt, payment.getPayer(), payment.getPayment());

    //Insert into MongoDb
    ordersRepo.insertOrder(order);

    return receipt;
  }

}
