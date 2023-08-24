package com.teamz.recipe.repository;

import com.teamz.recipe.domain.recipe.CookOrderImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CookOrderImageRepository extends JpaRepository<CookOrderImage, String> {
}
