package bg.tuvarna.sit.newsblog.service.implement;

import bg.tuvarna.sit.newsblog.dto.CommentUpdateCreationDto;
import bg.tuvarna.sit.newsblog.entity.Comment;
import bg.tuvarna.sit.newsblog.entity.News;
import bg.tuvarna.sit.newsblog.entity.User;
import bg.tuvarna.sit.newsblog.repository.CommentRepository;
import bg.tuvarna.sit.newsblog.repository.NewsRepository;
import bg.tuvarna.sit.newsblog.repository.UserRepository;
import bg.tuvarna.sit.newsblog.service.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    @Override
    public Comment createComment(CommentUpdateCreationDto dto, String username, Long newsId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("News not found"));

        Comment comment = Comment.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(user)
                .news(news)
                .createdAt(LocalDateTime.now())
                .build();

        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long id, CommentUpdateCreationDto dto, String username) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("You are not allowed to edit this comment");
        }

        comment.setTitle(dto.getTitle());
        comment.setContent(dto.getContent());

        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getCommentsByNewsId(Long newsId) {
        return commentRepository.findByNewsId(newsId);
    }
}

