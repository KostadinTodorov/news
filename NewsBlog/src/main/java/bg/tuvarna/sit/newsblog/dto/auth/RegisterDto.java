package bg.tuvarna.sit.newsblog.dto.auth;

import bg.tuvarna.sit.newsblog.entity.RoleName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String displayName;

    @NotNull
    private RoleName role;
}

