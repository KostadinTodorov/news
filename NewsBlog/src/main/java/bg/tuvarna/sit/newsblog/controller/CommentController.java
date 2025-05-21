package bg.tuvarna.sit.newsblog.controller;

import bg.tuvarna.sit.newsblog.dto.comment.CommentRequestDto;
import bg.tuvarna.sit.newsblog.dto.comment.CommentResponseDto;
import bg.tuvarna.sit.newsblog.service.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/newsblog")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/public/comments")
    public ResponseEntity<List<CommentResponseDto>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/public/news/comments/{newsid}")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByNews(@PathVariable Long newsid) {
        return ResponseEntity.ok(commentService.getCommentsByNews(newsid));
    }

    @PreAuthorize("hasRole('COMMENTATOR')")
    @PostMapping("/private/insert/comment/news/{newsid}")
    public ResponseEntity<CommentResponseDto> addComment(
            @PathVariable Long newsid,
            @RequestBody CommentRequestDto dto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(commentService.createComment(newsid, dto, userDetails));
    }

    @PreAuthorize("hasRole('COMMENTATOR')")
    @PutMapping("/private/update/comment{commentid}")
    public ResponseEntity<CommentResponseDto> update(
            @PathVariable Long commentid,
            @RequestBody CommentRequestDto dto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(commentService.updateComment(commentid, dto, userDetails));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/private/delete/comment/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
