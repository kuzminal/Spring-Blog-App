package com.kuzmin.blogjpaapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kuzmin.blogjpaapp.model.enums.PostStatus;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @Column(name = "post_status")
    @Enumerated(EnumType.STRING)
    PostStatus postStatus;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "blog_id")
    @ToString.Exclude
    private Blog blog;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private User user;
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_files", joinColumns = {
            @JoinColumn(name = "post_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "file_id", referencedColumnName = "id")})
    private Set<File> files;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Post post = (Post) o;
        return id != null && Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
