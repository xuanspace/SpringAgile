package com.agile.framework.entity;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginUser {

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    @NotEmpty
    private String captcha;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
