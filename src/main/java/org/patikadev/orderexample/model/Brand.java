package org.patikadev.orderexample.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Brand extends BaseModel {

    private String name;
}
