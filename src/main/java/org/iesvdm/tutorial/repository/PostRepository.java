package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
