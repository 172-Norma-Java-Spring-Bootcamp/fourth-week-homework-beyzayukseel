package org.patikadev.orderexample.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class CustomerAddress extends BaseModel {

    private String phoneNumber;
    private String country;
    private String city;
    private String postalCode;
    private String description;

    @JsonIgnore
    @OneToOne
    private Customer customer;

}
