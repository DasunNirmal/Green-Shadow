package lk.ijse.greenshadow.service;

import lk.ijse.greenshadow.dto.impl.UserDtoImpl;
import lk.ijse.greenshadow.secure.JWTAuthResponse;
import lk.ijse.greenshadow.secure.SignIn;

public interface AuthService {
    JWTAuthResponse signUp(UserDtoImpl userDto);
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse refreshToken(String existingToken);
}
