////////////////////////////////////////////////////////////////////
// Alessandro Cavaliere 1224440
// Filippo Brugnolaro 1217321
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.model;

import org.junit.Before;
import org.junit.Test;

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
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullTypeTest() {
        new EItem("???", null, 15.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNegativePriceTest() {
        new EItem("???", EItemType.KEYBOARD, -15.00);
    }

    @Test
    public void priceGetterTest() {
        assert mouse.getPrice() == 15.00;
        assert keyboard.getPrice() == 20.00;
    }

    @Test
    public void priceSetterTest() {
        mouse.setPrice(5.00);
        keyboard.setPrice(90.00);

        assert mouse.getPrice() == 5.00;
        assert keyboard.getPrice() == 90.00;
    }

    @Test
    public void typeGetterTest() {
        assert mouse.getEItemType() == EItemType.MOUSE;
        assert keyboard.getEItemType() == EItemType.KEYBOARD;
    }

}