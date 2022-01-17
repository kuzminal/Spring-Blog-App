package com.kuzmin.blogjpaapp.repository;

import com.kuzmin.blogjpaapp.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}
