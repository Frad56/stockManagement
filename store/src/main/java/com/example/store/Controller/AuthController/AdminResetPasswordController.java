package com.example.store.Controller.AuthController;




import com.example.store.DTO.authentification.ResetPasswordRequest;
import com.example.store.Service.AuthService.AdminSettings.adminResetPasswordService.interfaces.AdminResetPasswordService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth/admin")
public class AdminResetPasswordController {


  public final AdminResetPasswordService adminResetPasswordService;

  public AdminResetPasswordController(AdminResetPasswordService adminResetPasswordService){
      this.adminResetPasswordService =adminResetPasswordService;
  }

  //POST /auth/verify-password

//   @PostMapping("/verify-password")
//   public ResponseEntity<String> verifyPassword(){
//
//   }

  @PutMapping("/reset-password")
  public ResponseEntity<String> adminResetPassword(@RequestBody ResetPasswordRequest request){
      adminResetPasswordService.resetPassword(request);
      return ResponseEntity.ok("Password changed successfully.");
  }




}
