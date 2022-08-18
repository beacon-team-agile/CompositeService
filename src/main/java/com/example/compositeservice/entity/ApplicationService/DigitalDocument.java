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

public class DigitalDocument implements Serializable {

    private Integer id;

    private String type;

    private boolean is_required;

    private String path;

    private String description;

    private String title;

}
