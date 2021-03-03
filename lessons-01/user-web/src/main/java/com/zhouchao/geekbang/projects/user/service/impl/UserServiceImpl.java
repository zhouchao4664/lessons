package com.zhouchao.geekbang.projects.user.service.impl;

import com.zhouchao.geekbang.projects.user.domain.User;
import com.zhouchao.geekbang.projects.user.repository.DatabaseUserRepository;
import com.zhouchao.geekbang.projects.user.repository.UserRepository;
import com.zhouchao.geekbang.projects.user.service.UserService;
import com.zhouchao.geekbang.projects.user.sql.DBConnectionManager;

/**
 * @Author zhouchao
 * @Date 2021/3/3 21:28
 * @Description
 **/

public class UserServiceImpl implements UserService {
    @Override
    public boolean register(User user) {
        UserRepository userRepository = new DatabaseUserRepository(new DBConnectionManager());
        return userRepository.save(user);
    }
}
