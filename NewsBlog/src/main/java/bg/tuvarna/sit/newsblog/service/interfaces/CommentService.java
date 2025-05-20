package bg.tuvarna.sit.newsblog.service.interfaces;

import bg.tuvarna.sit.newsblog.dto.CommentUpdateCreationDto;
import bg.tuvarna.sit.newsblog.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(CommentUpdateCreationDto dto, String username, Long newsId);
    Comment updateComment(Long id, CommentUpdateCreationDto dto, String username);
    void deleteComment(Long id);
    List<Comment> getCommentsByNewsId(Long newsId);
}

