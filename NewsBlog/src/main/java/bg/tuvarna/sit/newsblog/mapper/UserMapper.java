package bg.tuvarna.sit.newsblog.mapper;

import bg.tuvarna.sit.newsblog.dto.auth.RegisterDto;
import bg.tuvarna.sit.newsblog.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public User toEntity(RegisterDto dto) {
        return modelMapper.map(dto, User.class);
    }
}

