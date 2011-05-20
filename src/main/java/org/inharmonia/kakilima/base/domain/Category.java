package org.inharmonia.kakilima.base.domain;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    @Sort(type= SortType.COMPARATOR, comparator = Comparator.class)
    private SortedSet<Category> children = new TreeSet<Category>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    @Sort(type= SortType.COMPARATOR, comparator = Item.Comparator.class)
    private SortedSet<Item> items = new TreeSet<Item>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
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

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public SortedSet<Category> getChildren() {
        return children;
    }

    public void setChildren(SortedSet<Category> children) {
        this.children = children;
    }

    public SortedSet<Item> getItems() {
        return items;
    }

    public void setItems(SortedSet<Item> items) {
        this.items = items;
    }

    public void addItem(Item item){
        if(item == null){
            throw new IllegalArgumentException("item cannot be null");
        }
        items.add(item);
    }

    public boolean isLeaf(){
        return getChildren().isEmpty();
    }

    public static class Comparator implements java.util.Comparator<Category>, Serializable {

        public Comparator() {
        }

        @Override
        public int compare(Category o1, Category o2) {
            return o1.getName().compareTo(o2.getName());        
        }
    }
}
