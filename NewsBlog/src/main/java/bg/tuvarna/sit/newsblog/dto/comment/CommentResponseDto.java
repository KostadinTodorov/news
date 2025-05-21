package bg.tuvarna.sit.newsblog.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String authorDisplayName;

    @NotBlank
    private Long newsId;

    @NotBlank
    private LocalDateTime createdAt;
}