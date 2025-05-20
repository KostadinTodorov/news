package bg.tuvarna.sit.newsblog.service.interfaces;

import bg.tuvarna.sit.newsblog.dto.NewsUpdateCreationDto;
import bg.tuvarna.sit.newsblog.entity.News;
import bg.tuvarna.sit.newsblog.entity.User;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    News createNews(NewsUpdateCreationDto dto, User author);
    News updateNews(Long id, NewsUpdateCreationDto dto);
    void deleteNews(Long id);
    List<News> findAll();
    Optional<News> findById(Long id);
}
