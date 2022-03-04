package com.betepawa.pawaTask2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pawa_tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "day")
    private  String day;

    @NotNull
    @Column(name = "month")
    private String month;

    @NotNull
    @Column(name = "year")
    private String year;



    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date Date;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "edited_by")
    private String lastEditedBy;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<Comment> comments;

    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "userTaskId",
            referencedColumnName = "id"
    )
    private User user;




    public void addComment(Comment comment) {
        this.comments.add(comment);
    }


}