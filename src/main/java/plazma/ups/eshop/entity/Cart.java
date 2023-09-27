package plazma.ups.eshop.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart {

    private List<CartItem> items = new ArrayList<>();

    public CartItem findItemById(long productId) {
        Iterator<CartItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getProduct().getId() == productId) {
                return item;
            }
        }
        return null;
    }

    public void addItem(Product product, float quantity) {
        CartItem item = findItemById(product.getId());
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
            return;
        }

        item = new CartItem(product, quantity);
        items.add(item);
    }

    public void removeItem(long productId) {
        Iterator<CartItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            if (item.getProduct().getId() == productId) {
                iterator.remove();
            }
        }
    }

    public List<CartItem> getItems() {
        return items;
    }
}
