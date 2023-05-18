package com.teamz.recipe.service;

import com.teamz.recipe.repository.CookOrderImageRepository;
import com.teamz.recipe.repository.CookOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CookOrderService {

    private final CookOrderRepository cookOrderRepository;
    private final CookOrderImageRepository cookOrderImageRepository;
}
