package vn.edu.iuh.fit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {

    private String street;
    private String city;
    private String state;
    private String zip;
}
