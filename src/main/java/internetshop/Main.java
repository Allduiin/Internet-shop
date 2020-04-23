package internetshop;

import internetshop.lib.Injector;
import internetshop.model.Product;
import internetshop.service.ProductService;
import java.util.List;

public class Main {
    private static Injector injector = Injector.getInstance("internetshop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);

        initializeDb(productService);

        List<Product> allProducts = productService.getAll();
        for (Product product : allProducts) {
            System.out.println(product.toString());
        }
    }

    private static void initializeDb(ProductService productService) {
        Product product1 = new Product("iPhone", 1000);
        Product product2 = new Product("Nokia", 100);
        Product product3 = new Product("Sumsung", 500);
        productService.create(product1);
        productService.create(product2);
        productService.create(product3);
    }
}
