package org.patikadev.orderexample.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Setter
@Getter
@Entity
public class Coupon extends BaseModel {
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private String code;
    private int quantity;
    private int amount;
}
