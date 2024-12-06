package lk.ijse.greenshadow.controller;

import lk.ijse.greenshadow.dto.impl.UserDtoImpl;
import lk.ijse.greenshadow.entity.Role;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.secure.JWTAuthResponse;
import lk.ijse.greenshadow.secure.SignIn;
import lk.ijse.greenshadow.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth/")
public class AuthController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> saveUser(@RequestPart("role") String role, @RequestPart ("email") String email,
            @RequestPart ("password") String password) {
        try {
            var buildUserDTO = new UserDtoImpl();
            buildUserDTO.setRole(Role.valueOf(role));
            buildUserDTO.setEmail(email);
            buildUserDTO.setPassword(passwordEncoder.encode(password));
            return ResponseEntity.ok(authService.signUp(buildUserDTO));
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "signIn",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn signIn){
        return ResponseEntity.ok(authService.signIn(signIn));
    }
    @PostMapping("refresh")
    public ResponseEntity<JWTAuthResponse> refreshToken(@RequestParam ("existingToken") String existingToken) {
        return ResponseEntity.ok(authService.refreshToken(existingToken));
    }
}
