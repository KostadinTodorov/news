package bg.tuvarna.sit.newsblog.dto.user;

import bg.tuvarna.sit.newsblog.entity.RoleName;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryDto {
    @NotBlank
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String displayName;

    @NotBlank
    private RoleName role;
}
