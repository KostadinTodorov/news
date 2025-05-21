package bg.tuvarna.sit.newsblog.service.implement;

import bg.tuvarna.sit.newsblog.dto.news.NewsRequestDto;
import bg.tuvarna.sit.newsblog.dto.news.NewsResponseDto;
import bg.tuvarna.sit.newsblog.entity.Category;
import bg.tuvarna.sit.newsblog.entity.News;
import bg.tuvarna.sit.newsblog.entity.User;
import bg.tuvarna.sit.newsblog.exception.ResourceNotFoundException;
import bg.tuvarna.sit.newsblog.mapper.NewsMapper;
import bg.tuvarna.sit.newsblog.repository.CategoryRepository;
import bg.tuvarna.sit.newsblog.repository.NewsRepository;
import bg.tuvarna.sit.newsblog.service.interfaces.NewsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final NewsServiceImpl userRepository;
    private final NewsMapper newsMapper;

    @Override
    public NewsResponseDto createNews(Long categoryId, NewsRequestDto dto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id "+categoryId));

        News news = newsMapper.toEntity(dto);
        news.setAuthor(dto.getAuthor());
        news.setCreatedAt(LocalDateTime.now());
        news.setCategories(Set.of(category));

        newsRepository.save(news);

        return newsMapper.toDto(news);
    }

    @Override
    public NewsResponseDto updateNews(Long id, NewsRequestDto dto) {
        News news = newsRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("News", "id "+id));


        Set<Category> categories = categoryRepository.findAllById(dto.getCategoryIds())
                .stream().collect(Collectors.toSet());

        news.setCategories(categories);
        news.setTitle(dto.getTitle());
        news.setContent(dto.getContent());
        news.setLastModifiedAt(LocalDateTime.now());


        return newsMapper.toDto(newsRepository.save(news));
    }

    @Override
    public void deleteNews(Long id) {
        if (!newsRepository.existsById(id)) {
            throw new ResourceNotFoundException("News", "id "+id);
        }
        newsRepository.deleteById(id);
    }

    @Override
    public List<NewsResponseDto> findAll() {
        return newsRepository.findAll().stream()
                .map(news -> newsMapper.toDto(news))
                .collect(Collectors.toList());
    }

    @Override
    public NewsResponseDto findById(Long id) {
        Optional<News> news = newsRepository.findById(id);
        return newsMapper.toDto(news.orElse(null));
    }
}

