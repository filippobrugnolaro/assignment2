////////////////////////////////////////////////////////////////////
// Alessandro Cavaliere 1224440
// Filippo Brugnolaro 1217321
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.business;

import it.unipd.mtss.model.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class OrderTest {
    private Order genericOrder;
    private final User genericUser = new User("CF", "Mario", "Rossi", "mario.rossi@email.com", LocalDate.now());

    @Before
    public void setup() {
        genericOrder = new Order(
                LocalTime.MIDNIGHT,
                genericUser,
                new ArrayList<>(),
                0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullOrderTimeTest() {
        new Order(null,
                genericUser,
                new ArrayList<>(),
                0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullUserTest() {
        new Order(LocalTime.MIDNIGHT,
                null,
                new ArrayList<>(),
                0);
    }



    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullOrderListTest() {
        new Order(LocalTime.MIDNIGHT,
                genericUser,
                null,
                0);
    }

    @Test
    public void userGetterTest() {
        assert genericOrder.getUser() == genericUser;
    }

    @Test
    public void orderTimeGetterTest() {
        assert genericOrder.getOrderTime() == LocalTime.MIDNIGHT;
    }
}
