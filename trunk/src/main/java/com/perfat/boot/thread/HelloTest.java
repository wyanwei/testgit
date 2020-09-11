/*
 * Chsi
 * Created on 2020-08-10
 */
package com.perfat.boot.thread;

/**
 * @author wangyw <a href="mailto:wangyw@chsi.com.cn">WangYanWei</a>
 * @version $Id$
 */
public class HelloTest {
    private String username = "hello";
    private String password = "world";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void say() {
        System.out.println("say Hello World");
    }
}
