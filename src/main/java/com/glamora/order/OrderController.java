package com.glamora.order;

import com.glamora.auth.AuthController;
import com.glamora.auth.AuthService;
import com.glamora.auth.Customer;
import com.glamora.auth.CustomerRepository;
import com.glamora.product.Product;
import com.glamora.product.ProductRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.*;

@RestController @RequestMapping("/api/orders")
public class OrderController {
 private final AuthService auth; private final CustomerRepository customers; private final ProductRepository products; private final CustomerOrderRepository orders;
 public OrderController(AuthService a,CustomerRepository c,ProductRepository p,CustomerOrderRepository o){auth=a;customers=c;products=p;orders=o;}
 public record ItemRequest(@NotNull Long productId,@NotNull @Min(1) @Max(20) Integer quantity){}
 public record PlaceOrderRequest(@NotBlank @Size(max=100) String customerName,@NotBlank @Size(max=300) String deliveryAddress,@NotBlank @Size(max=30) String phone,@NotEmpty @Size(max=30) List<@Valid ItemRequest> items){}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) @Transactional
 public CustomerOrder place(@RequestHeader(value="Authorization",required=false) String header,@Valid @RequestBody PlaceOrderRequest req){
  String email=auth.emailFromToken(AuthController.token(header)); Customer customer=customers.findByEmailIgnoreCase(email).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Please log in again."));
  CustomerOrder order=new CustomerOrder();order.setCustomerId(customer.getId());order.setCustomerEmail(customer.getEmail());order.setCustomerName(req.customerName().trim());order.setDeliveryAddress(req.deliveryAddress().trim());order.setPhone(req.phone().trim());
  BigDecimal subtotal=BigDecimal.ZERO; Set<Long> seen=new HashSet<>();
  for(ItemRequest item:req.items()){
   if(!seen.add(item.productId())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Remove duplicate product lines from the bag.");
   Product product=products.findById(item.productId()).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"A product in your bag no longer exists."));
   if(product.getStock()==null||product.getStock()<item.quantity()) throw new ResponseStatusException(HttpStatus.CONFLICT,"Not enough stock for "+product.getName()+".");
   product.setStock(product.getStock()-item.quantity()); products.save(product); subtotal=subtotal.add(product.getPrice().multiply(BigDecimal.valueOf(item.quantity()))); order.addItem(new OrderItem(product.getId(),product.getName(),item.quantity(),product.getPrice()));
  }
  BigDecimal delivery=subtotal.compareTo(new BigDecimal("999"))>=0?BigDecimal.ZERO:new BigDecimal("49.00");order.setSubtotal(subtotal);order.setDeliveryFee(delivery);order.setTotal(subtotal.add(delivery));return orders.save(order);
 }
 @GetMapping("/my") @Transactional(readOnly=true)
 public List<CustomerOrder> myOrders(@RequestHeader(value="Authorization",required=false) String header){String email=auth.emailFromToken(AuthController.token(header));Customer c=customers.findByEmailIgnoreCase(email).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Please log in again."));return orders.findByCustomerIdOrderByCreatedAtDesc(c.getId());}
}
