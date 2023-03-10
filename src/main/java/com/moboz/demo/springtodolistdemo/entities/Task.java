package com.moboz.demo.springtodolistdemo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity( name = "task")
@Table(
        name = "task"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Task {
    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    @Column(
            name = "task_id",
            updatable = false
    )
    @Setter(AccessLevel.NONE)
    Long id;

    @Column(
            name = "title",
            nullable = false,
            columnDefinition = "TEXT"
    )
    String title;

    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(
            name = "due_date",
            columnDefinition = "DATE"
    )
    private Date due_date;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    @ToString.Exclude
    private Users user;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<TaskComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<TaskAttachment> attachments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(
            name = "category_id"
    )
    private TaskCategory category;
}
