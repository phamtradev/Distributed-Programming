package vn.edu.iuh.fit.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {

    private String street;
    private String city;
    private String state;
    private String zip;
}
