export interface LoginRequest {
  username: string;
  password: string;
}
export interface LoginResponse {
  authenticationToken: string;
  refreshToken: string;
  expiresAt: Date;
  username: string;
}
export interface RefreshTokenRequest {
  refreshToken: string;
  username: string;
}
