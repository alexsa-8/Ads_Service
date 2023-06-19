package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.constant.Role;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

/**
 * Servic AuthServiceImpl
 * Service for entering a name and password
 * @see UserDetailsManager
 * @see PasswordEncoder
 * @see UserRepository
 * @see UserMapper
 * @author Kilikova Anna
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserDetailsManager manager;

  private final PasswordEncoder encoder;

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  /**
   * The method returns the username used for authentication
   *
   * @param userName user name
   * @param password user password
   * @return returns the user's password
   */
  @Override
  public boolean login(String userName, String password) {
    UserDetails userDetails = manager.loadUserByUsername(userName);
    return encoder.matches(password, userDetails.getPassword());
  }

  /**
   * The method determines whether the user's value is present in the register
   *
   * @param registerReq registration
   * @param role role
   * @return false - if the value is missing
   * @return true - if the value is present
   */
  @Override
  public boolean register(RegisterReq registerReq, Role role) {
    if (userRepository.findByEmailIgnoreCase(registerReq.getUsername()).isPresent()) {
      return false;
    }
    User regUser = userMapper.toEntity(registerReq);
    regUser.setRole(role);
    regUser.setPassword(encoder.encode(regUser.getPassword()));
    userRepository.save(regUser);
    return true;
  }

}