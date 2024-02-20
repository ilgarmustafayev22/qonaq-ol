package az.qonaqol.qonaqol.service;

import az.qonaqol.qonaqol.dao.entity.UserEntity;
import az.qonaqol.qonaqol.model.dto.UserDto;
import az.qonaqol.qonaqol.model.request.SignupRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {

    UserDto findUserByUsername(String email);

    void deleteUser(Long id);
    //TODO: Cascading deletion
    //TODO: Password Reconfirmation

}
