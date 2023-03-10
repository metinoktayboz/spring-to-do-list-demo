package com.moboz.demo.springtodolistdemo.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Entity( name = "task_attachment")
@Table(
        name = "task_attachment"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TaskAttachment {
    @Id
    @SequenceGenerator(
            name = "task_attachment_sequence",
            sequenceName = "task_attachment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_attachment_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "task_id"
    )
    @ToString.Exclude
    private Task task;

    @Column(
            name = "file_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String fileName;

    @Column(
            name = "content_type",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String contentType;

    @Column(
            name = "data",
            columnDefinition = "bytea"
    )
    private byte[] data;
}
