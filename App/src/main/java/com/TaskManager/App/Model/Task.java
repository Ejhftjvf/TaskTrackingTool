package com.TaskManager.App.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "Tasks")
public class Task {
    @Id
    private String id;
    private String title;
    private String details;
    @DBRef
    private List<User> userAssigned;
    private boolean status;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate deadLine;
}
