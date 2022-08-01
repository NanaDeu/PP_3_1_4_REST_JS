package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles; //private Set<Role> roles = new HashSet<>();
    //??
    public void addRole(Role role) {
        this.roles.add(role);
    }

/*
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_with_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();
    //private List<Role> roles;
*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(nullable = false, length = 45)
    private String name;
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;
    @Column
    private Byte age;

    @Column(nullable = false, unique = true, length = 45)
    private String username;

    @Column(nullable = false, length = 45)
    private String password;
    //private String email;

    public User() {

    }

    public User(List<Role> roles, String name, String lastName, Byte age, String username, String password) {
        this.roles = roles;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.password = password;
    }
/*    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }*/

    @Override
    public String toString() {
        return "User{" +
                "roles=" + roles +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

}
