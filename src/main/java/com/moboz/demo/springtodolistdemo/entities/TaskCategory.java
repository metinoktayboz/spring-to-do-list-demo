package com.moboz.demo.springtodolistdemo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity( name = "task_category")
@Table(
        name = "task_category"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TaskCategory {
    @Id
    @SequenceGenerator(
            name = "task_category_sequence",
            sequenceName = "task_category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_category_sequence"
    )
    @Column(
            name = "category_id",
            updatable = false
    )
    @Setter(AccessLevel.NONE)
    Long id;

    @Column(
            name = "category_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String categoryName;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<Task> tasks;

}
