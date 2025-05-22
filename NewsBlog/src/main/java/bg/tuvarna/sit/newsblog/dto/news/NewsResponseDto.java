package bg.tuvarna.sit.newsblog.dto.news;

import bg.tuvarna.sit.newsblog.dto.category.CategoryResponseDto;
import bg.tuvarna.sit.newsblog.dto.comment.CommentResponseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponseDto {
    @NotBlank
    private Long id;
    @NotBlank
    @Size(min = 10, message = "The topic name needs to be at least 10 symbols")
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String author;

    @NotBlank
    private LocalDateTime createdAt;

    @NotBlank
    private LocalDateTime lastModifiedAt;

    @NotBlank
    private Set<CategoryResponseDto> categories;

    @NotBlank
    private Set<CommentResponseDto> comments;

}