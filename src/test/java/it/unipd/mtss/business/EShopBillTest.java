////////////////////////////////////////////////////////////////////
// Alessandro Cavaliere 1224440
// Filippo Brugnolaro 1217321
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.EItemType;
import it.unipd.mtss.model.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.fail;

public class EShopBillTest {
    private EShopBill bill;
    private final User user = new User("TEST", "Test", "Testing", "test@test.com", LocalDate.of(2000, 2, 26));

    @Before
    public void before() {
        bill = new EShopBill();
    }

    @Test(expected = BillException.class)
    public void EmptyOrderTest() throws BillException {
        // Arrange
        List<EItem> itemList = new LinkedList<>();

        // Act
        bill.getOrderPrice(itemList, user);

        // Assert
        fail();
    }

    @Test
    public void nonEmptyOrderTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();
        EItem item1 = new EItem("Logitech xxx", EItemType.MOUSE, 50.00);
        for(int i = 0; i < 2; i++) {
            items.add(item1);
        }

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 100.00;
    }

    @Test
    public void lessThanFiveProcessorDiscountOrderTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();
        items.add(new EItem("Amd xxx", EItemType.PROCESSOR, 10.00));
        items.add(new EItem("Amd xxx", EItemType.PROCESSOR, 20.00));
        items.add(new EItem("Amd xxx", EItemType.PROCESSOR, 30.00));
        items.add(new EItem("Amd xxx", EItemType.PROCESSOR, 40.00));
        items.add(new EItem("Amd xxx", EItemType.PROCESSOR, 50.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 60.00));

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 210.00;
    }

    @Test
    public void moreThanFiveProcessorDiscountOrderTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();
        items.add(new EItem("Amd xxx", EItemType.PROCESSOR, 10.00));
        items.add(new EItem("Amd xxx", EItemType.PROCESSOR, 20.00));
        items.add(new EItem("Amd xxx", EItemType.PROCESSOR, 30.00));
        items.add(new EItem("Amd xxx", EItemType.PROCESSOR, 40.00));
        items.add(new EItem("Amd xxx", EItemType.PROCESSOR, 50.00));
        items.add(new EItem("Amd xxx", EItemType.PROCESSOR, 60.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 70.00));

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 280.00 - 5.00;
    }

    @Test
    public void moreThanFiveEqualProcessorDiscountOrderTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();

        for(int i = 0; i < 6; i++) {
            items.add(new EItem("Amd xxx", EItemType.PROCESSOR, 60.00));
        }

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 360.00 - 30.00;
    }

    @Test
    public void lessThanTenMouseOrderTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 5.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 6.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 7.00));
        items.add(new EItem("Asus Sabertooth xxx", EItemType.MOTHERBOARD, 130.00));
        items.add(new EItem("Intel i5 xxx", EItemType.PROCESSOR, 73.41));

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 221.41;
    }

    @Test
    public void moreThanTenMouseOrderTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();

        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 5.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 6.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 7.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 5.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 6.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 7.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 5.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 6.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 7.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 5.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 6.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 7.00));

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 67.00;
    }

    @Test
    public void combinedMouseAndProcessorTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();

        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 5.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 6.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 7.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 5.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 6.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 7.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 5.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 6.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 7.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 5.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 6.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 7.00));

        for(int i = 0; i < 6; i++) {
            items.add(new EItem("Amd xxx", EItemType.PROCESSOR, 60.00));
        }

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 360 - 30 + 72 - 5;
    }

    @Test
    public void numberMouseEqualsKeyboardGreaterThanZeroDiscountTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();

        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 10.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 20.00));
        items.add(new EItem("Asus Sabertooth xxx", EItemType.MOTHERBOARD, 20.00));
        items.add(new EItem("Asus Sabertooth xxx", EItemType.MOTHERBOARD, 20.00));
        items.add(new EItem("Asus Sabertooth xxx", EItemType.MOTHERBOARD, 20.00));
        items.add(new EItem("MSI xxx", EItemType.KEYBOARD, 30.00));
        items.add(new EItem("MSI xxx", EItemType.KEYBOARD, 50.00));

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 160.00;
    }

    @Test
    public void numberMouseEqualsKeyboardEqualZeroDiscountTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();
        items.add(new EItem("Asus Sabertooth xxx", EItemType.MOTHERBOARD, 100.00));
        items.add(new EItem("Asus Sabertooth xxx", EItemType.MOTHERBOARD, 100.00));
        items.add(new EItem("Asus Sabertooth xxx", EItemType.MOTHERBOARD, 100.00));

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 300.00;
    }

    @Test
    public void numberMouseKeyboardAllEqualsTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            items.add(new EItem("Logitech keyboard xxx", EItemType.KEYBOARD, 15.00));
            items.add(new EItem("Super Logitech Mouse xxx", EItemType.MOUSE, 15.00));
        }

        // Act
        double total = bill.getOrderPrice(items, user);
        
        // Assert
        assert total == 90.00 - 15.00;
    }

    @Test
    public void numberMouseDiffersKeyboardDiscountTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();

        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 10.00));
        items.add(new EItem("Logitech xxx", EItemType.MOUSE, 20.00));
        items.add(new EItem("Asus Sabertooth xxx", EItemType.MOTHERBOARD, 100.00));
        items.add(new EItem("Asus Sabertooth xxx", EItemType.MOTHERBOARD, 100.00));
        items.add(new EItem("Asus Sabertooth xxx", EItemType.MOTHERBOARD, 100.00));
        items.add(new EItem("Asus Sabertooth xxx", EItemType.KEYBOARD, 30.00));
        items.add(new EItem("Asus Sabertooth xxx", EItemType.KEYBOARD, 40.00));
        items.add(new EItem("Asus Sabertooth xxx", EItemType.KEYBOARD, 50.00));

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 450.00;
    }

    @Test
    public void mouseDiscountAndMouseEqualsKeyboardDiscountOrderTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();

        for (int i = 0; i < 14; i++)
            items.add(new EItem("Logitech M185", EItemType.MOUSE, 15.00));
        for (int i = 0; i < 14; i++)
            items.add(new EItem("Asus Sabertooth xxx", EItemType.KEYBOARD, 20.00));

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 490.00 - 15.00;
    }

    @Test
    public void combinedMouseAndProcessorAndEqualsMouseKeyboardDiscountTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();

        for (int i = 0; i < 11; i++)
            items.add(new EItem("Logitech M185", EItemType.MOUSE, 15.00));
        for (int i = 0; i < 11; i++)
            items.add(new EItem("Asus Sabertooth xxx", EItemType.KEYBOARD, 20.00));
        for (int i = 0; i < 6; i++)
            items.add(new EItem("Intel i9 10900k", EItemType.PROCESSOR, 20.00));

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 505.00 - 15.00 - 10.00;
    }

    @Test
    public void lessThanOneThousandTotalOrderTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();
        items.add(new EItem("Asus Sabertooth xxx", EItemType.MOTHERBOARD, 340.00));
        items.add(new EItem("Intel i9 10900k", EItemType.PROCESSOR, 449.00));

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 789.00;
    }

    @Test
    public void moreThanOneThousandTotalOrderTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();
        items.add(new EItem("Asus Sabertooth xxx", EItemType.MOTHERBOARD, 640.00));
        items.add(new EItem("Intel i9 10900k", EItemType.PROCESSOR, 449.00));

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 980.10;
    }

    @Test
    public void processorAndTotalAmountDiscountOrderTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();
        EItem specificProcessor = new EItem("Intel i5 5280", EItemType.PROCESSOR, 150.00);
        EItem item1 = new EItem("Logitech M185", EItemType.MOUSE, 15.00);

        items.add(item1);
        items.add(specificProcessor);

        for (int i = 0; i < 5; i++)
            items.add(new EItem("Intel i9 10900k", EItemType.PROCESSOR, 449.00));

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 2410.00 - 241.00 - 75.00;
    }

    @Test
    public void processorAndNoTotalAmountDiscountOrderTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();
        EItem specificProcessor = new EItem("Intel Pentium", EItemType.PROCESSOR, 90.00);

        items.add(specificProcessor);
        for (int i = 0; i < 6; i++)
            items.add(new EItem("Intel i2 1024", EItemType.PROCESSOR, 150.00));

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 990.00 - 45.00;
    }

    @Test
    public void mouseAndTotalAmountDiscountOrderTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();
        EItem processor = new EItem("Intel i9 10900k", EItemType.PROCESSOR, 849.00);

        items.add(processor);
        for (int i = 0; i < 15; i++)
            items.add(new EItem("Logitech M185", EItemType.MOUSE, 15.00));

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 1074.00 - 107.40 - 15.00;
    }

    @Test
    public void mouseAndNoTotalAmountDiscountOrderTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();

        for (int i = 0; i < 20; i++)
            items.add(new EItem("Logitech M185", EItemType.MOUSE, 15.00));

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 300.00 - 15.00;
    }

    @Test
    public void combinedMouseAndProcessorAndEqualsMouseKeyboardAndOverallDiscountTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();

        for (int i = 0; i < 11; i++)
            items.add(new EItem("Logitech M185", EItemType.MOUSE, 15.00));
        for (int i = 0; i < 11; i++)
            items.add(new EItem("Asus Sabertooth xxx", EItemType.KEYBOARD, 20.00));
        for (int i = 0; i < 6; i++)
            items.add(new EItem("Intel i9 10900k", EItemType.PROCESSOR, 200.00));

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 1585.00 - 15.00 - 100.00 - 158.50;
    }

    @Test(expected = BillException.class)
    public void over30ItemsOrderTest() throws BillException {
        // Arrange
        List<EItem> itemList = new LinkedList<>();

        for (int i = 0; i < 31; i++) {
            itemList.add(new EItem("Asus Sabertooth xxx", EItemType.KEYBOARD, 50.00));
        }

        // Act
        bill.getOrderPrice(itemList, user);

        // Assert
        fail();
    }

    @Test
    public void lessThanTenTotalAmountOrderTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();
        EItem genericMouse = new EItem("Logitech M18", EItemType.MOUSE, 7.00);
        items.add(genericMouse);

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 7.00 + 2.00;
    }

    @Test
    public void equalToTenTotalAmountOrderTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();
        EItem genericMouse = new EItem("Logitech M18", EItemType.MOUSE, 10.00);
        items.add(genericMouse);

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 10.00;
    }

    @Test
    public void overThanTenTotalAmountOrderTest() throws BillException {
        // Arrange
        List<EItem> items = new ArrayList<>();
        EItem genericMouse = new EItem("Logitech M18", EItemType.MOUSE, 15.00);
        items.add(genericMouse);

        // Act
        double total = bill.getOrderPrice(items, user);

        // Assert
        assert total == 15.00;
    }
}