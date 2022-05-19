////////////////////////////////////////////////////////////////////
// Alessandro Cavaliere 1224440
// Filippo Brugnolaro 1217321
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class EItemTest {
    private EItem mouse, keyboard;

    @Before
    public void setup() {
        mouse = new EItem("Logitech mouse XXX", EItemType.MOUSE, 15.00);
        keyboard = new EItem("Logitech keyboard YYY", EItemType.KEYBOARD, 20.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullNameTest() {
        new EItem(null, EItemType.KEYBOARD, 15.00);
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullTypeTest() {
        new EItem("???", null, 15.00);
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNegativePriceTest() {
        new EItem("???", EItemType.KEYBOARD, -15.00);
        fail();
    }

    @Test
    public void priceGetterTest() {
        assert mouse.getPrice() == 15.00;
        assert keyboard.getPrice() == 20.00;
    }
}
