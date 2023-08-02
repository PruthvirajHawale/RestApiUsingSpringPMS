package com.mycompany.projectmanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="PROPERTY_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class PropertyEntity {

    @Id  // makes this column as primary key column
    @GeneratedValue(strategy = GenerationType.AUTO) //automatically generate value for this field according to strategy in db
    private long id;
    @Column(name = "PROPERTY_TITLE",nullable = false)
    private String title;
    private String description;
    private String ownerName;
    private String onwerEmail;
    private Double price;
    private String address;


}
