package bg.tuvarna.sit.newsblog.controller;

import bg.tuvarna.sit.newsblog.dto.news.NewsRequestDto;
import bg.tuvarna.sit.newsblog.dto.news.NewsResponseDto;
import bg.tuvarna.sit.newsblog.entity.News;
import bg.tuvarna.sit.newsblog.service.interfaces.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/newsblog")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/public/news")
    public ResponseEntity<List<NewsResponseDto>> getAllNews() {
        return ResponseEntity.ok(newsService.findAll());
    }

    @GetMapping("/public/news/{newsid}")
    public ResponseEntity<NewsResponseDto> getNewsById(@PathVariable Long newsid) {
        return ResponseEntity.ok(newsService.findById(newsid));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/private/insert/category/{categoryId}/news")
    public ResponseEntity<NewsResponseDto> createNews( @PathVariable Long categoryId,
                                                       @RequestBody @Valid NewsRequestDto request) {
        return ResponseEntity.ok(newsService.createNews(categoryId, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/private/update/category/news{newsid}")
    public ResponseEntity<NewsResponseDto> updateNews(@PathVariable Long newsid, @RequestBody @Valid NewsRequestDto request) {
        return ResponseEntity.ok(newsService.updateNews(newsid, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/private/delete/news{newsid}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long newsid) {
        newsService.deleteNews(newsid);
        return ResponseEntity.noContent().build();
    }
}

