package com.zhouchao.geekbang.projects.user.service;

import com.zhouchao.geekbang.projects.user.domain.User;

/**
 * @Author zhouchao
 * @Date 2021/3/3 21:27
 * @Description
 **/

public interface UserService {

    /**
     * 注册用户
     *
     * @param user 用户对象
     * @return 成功返回<code>true</code>
     */
    boolean register(User user);
}
