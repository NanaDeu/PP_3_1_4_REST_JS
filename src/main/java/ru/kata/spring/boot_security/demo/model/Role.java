package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return getName();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column             //    @Column(name = "id", nullable = false, updatable= false)
    private long id;

    @Column(nullable = false, length = 45)
    @NotNull
    //@Enumerated(EnumType.STRING)
    private String name;

/*    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;*/

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }
    public Role(long id) {
        this.id = id;
    }

    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
