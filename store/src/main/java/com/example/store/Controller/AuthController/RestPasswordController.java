package com.example.store.Controller.AuthController;


import com.example.store.DTO.authentification.EmailRequestDTO;
import com.example.store.Service.AuthService.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/email")
public class RestPasswordController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequestDTO request) {

        String code = emailService.generateCode();

        emailService.sendEmail(
                request.getEmail(),
                "Reset Password",
                "Your code is: " + code
        );

        return "Email sent successfully";
    }
}
