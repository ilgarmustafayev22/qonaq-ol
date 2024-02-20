package az.qonaqol.qonaqol.service.impl;

import az.qonaqol.qonaqol.dao.entity.UserEntity;
import az.qonaqol.qonaqol.dao.repository.UserRepository;
import az.qonaqol.qonaqol.exception.UserNotFoundException;
import az.qonaqol.qonaqol.mapper.UserMapper;
import az.qonaqol.qonaqol.model.dto.UserDto;
import az.qonaqol.qonaqol.model.request.SignupRequest;
import az.qonaqol.qonaqol.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
   // private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto findUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException("User not found with email " + email));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
