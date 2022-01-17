package com.kuzmin.blogjpaapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kuzmin.blogjpaapp.model.enums.PostStatus;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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
    @Enumerated(EnumType.ORDINAL) // Use this otherwise to store it as integer
                    PostStatus postStatus;
                    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType. REFRESH)
                    @JoinColumn(name = "blog_id")
                    @ToString.Exclude
                    private Blog blog;
                    @JsonIgnore
                    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
                    @JoinColumn(name = "author_id")
                    @ToString.Exclude
                    private User user;

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
