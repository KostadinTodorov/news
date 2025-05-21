package bg.tuvarna.sit.newsblog.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer";
}

