package javaops.votingsystem.model;

import javaops.votingsystem.HasId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
public class Menu implements HasId {
    @Id
    private Integer id;
    private LocalDate localDate;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "menu")
    private List<Dish> dishes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    public Menu() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", localDate=" + localDate +
                ", dishes=" + dishes +
                '}';
    }
}
