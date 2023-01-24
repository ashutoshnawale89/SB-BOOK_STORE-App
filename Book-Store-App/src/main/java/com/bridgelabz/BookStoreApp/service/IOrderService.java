package com.bridgelabz.BookStoreApp.service;

import com.bridgelabz.BookStoreApp.dto.OrderDTO;
import com.bridgelabz.BookStoreApp.entity.Order;

import java.util.List;

//Interface to achieve abstraction
public interface IOrderService {
    public Order insertOrder(OrderDTO orderdto);

    public List<Order> getAllOrderRecords();

    public Order getOrderRecord(Integer id);

    public Order updateOrderRecord(Integer id,OrderDTO dto);

    public Order deleteOrderRecord(Integer id);


}