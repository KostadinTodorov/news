package bg.tuvarna.sit.newsblog.controller;

import bg.tuvarna.sit.newsblog.dto.news.NewsResponseDto;
import bg.tuvarna.sit.newsblog.entity.News;
import bg.tuvarna.sit.newsblog.service.interfaces.NewsService;
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

//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping
//    public ResponseEntity<NewsResponseDto> createNews(@RequestBody NewsResponseDto dto) {
//        return ResponseEntity.ok(newsService.create(dto));
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @PutMapping("/{id}")
//    public ResponseEntity<NewsResponseDto> updateNews(@PathVariable Long id, @RequestBody NewsResponseDto dto) {
//        return ResponseEntity.ok(newsService.update(id, dto));
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
//        newsService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}

