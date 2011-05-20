package org.inharmonia.kakilima.base.domain;

import org.apache.commons.collections.ComparatorUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;

@Entity
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @Column(length = 1024)
    private String description;
    private boolean negotiable;
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Store store;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Category category;

    public Item() {
    }

    public Item(String name, String description, boolean negotiable, Double price, Category category, Store store) {
        this.name = name;
        this.description = description;
        this.negotiable = negotiable;
        this.price = price;
        this.category = category;
        this.store = store;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isNegotiable() {
        return negotiable;
    }

    public void setNegotiable(boolean negotiable) {
        this.negotiable = negotiable;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static class Comparator implements java.util.Comparator<Item>, Serializable {

        public Comparator() {
        }

        @Override
        public int compare(Item o1, Item o2) {
            return o1.getName().compareTo(o2.getName());
        }
        
    }
}
