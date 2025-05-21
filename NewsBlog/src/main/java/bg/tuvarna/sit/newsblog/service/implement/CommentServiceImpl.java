package bg.tuvarna.sit.newsblog.service.implement;

import bg.tuvarna.sit.newsblog.dto.comment.CommentRequestDto;
import bg.tuvarna.sit.newsblog.dto.comment.CommentResponseDto;
import bg.tuvarna.sit.newsblog.entity.Comment;
import bg.tuvarna.sit.newsblog.entity.News;
import bg.tuvarna.sit.newsblog.entity.User;
import bg.tuvarna.sit.newsblog.exception.ResourceNotFoundException;
import bg.tuvarna.sit.newsblog.mapper.CategoryMapper;
import bg.tuvarna.sit.newsblog.mapper.CommentMapper;
import bg.tuvarna.sit.newsblog.repository.CommentRepository;
import bg.tuvarna.sit.newsblog.repository.NewsRepository;
import bg.tuvarna.sit.newsblog.repository.UserRepository;
import bg.tuvarna.sit.newsblog.service.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    public List<CommentResponseDto> getAllComments() {
        return commentRepository.findAll().stream()
                .map(comment -> commentMapper.toDto(comment))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentResponseDto> getCommentsByNews(Long newsId) {
        return commentRepository.findByNewsId(newsId).stream()
                .map(comment -> commentMapper.toDto(comment))
                .collect(Collectors.toList());
    }

    @Override
    public CommentResponseDto createComment(Long newsId, CommentRequestDto dto, UserDetails userDetails) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(()-> new ResourceNotFoundException("News", "id "+newsId));

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(()-> new ResourceNotFoundException("User", "name "+userDetails.getUsername()));

        Comment comment = commentMapper.toEntity(dto);
        commentMapper.enrichComment(comment, news, user);

        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Override
    public CommentResponseDto updateComment(Long id, CommentRequestDto dto, UserDetails userDetails) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Comment", "id "+id));

        if (!comment.getAuthor().getUsername().equals(userDetails.getUsername())) {
            throw new RuntimeException("You are not allowed to edit this comment");
        }

        comment.setTitle(dto.getTitle());
        comment.setContent(dto.getContent());

        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comment", "id "+id);
        }
        commentRepository.deleteById(id);
    }
}

