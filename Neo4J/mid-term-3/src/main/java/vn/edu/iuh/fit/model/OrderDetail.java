package vn.edu.iuh.fit.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderDetail {

    private Order oder;
    private Product product;
    private int quantity;
    private double unitPrice;
    private double discount;
    
}
