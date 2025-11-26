
/**
 * Класс инвентаря
 */
public class Inventory {

    private Item[] items;
    private int count;

    public Inventory(int size) {
        items = new Item[size];
        count = 0;
    }

    public boolean addItem(Item item) {
        if (count < items.length) {
            items[count] = item;
            count++;
            return true;
        }
        return false;
    }

    public void displayItems() {
        if (count == 0) {
            System.out.println("Инвентарь пуст");
            return;
        }

        System.out.println("\n=== ИНВЕНТАРЬ ===");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + items[i].getName()
                    + " - " + items[i].getDescription());
        }
    }

    public Item getItem(int index) {
        if (index >= 0 && index < count) {
            return items[index];
        }
        return null;
    }

    public int getCount() {
        return count;
    }
}
