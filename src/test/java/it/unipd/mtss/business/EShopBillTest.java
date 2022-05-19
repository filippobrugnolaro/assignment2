////////////////////////////////////////////////////////////////////
// Alessandro Cavaliere 1224440
// Filippo Brugnolaro 1217321
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EShopBillTest {
    private EShopBill bill;
    private final User user = new User("TEST", "Test", "Testing", "test@test.com", LocalDate.of(2000, 2, 26));

    @Before
    public void before() {
        bill = new EShopBill();
    }

    @Test
    public void EmptyOrderTest() throws BillException {
        // Arrange
        List<EItem> itemList = new ArrayList<>();

        // Act
        double total = bill.getOrderPrice(itemList, user);

        // Assert
        assert total == 0.00;
    }
}