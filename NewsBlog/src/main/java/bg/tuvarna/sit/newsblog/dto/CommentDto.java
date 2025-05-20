package bg.tuvarna.sit.newsblog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String authorUsername;

    private LocalDateTime createdAt;
}