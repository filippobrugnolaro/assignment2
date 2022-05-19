////////////////////////////////////////////////////////////////////
// Alessandro Cavaliere 1224440
// Filippo Brugnolaro 1217321
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.model;

public enum EItemType {
    PROCESSOR("PROCESSOR"),
    MOTHERBOARD("MOTHERBOARD"),
    KEYBOARD("KEYBOARD"),
    MOUSE("MOUSE");

    private final String value;

    EItemType(final String val) {
        this.value = val;
    }
}
