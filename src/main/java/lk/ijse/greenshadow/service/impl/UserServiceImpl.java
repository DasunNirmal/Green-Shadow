package lk.ijse.greenshadow.service.impl;

import lk.ijse.greenshadow.dao.UserDao;
import lk.ijse.greenshadow.exception.UserNotFoundException;
import lk.ijse.greenshadow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetailsService userDetailsService() {
        return userName ->
                userDao.findByEmail(userName)
                        .orElseThrow(()-> new UserNotFoundException("User Not Found"));
    }
}
