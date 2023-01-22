package com.bridgelabz.BookStoreApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue
    private Integer cartID;
    @OneToOne()
    @JoinColumn(name="userID")
    private User user;
    @ManyToOne()
    @JoinColumn(name="bookID")
    private Book book;
    private Integer quantity;

    public Cart(Integer cartID,Integer quantity, Book book, User user) {
        this.cartID= cartID;
        this.quantity = quantity;
        this.book=book;
        this.user=user;
    }
    public Cart(Integer quantity, Book book, User user) {
        this.quantity = quantity;
        this.book=book;
        this.user=user;
    }
}
