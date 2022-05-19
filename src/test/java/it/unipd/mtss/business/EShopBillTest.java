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
}