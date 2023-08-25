package com.teamz.recipe.service;

import com.teamz.recipe.domain.recipe.RecipeEntity;
import com.teamz.recipe.domain.recipe.RecipeTagMap;
import com.teamz.recipe.domain.recipe.Tag;
import com.teamz.recipe.repository.TagMapRepository;
import com.teamz.recipe.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapRepository tagMapRepository;

    public void createTagList(String tags,RecipeEntity recipe){
        Pattern TAG_PATTEN = Pattern.compile("#(\\S+)");
        Matcher mat = TAG_PATTEN.matcher(tags);
        List<String> tagList = new ArrayList<>();

        while (mat.find()) {
            System.out.println(mat.group(1));
            tagList.add(mat.group(1));
        }

        System.out.println("Create HashTags Success! -----> " + tagList);
        saveTagMapRecipe(tagList, recipe);
    }

    public Boolean saveTagMapRecipe(List<String> tagList, RecipeEntity recipe) {
        Integer result = 1;

        for (String tag : tagList) {
            //DB에서 태그를 검색하여 null이면 태그를 생성, 아니면 검색한 태그
            Tag findResult = tagRepository.findByName(tag).orElseGet(()-> saveTag(tag));

            RecipeTagMap recipeTagMap = RecipeTagMap.builder()
                    .recipe(recipe)
                    .tag(findResult)
                    .build();

            tagMapRepository.save(recipeTagMap);

        }

        return result == 1;
    }

    //태그 테이블에 태그 추가
    public Tag saveTag(String tag){

        Tag newTag = tagRepository.save(Tag.builder()
                .name(tag)
                .build());

        return newTag;
    }

}
