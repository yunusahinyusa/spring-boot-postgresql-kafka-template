package com.ysahin.spring.boot.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "ApiS")
public class ApiS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "apiMethod",length = 200)
    private String apiMethod;

    @Column(name = "responseTime",length = 200)
    private String responseTime;

    @Column(name = "timestamp",length = 200)
    private String timestamp;

}
