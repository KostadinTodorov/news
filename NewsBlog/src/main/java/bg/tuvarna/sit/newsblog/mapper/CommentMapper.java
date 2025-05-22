package bg.tuvarna.sit.newsblog.mapper;

import bg.tuvarna.sit.newsblog.dto.comment.CommentRequestDto;
import bg.tuvarna.sit.newsblog.dto.comment.CommentResponseDto;
import bg.tuvarna.sit.newsblog.entity.Comment;
import bg.tuvarna.sit.newsblog.entity.News;
import bg.tuvarna.sit.newsblog.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    private final CustomMapper mapper;
    private final CategoryMapper categoryMapper;

    public CommentResponseDto toDto(Comment comment) {
        CommentResponseDto dto = new CommentResponseDto();
        dto.setTitle(comment.getTitle());
        dto.setContent(comment.getContent());
        dto.setAuthorDisplayName(comment.getUser().getDisplayName());

        if (comment.getNews() != null) {
            dto.setNewsId(comment.getNews().getId());
            dto.setTitle(comment.getNews().getTitle());
            dto.setCategories(comment.getNews().getCategories().stream()
                    .map(categoryMapper::toDto)
                    .collect(Collectors.toSet()));
            dto.setNewsContent(comment.getNews().getContent());
        }

        return dto;
    }

    public Comment toEntity(CommentRequestDto dto) {Comment comment = new Comment();
        comment.setTitle(dto.getTitle());
        comment.setContent(dto.getContent());
        return comment;
    }

    public void enrichComment(Comment comment, News news, User user) {
        comment.setNews(news);
        comment.setUser(user);
        //comment.setCreatedAt(LocalDateTime.now());
    }
}
