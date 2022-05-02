package org.patikadev.orderexample.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Customer extends BaseExtendedModel {

    private String username;
    private String email;
    private Long identity;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String password;
    private Boolean isDeleted;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private CustomerAddress customerAddress;

    @OneToMany(mappedBy = "customer", orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;

    public void addOrder(Order order) {
        orders.add(order);
    }
}
