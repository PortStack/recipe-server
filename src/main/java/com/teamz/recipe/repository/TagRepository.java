package com.teamz.recipe.repository;

import com.teamz.recipe.domain.CommentEntity;
import com.teamz.recipe.domain.Tag;
import com.teamz.recipe.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}
