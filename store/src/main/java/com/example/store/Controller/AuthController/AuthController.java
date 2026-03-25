package com.example.store.Controller.AuthController;

import com.example.store.DTO.Response.ApiResponse;
import com.example.store.DTO.authentification.LoginRequest;
import com.example.store.DTO.authentification.LoginResponse;
import com.example.store.DTO.authentification.UserDTO;
import com.example.store.Model.Authentification.Role;
import com.example.store.Model.Authentification.User;
import com.example.store.Security.jwt.JwtUtil;
import com.example.store.Security.details.CustomUserDetailsService;
import com.example.store.Service.AuthService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private CustomUserDetailsService customUserDetailsService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager
            ,CustomUserDetailsService customUserDetailsService,
                          PasswordEncoder passwordEncoder,
                          UserService userService,
                          JwtUtil jwtUtil){
        this.authenticationManager =authenticationManager;
        this.customUserDetailsService =customUserDetailsService;
        this.passwordEncoder =passwordEncoder;
        this.userService=userService;
        this.jwtUtil=jwtUtil;
    }


    @PostMapping("/signin")
    public LoginResponse authenticateUser(@RequestBody LoginRequest user) {
        Authentication authentication = authenticationManager.authenticate(
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
         String token =jwtUtil.generateToken(userDetails.getUsername());
        User get_user = userService.findByUsername(user.getUsername());
        Role user_Role = get_user.getRole();
         return new LoginResponse(token,user_Role);

    }


    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> register(@RequestBody UserDTO user){
        if (userService.verifyUserExisting(user.getUsername())){
            return ResponseEntity.badRequest().body(new ApiResponse(false,"user Already Exist"));
        }
        userService.register(user);
        return ResponseEntity.ok(new ApiResponse(true,"user created successfully"));
    }
}
