package bg.tuvarna.sit.newsblog.service.interfaces;

import bg.tuvarna.sit.newsblog.dto.comment.CommentRequestDto;
import bg.tuvarna.sit.newsblog.dto.comment.CommentResponseDto;
import bg.tuvarna.sit.newsblog.entity.Comment;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CommentService {
    List<CommentResponseDto> getAllComments();
    List<CommentResponseDto> getCommentsByNews(Long newsId);
    CommentResponseDto createComment(Long newsId, CommentRequestDto dto, UserDetails userDetails);
    CommentResponseDto updateComment(Long commentid, CommentRequestDto dto, UserDetails userDetails);
    void deleteComment(Long id);
}

