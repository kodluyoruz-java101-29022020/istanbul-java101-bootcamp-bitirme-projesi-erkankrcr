package com.erkankrcr.mylibraryproject.repostory.model;

import java.util.UUID;

/**
 * For The Glory Of Machine
 * ╔════════════════════════════╗
 * ║  Created by Erkan Karacar  ║
 * ╠════════════════════════════╣
 * ║ erkankrcr@outlook.com.tr   ║
 * ╠════════════════════════════╣
 * ║     01/05/2020 - 13:58     ║
 * ╚════════════════════════════╝
 */

public class User {

    private UUID id;

    private String username;

    private String password;

    private String name;

    private String lastname;

    public User() {
    }

    public User(UUID uuid ,String username ,String password ,String name ,String lastname){
        this.id = uuid;
        this.username = username;
        this.lastname = lastname;
        this.name = name;
        this.password = password;
    }

    public User(String username , String password , String name , String lastname){
        this.id = UUID.randomUUID();
        this.username = username;
        this.lastname = lastname;
        this.name = name;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID userId) {
        this.id = userId;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}

