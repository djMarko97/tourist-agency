package com.example.touristagency.requests.authority;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Base user requests
 * @author djMarko97
 */
public abstract class BaseUserRequest {

    /** Username **/
    @NotBlank @Email
    private String username;

    /** Password **/
    @NotBlank
    private String password;

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
}
