package internetshop;

import internetshop.lib.Injector;
import internetshop.model.Item;
import internetshop.service.ItemService;
import java.util.List;

public class Main {
    private static Injector injector = Injector.getInstance("internetshop");

    public static void main(String[] args) {
        ItemService itemService = (ItemService) injector.getInstance(ItemService.class);

        initializeDb(itemService);

        List<Item> allItems = itemService.getAll();
        for (Item item: allItems) {
            System.out.println(item.toString());
        }
    }

    private static void initializeDb(ItemService itemService) {
        Item item1 = new Item("iPhone", 1000);
        Item item2 = new Item("Nokia", 100);
        Item item3 = new Item("Sumsung", 500);
        itemService.create(item1);
        itemService.create(item2);
        itemService.create(item3);
    }
}
