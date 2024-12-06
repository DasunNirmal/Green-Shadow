package lk.ijse.greenshadow.service.impl;

import lk.ijse.greenshadow.dao.UserDao;
import lk.ijse.greenshadow.dto.impl.UserDtoImpl;
import lk.ijse.greenshadow.entity.impl.UserEntity;
import lk.ijse.greenshadow.exception.UserNotFoundException;
import lk.ijse.greenshadow.secure.JWTAuthResponse;
import lk.ijse.greenshadow.secure.SignIn;
import lk.ijse.greenshadow.service.AuthService;
import lk.ijse.greenshadow.service.JWTService;
import lk.ijse.greenshadow.utill.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDao userDao;
    private final JWTService jwtService;
    private final Mapping mapping;
    private final AuthenticationManager authenticationManager;

    @Override
    public JWTAuthResponse signUp(UserDtoImpl userDto) {
        userDto.setPassword(userDto.getPassword());
        UserEntity userEntity = mapping.toUserEntity(userDto);
        if (userDao.existsById(userDto.getEmail())) throw new UserNotFoundException("User Already Exists");
        userDao.save(userEntity);
        var jwtToken = jwtService.generateJwtToken(userEntity);
        return JWTAuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        var userEntity = userDao.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        var jwtToken = jwtService.generateJwtToken(userEntity);
        return JWTAuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
        var userName = jwtService.getUserEmail(accessToken);
        var findUser = userDao.findByEmail(userName).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        var jwtToken = jwtService.generateJwtToken(findUser);
        return JWTAuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
