package vn.edu.iuh.fit.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Order {

    private String orderId;
    private LocalDate orderDate;
    private String customerName;
    private String employeeName;
    private Status status;

}
