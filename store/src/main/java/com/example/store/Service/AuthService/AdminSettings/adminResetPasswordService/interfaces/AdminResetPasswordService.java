package com.example.store.Service.AuthService.AdminSettings.adminResetPasswordService.interfaces;

import com.example.store.DTO.authentification.ResetPasswordRequest;
import com.example.store.Model.Authentification.Role;

public interface AdminResetPasswordService {

    boolean isAdmin(Role role);
    void resetPassword(ResetPasswordRequest passwordRequest);

}
