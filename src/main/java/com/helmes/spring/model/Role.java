package com.helmes.spring.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="role")
public class Role {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String role;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
