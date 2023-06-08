package com.teamz.recipe.repository;

import com.teamz.recipe.domain.SearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<SearchEntity, Long> {
}
