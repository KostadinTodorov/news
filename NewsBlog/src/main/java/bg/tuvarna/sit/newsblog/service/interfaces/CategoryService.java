package bg.tuvarna.sit.newsblog.service.interfaces;

import bg.tuvarna.sit.newsblog.dto.category.CategoryRequestDto;
import bg.tuvarna.sit.newsblog.dto.category.CategoryResponseDto;
import bg.tuvarna.sit.newsblog.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto create(CategoryRequestDto dto);
    CategoryResponseDto update(Long id, CategoryRequestDto dto);
    void delete(Long id);
}

