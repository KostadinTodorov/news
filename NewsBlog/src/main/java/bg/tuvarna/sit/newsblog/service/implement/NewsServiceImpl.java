package bg.tuvarna.sit.newsblog.service.implement;

import bg.tuvarna.sit.newsblog.dto.NewsUpdateCreationDto;
import bg.tuvarna.sit.newsblog.entity.Category;
import bg.tuvarna.sit.newsblog.entity.News;
import bg.tuvarna.sit.newsblog.entity.User;
import bg.tuvarna.sit.newsblog.repository.CategoryRepository;
import bg.tuvarna.sit.newsblog.repository.NewsRepository;
import bg.tuvarna.sit.newsblog.service.interfaces.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public News createNews(NewsUpdateCreationDto dto, User author) {
        Set<Category> categories = categoryRepository.findAllById(dto.getCategoryIds())
                .stream().collect(Collectors.toSet());

        News news = News.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(author)
                .categories(categories)
                .createdAt(LocalDateTime.now())
                .lastModifiedAt(LocalDateTime.now())
                .build();

        return newsRepository.save(news);
    }

    @Override
    public News updateNews(Long id, NewsUpdateCreationDto dto) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found"));

        news.setTitle(dto.getTitle());
        news.setContent(dto.getContent());
        news.setLastModifiedAt(LocalDateTime.now());

        Set<Category> categories = categoryRepository.findAllById(dto.getCategoryIds())
                .stream().collect(Collectors.toSet());
        news.setCategories(categories);

        return newsRepository.save(news);
    }

    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public Optional<News> findById(Long id) {
        return newsRepository.findById(id);
    }
}

