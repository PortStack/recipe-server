package com.teamz.recipe.global.modules;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageInfo {
    private int page;
    private int size;
    private int totalElements;
    private int totalPages;
}
