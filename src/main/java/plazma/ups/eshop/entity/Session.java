package plazma.ups.eshop.entity;

public class Session {

    private Cart cart;

    public Cart getCart() {
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }

}
