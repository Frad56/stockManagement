export interface ResetPasswordRequest{
   email: String;
   oldPassword: String;
   newPassword: String;
   code: String;
}