import { Component, Inject, inject } from '@angular/core';
import { Location } from '@angular/common';
import { AdminResetEmailService } from '../../../../../auth/service/adminResetEmail/admin-reset-email.service';
import { EmailServiceService } from '../../../../../auth/service/emailService/email-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-admin-change-password',
  standalone: true,
  imports: [],
  templateUrl: './admin-change-password.component.html',
  styleUrl: './admin-change-password.component.css'
})
export class AdminChangePasswordComponent {

  location = inject(Location);
  private adminCahngePasswordService=Inject(AdminChangePasswordComponent);
  private adminEmailService = inject(AdminResetEmailService);
  private emailService = inject(EmailServiceService);

  email!: string;
  loading = false;
  codeSent = false;

  ngOnInit(){
    this.adminEmailService.getMyEmail().subscribe({  
      next: (res) => {
        console.log('Current email:', res.email);
        this.email = res.email;
      },
      error: (error) => {
        console.error('Error fetching current email:', error);
      }
    });
  }

  private sendCodeRequest(newEmail: string) {
    return this.emailService.sendCode({ newEmail });
  }

  onSendCode(){
      this.loading = true;

        this.sendCodeRequest(this.email).subscribe({
        next: (res) => {

          console.log('Email sent', res.message);
          //this.step = 2; 
          this.loading = false;
        },
        error: (error) => {
          console.error(error)
          Swal.fire({
            icon: 'error',
            title: 'Error',
            text: error.error?.message || 'Failed to send code. Please try again'
          });
          
          this.loading = false;}
        
      });
  }

  onResendCode(){
    this.sendCodeRequest(this.email).subscribe({
      next: (res) => {
        console.log('Code resent', res.message);
        Swal.fire({
          text: 'Verification code resent. Please check your email'
        });
      },
      error: (error) => {
        console.error(error)
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: error.error?.message || 'Failed to resend code. Please try again'
        });
      }
    });
  }
  goBack(){
    this.location.back();
  }
}
