package org.inharmonia.kakilima.base.domain;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
    @Sort(type = SortType.NATURAL)
    private SortedSet<Item> items = new TreeSet<Item>();

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

    public SortedSet<Item> getItems() {
        return items;
    }

    public void setItems(SortedSet<Item> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
