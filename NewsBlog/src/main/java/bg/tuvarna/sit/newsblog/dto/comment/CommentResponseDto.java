package bg.tuvarna.sit.newsblog.dto.comment;

import bg.tuvarna.sit.newsblog.dto.category.CategoryResponseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private Set<CategoryResponseDto> categories;

    @NotBlank
    private String newsContent;

    @NotBlank
    private String authorDisplayName;

    @NotBlank
    private Long newsId;

    @NotBlank
    private LocalDateTime createdAt;
}