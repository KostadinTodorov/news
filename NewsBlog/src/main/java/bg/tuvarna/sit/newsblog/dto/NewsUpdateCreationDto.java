package bg.tuvarna.sit.newsblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsUpdateCreationDto {
    @NotBlank
    @Size(min = 10, message = "The topic name needs to be at least 10 symbols")
    private String title;

    @NotBlank
    private String content;

    private Set<Long> categoryIds;  // IDs of categories
}