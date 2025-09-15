package org.example;

public class User {
    private final Integer user_id;
    private final String name;

    public User(Integer user_id, String name) {
        this.user_id = user_id;
        this.name = name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                '}';
    }
}
