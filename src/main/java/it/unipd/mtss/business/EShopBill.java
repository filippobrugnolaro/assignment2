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
        double totalAmount = itemsOrdered.stream()
                .mapToDouble(EItem::getPrice)
                .sum();

        if(totalItems == 0) {
            throw new BillException("No items on the order");
        }

        if(totalItems > 30) {
            throw new BillException("Limit of items exceeded");
        }

        getProcessorDiscount(itemsOrdered);
        getMouseDiscount(itemsOrdered);
        getMouseOrKeyBoardDiscount(itemsOrdered);

        double totalDiscount = getOverallDiscount(totalAmount);
        double commissionAmount = getCommissionAmount(totalAmount);

        double totalDiscountedAmount = itemsOrdered.stream()
                .mapToDouble(EItem::getPrice)
                .sum();

        return totalDiscountedAmount - totalDiscount + commissionAmount;
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

    private void getMouseDiscount(List<EItem> itemsOrdered) {
        long mouses = itemsOrdered.stream()
                .filter(item -> item.getEItemType() == EItemType.MOUSE)
                .count();


        if(mouses > 10) {
            Optional<EItem> minItem = itemsOrdered.stream()
                    .filter(item -> item.getEItemType() == EItemType.MOUSE)
                    .min((item1, item2) ->
                            item1.getPrice() >= item2.getPrice() ? 1 : -1);

            minItem.ifPresent(eItem -> eItem.setPrice(0));
        }
    }

    private void getMouseOrKeyBoardDiscount(List<EItem> itemsOrdered) {
        long mouses = itemsOrdered.stream()
                .filter(item -> item.getEItemType() == EItemType.MOUSE)
                .count();

        long numberProcessor = itemsOrdered.stream()
                .filter(item -> item.getEItemType() == EItemType.KEYBOARD)
                .count();

        if(mouses == numberProcessor && mouses > 0) {
            Optional<EItem> minItem = itemsOrdered.stream()
                    .filter(item -> item.getEItemType() == EItemType.MOUSE ||
                            item.getEItemType() == EItemType.KEYBOARD)
                    .min((item1, item2) ->
                            item1.getPrice() >= item2.getPrice() ? 1 : -1);

            minItem.ifPresent(eItem -> eItem.setPrice(0.00));
        }
    }

    private double getOverallDiscount(double totalAmount) {
        return totalAmount > 1000 ? totalAmount * 0.1 : 0;
    }

    private double getCommissionAmount(double totalAmount) {
        return totalAmount < 10 ? 2 : 0;
    }
}
