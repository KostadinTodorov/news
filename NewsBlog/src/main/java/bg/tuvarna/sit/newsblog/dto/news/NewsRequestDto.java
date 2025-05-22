package bg.tuvarna.sit.newsblog.dto.news;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsRequestDto {
    @NotBlank
    @Size(min = 10, message = "The topic name needs to be at least 10 symbols")
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String author;

    @NotEmpty
    private Set<Long> categoryIds;
}