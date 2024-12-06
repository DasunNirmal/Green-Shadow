package lk.ijse.greenshadow.dto.impl;
import lk.ijse.greenshadow.dto.UserStatus;

import lk.ijse.greenshadow.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDtoImpl implements UserStatus {
    private Role role;
    private String email;
    private String password;
}
