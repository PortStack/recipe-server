package com.teamz.recipe.repository;

import com.teamz.recipe.domain.recipe.CookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CookOrderRepository extends JpaRepository<CookOrder, String> {
}
