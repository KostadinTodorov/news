package bg.tuvarna.sit.newsblog.controller;

import bg.tuvarna.sit.newsblog.dto.CommentDto;
import bg.tuvarna.sit.newsblog.service.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/news/{newsId}")
    public ResponseEntity<List<CommentDto>> getByNews(@PathVariable Long newsId) {
        return ResponseEntity.ok(commentService.getByNews(newsId));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/news/{newsId}")
    public ResponseEntity<CommentDto> create(
            @PathVariable Long newsId,
            @RequestBody CommentDto dto,
            Authentication authentication
    ) {
        return ResponseEntity.ok(commentService.create(newsId, dto, authentication.getName()));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> update(
            @PathVariable Long id,
            @RequestBody CommentDto dto,
            Authentication authentication
    ) {
        return ResponseEntity.ok(commentService.update(id, dto, authentication.getName()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
