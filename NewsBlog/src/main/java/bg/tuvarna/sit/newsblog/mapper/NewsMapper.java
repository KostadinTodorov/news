package bg.tuvarna.sit.newsblog.mapper;

import bg.tuvarna.sit.newsblog.dto.news.NewsRequestDto;
import bg.tuvarna.sit.newsblog.dto.news.NewsResponseDto;
import bg.tuvarna.sit.newsblog.entity.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NewsMapper {
    private final CategoryMapper categoryMapper;
    private final CommentMapper commentMapper;

    public NewsResponseDto toDto(News news) {
        if (news == null) return null;

        NewsResponseDto dto = new NewsResponseDto();
        //dto.setId(news.getId());
        dto.setTitle(news.getTitle());
        dto.setContent(news.getContent());
        dto.setAuthor(news.getAuthor());
        dto.setCreatedAt(news.getCreatedAt());
        dto.setLastModifiedAt(news.getLastModifiedAt());

        if (news.getCategories() != null) {
            dto.setCategories(
                    news.getCategories().stream()
                            .map(categoryMapper::toDto)
                            .collect(Collectors.toSet())
            );
        }

//        if (news.getComments() != null) {
//            dto.setComments(
//                    news.getComments().stream()
//                            .map(commentMapper::toDto)
//                            .collect(Collectors.toSet())
//            );
//        }

        return dto;
    }

    public News toEntity(NewsRequestDto dto) {
        News news = new News();
        news.setTitle(dto.getTitle());
        news.setAuthor(dto.getAuthor());
        news.setContent(dto.getContent());
        return news;
    }
}
