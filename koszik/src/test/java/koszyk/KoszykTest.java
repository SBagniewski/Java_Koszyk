package koszyk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.Collectors;


import java.util.List;

public class KoszykTest {
    
    private Koszyk cart;
    private Product product1;
    private Product product2;
    private Product product3;
    private Product product4;

    @BeforeEach
    public void setup() {
        cart = new Koszyk();
        product1 = new Product("001", "Laptop", 2500.00);
        product2 = new Product("002", "Smartphone", 800.00);
        product3 = new Product("003", "Headphones", 175.00);
        product4 = new Product("004", "Kubek firmowy", 25.00);

        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);
        cart.addProduct(product4);
    }

    @Test
    public void testFindCheapestProduct() {
        Product cheapest = cart.findCheapestProduct();
        assertEquals(product4, cheapest);
    }

    @Test
    public void testFindMostExpensiveProduct() {
        Product mostExpensive = cart.findMostExpensiveProduct();
        assertEquals(product1, mostExpensive);
    }

    @Test
    public void testFindNCheapestProducts() {
        List<Product> cheapestProducts = cart.findNCheapestProducts(2);
        assertEquals(2, cheapestProducts.size());
        assertEquals(product4, cheapestProducts.get(0));
        assertEquals(product3, cheapestProducts.get(1));
    }

    @Test
    public void testFindNMostExpensiveProducts() {
        List<Product> expensiveProducts = cart.findNMostExpensiveProducts(2);
        assertEquals(2, expensiveProducts.size());
        assertEquals(product1, expensiveProducts.get(0));
        assertEquals(product2, expensiveProducts.get(1));
    }

    @Test
    public void testSortProducts() {
        cart.sortProducts();
        List<Product> sortedProducts = cart.getProducts();
        assertEquals(product1, sortedProducts.get(0));  
        assertEquals(product2, sortedProducts.get(1));  
        assertEquals(product3, sortedProducts.get(2));  
        assertEquals(product4, sortedProducts.get(3));  
    }

    @Test
    public void testCalculateTotalPrice() {
        double totalPrice = cart.calculateTotalPrice();
        assertEquals(3500.00, totalPrice);
    }

    @Test
    public void testCalculateTotalPriceAfterDiscounts() {
        cart.applyDiscount(cart.calculateTotalPrice());
        double totalPriceAfterDiscount = cart.calculateTotalPriceAfterDiscounts();
        assertEquals(3325.00, totalPriceAfterDiscount, 0.01);
    }

    @Test
    public void testApplyBuyTwoGetThirdFree() {
        cart.applyBuyTwoGetThirdFree();
        cart.sortProducts();
        assertEquals(0.0, cart.getProducts().get(3).getDiscountPrice(), 0.01);  // 3. najtańszy produkt powinien być za darmo
    }

}
