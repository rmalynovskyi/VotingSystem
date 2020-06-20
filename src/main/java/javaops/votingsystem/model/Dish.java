package javaops.votingsystem.model;

import javaops.votingsystem.HasId;

import javax.persistence.*;

@Entity
public class Dish implements HasId {
    @Id
    private Integer id;
    private String name;
    private Integer price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id",nullable = false)
    private Menu menu;

    public Dish() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
