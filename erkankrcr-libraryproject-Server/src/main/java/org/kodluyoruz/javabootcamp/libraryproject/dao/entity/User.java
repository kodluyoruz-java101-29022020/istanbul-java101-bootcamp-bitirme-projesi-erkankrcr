package org.kodluyoruz.javabootcamp.libraryproject.dao.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user",schema = "public")
public class User {
    @Id
    @Column(name = "user_id")
    @Type(type="pg-uuid")
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(lastname, user.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, name, lastname);
    }
}
