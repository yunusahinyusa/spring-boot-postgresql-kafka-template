package com.ysahin.spring.boot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ApiS")
public class ApiS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "apiMethod",length = 200)
    private String apiMethod;

    @Column(name = "responseTime",length = 200)
    private String responseTime;

    @Column(name = "timestamp",length = 200)
    private String timestamp;

}
