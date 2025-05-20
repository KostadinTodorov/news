package bg.tuvarna.sit.newsblog.dto;

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
public class NewsDto {
    private Long id;

    @NotBlank
    @Size(min = 10, message = "The topic name needs to be at least 10 symbols")
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String authorUsername;

    private Set<String> categories;

    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
}