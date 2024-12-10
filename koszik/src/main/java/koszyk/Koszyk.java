package koszyk;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Koszyk {
    private List<Product> products;

    public Koszyk() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product findCheapestProduct() {
        return Collections.min(products, Comparator.comparingDouble(Product::getPrice));
    }

    public Product findMostExpensiveProduct() {
        return Collections.max(products, Comparator.comparingDouble(Product::getPrice));
    }

    public List<Product> findNCheapestProducts(int n) {
        return products.stream()
                       .sorted(Comparator.comparingDouble(Product::getPrice))
                       .limit(n)
                       .collect(Collectors.toList());
    }

    public List<Product> findNMostExpensiveProducts(int n) {
        return products.stream()
                       .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                       .limit(n)
                       .collect(Collectors.toList());
    }

    public void sortProducts() {
        products.sort(Comparator.comparingDouble(Product::getPrice).reversed()
                                .thenComparing(Product::getName));
    }

    public double calculateTotalPrice() {
        double total = 0.0;
        for (Product product : products) {
            total += product.getPrice();  
        }
        return total;
    }
    

    public double calculateTotalPriceAfterDiscounts() {
        double total = 0.0;
        for (Product product : products) {
            total += product.getDiscountPrice();  
        }
        return total;
    }
    

    public void applyDiscount(double orderTotal) {
        if (orderTotal > 300) {
            for (Product product : products) {
                double discountedPrice = product.getPrice() * 0.95;
                product.setDiscountPrice(discountedPrice);
            }
        }
    }

    public void applyBuyTwoGetThirdFree() {
    products.sort(Comparator.comparingDouble(Product::getPrice));

    if (products.size() >= 3) {
        products.get(0).setDiscountPrice(0.0); 
    }
}

    public void applyFreeMug(double orderTotal) {
        if (orderTotal > 200) {
            System.out.println("Kubek gratis!");
        }
    }

    public void displayProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public List<Product> getProducts() {
        return products;
    }
}
