package bg.tuvarna.sit.newsblog.mapper;

import bg.tuvarna.sit.newsblog.dto.category.CategoryRequestDto;
import bg.tuvarna.sit.newsblog.dto.category.CategoryResponseDto;
import bg.tuvarna.sit.newsblog.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    public CategoryResponseDto toDto(Category category) {
        if (category == null) return null;

        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    public Category toEntity(CategoryRequestDto dto) {
        if (dto == null) return null;

        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}

