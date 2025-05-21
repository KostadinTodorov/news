package bg.tuvarna.sit.newsblog.mapper;

import bg.tuvarna.sit.newsblog.dto.category.CategoryRequestDto;
import bg.tuvarna.sit.newsblog.dto.category.CategoryResponseDto;
import bg.tuvarna.sit.newsblog.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final CustomMapper mapper;

    public CategoryResponseDto toDto(Category category) {
        return mapper.map(category, CategoryResponseDto.class);
    }

    public Category toEntity(CategoryRequestDto dto) {
        return mapper.map(dto, Category.class);
    }
}

