package com.zhouchao.geekbang.projects.user.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Author zhouchao
 * @Date 2021/3/3 20:48
 * @Description
 **/
@Data
@ToString
@EqualsAndHashCode
public class User {
    private Long id;

    private String name;

    private String password;

    private String email;

    private String phoneNumber;
}
