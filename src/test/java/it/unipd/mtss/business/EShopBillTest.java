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
}