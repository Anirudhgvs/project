package com.springdemo.project.DTO;



import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskDTO {

    private String title;
    private String description;
    private Date dueDate;
    private String assignedTo;
    private String createdBy;
}
