////////////////////////////////////////////////////////////////////
// Alessandro Cavaliere 1224440
// Filippo Brugnolaro 1217321
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.business;

import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

import java.time.LocalTime;
import java.util.List;

public class Order {

    private final LocalTime orderTime;
    private final User user;
    private final List<EItem> itemList;
    private final double totalPrice;

    public Order(LocalTime orderTime, User user,
                 List<EItem> itemList, double price) {

        if(orderTime == null) {
            throw new IllegalArgumentException("Order time must not be null");
        }

        if(user == null) {
            throw new IllegalArgumentException("User must not be null");
        }

        if(itemList == null) {
            throw new IllegalArgumentException("Item list must not be null");
        }

        this.orderTime = orderTime;
        this.user = user;
        this.itemList = itemList;
        this.totalPrice = price;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public User getUser() {
        return user;
    }

    public static LocalTime startFreeOrderTime = LocalTime.of(18,0,0);
    public static LocalTime endFreeOrderTime = LocalTime.of(19,0,0);

}
