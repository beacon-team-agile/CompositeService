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
public class ApplicationWorkFlow implements Serializable {

    private Integer id;

    private String employee_id;

    private String create_date;

    private String last_modification_date;

    private boolean status;

    private String comment;
}
