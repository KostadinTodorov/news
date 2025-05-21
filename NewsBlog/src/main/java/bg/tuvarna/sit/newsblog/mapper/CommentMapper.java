package bg.tuvarna.sit.newsblog.mapper;

import bg.tuvarna.sit.newsblog.dto.comment.CommentRequestDto;
import bg.tuvarna.sit.newsblog.dto.comment.CommentResponseDto;
import bg.tuvarna.sit.newsblog.entity.Comment;
import bg.tuvarna.sit.newsblog.entity.News;
import bg.tuvarna.sit.newsblog.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    private final CustomMapper mapper;

    public CommentResponseDto toDto(Comment comment) {
        return mapper.map(comment, CommentResponseDto.class);
    }

    public Comment toEntity(CommentRequestDto dto) {
        return mapper.map(dto, Comment.class);
    }

    public void enrichComment(Comment comment, News news, User user) {
        comment.setNews(news);
        comment.setAuthor(user);
        comment.setCreatedAt(LocalDateTime.now());
    }
}
