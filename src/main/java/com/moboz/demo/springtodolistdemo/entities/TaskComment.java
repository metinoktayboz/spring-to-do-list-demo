package com.moboz.demo.springtodolistdemo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity( name = "task_comment")
@Table(
        name = "task_comment"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TaskComment {
    @Id
    @SequenceGenerator(
            name = "task_comment_sequence",
            sequenceName = "task_comment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_comment_sequence"
    )
    @Column(
            name = "comment_id",
            updatable = false
    )
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    @ToString.Exclude
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private Users user;

    @Column(name = "comment_text")
    private String commentText;

    @Column(name = "created_at")
    private Date createdAt;
}
