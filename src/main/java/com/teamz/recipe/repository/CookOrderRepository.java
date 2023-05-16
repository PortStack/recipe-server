package com.teamz.recipe.repository;

import com.teamz.recipe.domain.CookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CookOrderRepository extends JpaRepository<CookOrder, String> {
}
