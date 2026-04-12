import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { ResetEmailRequest } from '../../auth/ResetEmailRequest';
import { UserResponse } from '../../auth/UserResponse';
import { ResetPasswordRequest } from '../../auth/ResetPasswordRequest';

@Injectable({
  providedIn: 'root'
})
export class AdminResetPasswordService {

  private readonly API = 'http://localhost:8080/api/v1/auth/admin';
  private http = inject(HttpClient);



  adminResetPassword(req:ResetPasswordRequest){
    return this.http.post(`${this.API}/reset-password`, req);
  }

}
