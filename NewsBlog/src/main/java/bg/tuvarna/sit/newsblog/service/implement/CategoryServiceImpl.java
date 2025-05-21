package bg.tuvarna.sit.newsblog.service.implement;

import bg.tuvarna.sit.newsblog.dto.category.CategoryRequestDto;
import bg.tuvarna.sit.newsblog.dto.category.CategoryResponseDto;
import bg.tuvarna.sit.newsblog.entity.Category;
import bg.tuvarna.sit.newsblog.exception.ResourceNotFoundException;
import bg.tuvarna.sit.newsblog.mapper.CategoryMapper;
import bg.tuvarna.sit.newsblog.repository.CategoryRepository;
import bg.tuvarna.sit.newsblog.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDto create(CategoryRequestDto dto) {
        if (categoryRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Category already exists");
        }
        else {
            Category category = categoryMapper.toEntity(dto);
            categoryRepository.save(category);
            return categoryMapper.toDto(category);
        }
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryRequestDto dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "ID "+id));

        category.setName(dto.getName());
        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}

