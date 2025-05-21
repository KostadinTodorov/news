package bg.tuvarna.sit.newsblog.mapper;

import bg.tuvarna.sit.newsblog.dto.news.NewsRequestDto;
import bg.tuvarna.sit.newsblog.dto.news.NewsResponseDto;
import bg.tuvarna.sit.newsblog.entity.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewsMapper {
    private final CustomMapper mapper;

    public NewsResponseDto toDto(News news) {
        return mapper.map(news, NewsResponseDto.class);
    }

    public News toEntity(NewsRequestDto dto) {
        return mapper.map(dto, News.class);
    }
}
