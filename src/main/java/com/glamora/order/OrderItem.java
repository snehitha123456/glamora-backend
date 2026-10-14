package com.glamora.order;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity @Table(name="order_items")
public class OrderItem {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="order_id",nullable=false) private CustomerOrder order;
 @Column(nullable=false) private Long productId;
 @Column(nullable=false,length=180) private String productName;
 @Column(nullable=false) private Integer quantity;
 @Column(nullable=false,precision=10,scale=2) private BigDecimal unitPrice;
 @Column(nullable=false,precision=10,scale=2) private BigDecimal lineTotal;
 public OrderItem(){}
 public OrderItem(Long productId,String productName,Integer quantity,BigDecimal unitPrice){this.productId=productId;this.productName=productName;this.quantity=quantity;this.unitPrice=unitPrice;this.lineTotal=unitPrice.multiply(BigDecimal.valueOf(quantity));}
 public void setOrder(CustomerOrder v){order=v;} public Long getId(){return id;} public Long getProductId(){return productId;} public String getProductName(){return productName;} public Integer getQuantity(){return quantity;} public BigDecimal getUnitPrice(){return unitPrice;} public BigDecimal getLineTotal(){return lineTotal;}
}
