package bg.tuvarna.sit.newsblog.repository;

import bg.tuvarna.sit.newsblog.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByCategories_Name(String categoryName); // filter by category
    List<News> findByAuthor_Username(String username);
}

