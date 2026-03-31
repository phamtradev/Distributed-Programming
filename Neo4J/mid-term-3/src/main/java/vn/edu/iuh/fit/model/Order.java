package vn.edu.iuh.fit.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Order {

    private String orderID;
    private LocalDate orderDate;
    private String customerName;
    private Status status;

    private List<OrderDetail> orderDetails;
    
}
