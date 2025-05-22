package bg.tuvarna.sit.newsblog.repository;

import bg.tuvarna.sit.newsblog.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @Query("SELECT DISTINCT n FROM News n LEFT JOIN FETCH n.categories")
    List<News> findAllWithCategories();

//    @Query("SELECT DISTINCT n FROM News n " +
//            "LEFT JOIN FETCH n.categories " +
//            "LEFT JOIN FETCH n.comments")
//    List<News> findAllWithCategoriesAndComments();

}

