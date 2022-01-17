package com.kuzmin.blogjpaapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @org.hibernate.annotations.Type(type = "pg-uuid")
    UUID guid;
    private String name;
    private String about;
    private LocalDateTime publishedAt;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            orphanRemoval = true, mappedBy = "blog")
    @ToString.Exclude
    private Set<Post> posts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Blog blog = (Blog) o;
        return id != null && Objects.equals(id, blog.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
