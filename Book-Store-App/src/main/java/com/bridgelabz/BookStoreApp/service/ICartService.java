package com.bridgelabz.BookStoreApp.service;

import com.bridgelabz.BookStoreApp.dto.CartDTO;
import com.bridgelabz.BookStoreApp.entity.Cart;

import java.util.List;

public interface ICartService {
    public Cart insertCart(CartDTO cartdto);
    public List<Cart> getAllCartRecords();
    public Cart getCartRecord(Integer id);
    public Cart updateCartRecord(Integer id, CartDTO dto);
    public Cart deleteCartRecord(Integer id);
    public Cart updateQuantity(Integer id, Integer quantity);
    public List<Cart> deleteAllFromCart();

    public Cart updateCartRecordByUserID(Integer id, Integer quantity);
}