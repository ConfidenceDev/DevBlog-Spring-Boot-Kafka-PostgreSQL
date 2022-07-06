package com.confidencedev.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DevBlog {

    @Id
    @SequenceGenerator(
            name = "devblog_id_sequence",
            sequenceName = "devblog_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "devblog_id_sequence"
    )
    private Integer id;
    private String creator;
    private String title;
    private String description;
    private LocalDateTime created;

    public DevBlog(String creator, String title,
                   String description, LocalDateTime created) {
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.created = created;
    }
}
