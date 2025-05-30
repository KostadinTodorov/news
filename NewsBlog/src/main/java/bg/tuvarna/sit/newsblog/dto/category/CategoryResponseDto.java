package bg.tuvarna.sit.newsblog.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {

    @NotBlank
    private Long id;

    @NotBlank
    private String name;
}
