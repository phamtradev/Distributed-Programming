package vn.edu.iuh.fit.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Product {

    private String productID;
    private String productName;
    private String unit;
    private double unitPrice;
    private int unitsInstock;

    private Supplier supplier;
    private List<OrderDetail> orderDetails;

}
