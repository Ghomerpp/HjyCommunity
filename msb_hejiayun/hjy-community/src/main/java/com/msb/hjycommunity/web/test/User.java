package com.msb.hjycommunity.web.test;

import javax.validation.constraints.NotNull;

/**
 * @author Licyh
 * @version: 1.0
 * @since 2025/11/17 - 11 - 17 - 20:19
 * Description: com.msb.hjycommunity.web.test
 */
public class User {

    @NotNull(message = "userId 不能为空")
    private String userId;

    @NotNull(message = "userName 不能为空")
    private String username;

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
