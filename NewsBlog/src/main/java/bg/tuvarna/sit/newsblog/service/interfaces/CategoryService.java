package bg.tuvarna.sit.newsblog.service.interfaces;

import bg.tuvarna.sit.newsblog.dto.CategoryDto;
import bg.tuvarna.sit.newsblog.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category create(CategoryDto dto);
    Category update(Long id, CategoryDto dto);
    void delete(Long id);
}

