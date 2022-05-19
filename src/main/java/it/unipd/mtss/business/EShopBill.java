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
        int totalItems = itemsOrdered.size();

        if(totalItems == 0) {
            throw new BillException("No items on the order");
        }

        getProcessorDiscount(itemsOrdered);

        double totalAmount = itemsOrdered.stream()
                .mapToDouble(EItem::getPrice)
                .sum();

        return totalAmount;
    }

    private void getProcessorDiscount(List<EItem> itemsOrdered) {
        long numberProcessor = itemsOrdered.stream()
                .filter(item -> item.getEItemType() == EItemType.PROCESSOR)
                .count();

        if(numberProcessor > 5) {
            Optional<EItem> minItem = itemsOrdered.stream()
                    .filter(item -> item.getEItemType() == EItemType.PROCESSOR)
                    .min((item1, item2) ->
                            item1.getPrice() >= item2.getPrice() ? 1 : -1);

            minItem.ifPresent(eItem -> eItem.setPrice(eItem.getPrice() * 0.5));

        }
    }
}