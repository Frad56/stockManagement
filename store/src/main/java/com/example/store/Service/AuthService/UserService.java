package com.example.store.Service.AuthService;

import com.example.store.DTO.authentification.UserDTO;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.Authentification.Role;
import com.example.store.Model.Authentification.User;
import com.example.store.Repository.AuthRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository =userRepository;
        this.passwordEncoder =passwordEncoder;
    }

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(
                user -> {
                    UserDTO dto = new UserDTO();
                    dto.setId(user.getId());
                    dto.setEmail(user.getEmail());
                    dto.setRole(user.getRole());
                    return dto;
                }).collect(Collectors.toList());
    }

    public void register(UserDTO user_request){
        if(userRepository.findByUsername(user_request.getUsername()).isPresent()){
            throw new RuntimeException("User already exist!");
        }
        User user = new User();
        user.setEmail(user_request.getEmail());
        user.setUsername(user_request.getUsername());
        user.setPassword(passwordEncoder.encode(user_request.getPassword()));
        user.setRole(user_request.getRole());

        userRepository.save(user);
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ElementNotFoundException(username));
    }

    public Boolean verifyUserExisting(String username){
        return userRepository.findByUsername(username).isPresent();
    }



}
