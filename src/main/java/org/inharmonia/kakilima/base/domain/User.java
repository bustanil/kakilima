package org.inharmonia.kakilima.base.domain;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email") )
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String email;
    private String fullname;
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Sort(type= SortType.NATURAL)
    private SortedSet<Store> stores = new TreeSet<Store>();

    public User() {
    }

    public User(String email, String fullname, String password) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SortedSet<Store> getStores() {
        return stores;
    }

    public void setStores(SortedSet<Store> stores) {
        this.stores = stores;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
