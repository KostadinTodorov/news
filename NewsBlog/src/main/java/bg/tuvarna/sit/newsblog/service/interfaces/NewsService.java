package bg.tuvarna.sit.newsblog.service.interfaces;

import bg.tuvarna.sit.newsblog.dto.news.NewsRequestDto;
import bg.tuvarna.sit.newsblog.dto.news.NewsResponseDto;
import bg.tuvarna.sit.newsblog.entity.News;
import bg.tuvarna.sit.newsblog.entity.User;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    NewsResponseDto createNews(Long categoryId, NewsRequestDto request);
    NewsResponseDto updateNews(Long newsId, NewsRequestDto request);
    void deleteNews(Long id);
    List<NewsResponseDto> findAll();
    NewsResponseDto findById(Long id);
}
