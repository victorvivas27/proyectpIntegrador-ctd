package com.musichouse.api.music.interfaces;

import com.musichouse.api.music.dto.dto_entrance.CategoryDtoEntrance;
import com.musichouse.api.music.dto.dto_exit.CategoryDtoExit;
import com.musichouse.api.music.dto.dto_modify.CategoryDtoModify;
import com.musichouse.api.music.exception.ResourceNotFoundException;

import java.util.List;

public interface CategoryInterface {
    CategoryDtoExit createCategory(CategoryDtoEntrance categoryDtoEntrance);

    List<CategoryDtoExit> getAllCategories();

    CategoryDtoExit getCategoryById(Long idCategory) throws ResourceNotFoundException;

    CategoryDtoExit updateCategory(CategoryDtoModify categoryDtoModify) throws ResourceNotFoundException;

    void deleteCategory(Long idCategory) throws ResourceNotFoundException;

}
