////////////////////////////////////////////////////////////////////
// Alessandro Cavaliere 1224440
// Filippo Brugnolaro 1217321
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.EItemType;
import it.unipd.mtss.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EShopBill implements Bill {
    @Override
    public double getOrderPrice(List<EItem> itemsOrdered, User user)
            throws BillException {
        return 0.00;
    }
}
