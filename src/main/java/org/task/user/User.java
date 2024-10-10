package org.task.user;

import lombok.Data;
@Data
public class User {
    public Long id;
    public String userName;

    public User(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public User() {
    }
}
