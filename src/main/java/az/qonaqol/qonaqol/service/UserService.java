package az.qonaqol.qonaqol.service;

import az.qonaqol.qonaqol.dao.entity.UserEntity;
import az.qonaqol.qonaqol.model.dto.UserDto;
import az.qonaqol.qonaqol.model.request.SignupRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto findUserByUsername(String email);

    List<UserDto> findAllUsers();

    UserDto findById(Long id);

    UserDto save(UserDto userDto);

    void deleteUser(Long id);

}
