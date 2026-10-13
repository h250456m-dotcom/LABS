/** Checked exception: the requested item is already on loan. */
public class ItemNotAvailableException extends Exception {
    private final String itemId;

    public ItemNotAvailableException(String itemId) {
        super("Item " + itemId + " is not available (already on loan)");
        this.itemId = itemId;
    }

    public String getItemId() { return itemId; }
}
