package com.example.compositeservice.entity.ApplicationService;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "ApplicationWorkFlow")
public class ApplicationWorkFlow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "employee_id")
    private String employee_id;

    @Column(name = "create_date")
    private String create_date;

    @Column(name = "last_modification_date")
    private String last_modification_date;

    @Column(name = "status")
    private boolean status;

    @Column(name = "comment")
    private String comment;
}
