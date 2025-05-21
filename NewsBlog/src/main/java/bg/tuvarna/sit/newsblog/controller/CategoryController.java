package bg.tuvarna.sit.newsblog.controller;

import bg.tuvarna.sit.newsblog.dto.category.CategoryRequestDto;
import bg.tuvarna.sit.newsblog.dto.category.CategoryResponseDto;
import bg.tuvarna.sit.newsblog.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/newsblog")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/private/insert/category")
    public ResponseEntity<CategoryResponseDto> create(@RequestBody CategoryRequestDto dto) {
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/private/update/category/{categoryid}")
    public ResponseEntity<CategoryResponseDto> update(@PathVariable Long categoryid, @RequestBody CategoryRequestDto dto) {
        return ResponseEntity.ok(categoryService.update(categoryid, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/private/delete/category/{categoryid}")
    public ResponseEntity<Void> delete(@PathVariable Long categoryid) {
        categoryService.delete(categoryid);
        return ResponseEntity.noContent().build();
    }
}
