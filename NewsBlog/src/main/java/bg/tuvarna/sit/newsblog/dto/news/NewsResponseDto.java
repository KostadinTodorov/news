package bg.tuvarna.sit.newsblog.dto.news;

import bg.tuvarna.sit.newsblog.dto.category.CategoryResponseDto;
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
    @Size(min = 10, message = "The topic name needs to be at least 10 symbols")
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String authorUsername;

    @NotBlank
    private Set<CategoryResponseDto> categories;

}