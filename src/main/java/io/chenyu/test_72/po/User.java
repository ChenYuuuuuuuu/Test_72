package io.chenyu.test_72.po;

public class User {
    private Integer userId;

    private String username;

    private String encrypassword;

    private String salt;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getEncrypassword() {
        return encrypassword;
    }

    public void setEncrypassword(String encrypassword) {
        this.encrypassword = encrypassword == null ? null : encrypassword.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }
}