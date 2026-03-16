package vn.edu.iuh.fit.model.people;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {

    private String streetAddress;
    private String city;
    private String state;
    private int postalCode;
}
