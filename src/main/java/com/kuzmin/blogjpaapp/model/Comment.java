package com.kuzmin.blogjpaapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private Long postId;
    private Long parentId;
    private String content;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    Post post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comment comment = (Comment) o;
        return id != null && Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
