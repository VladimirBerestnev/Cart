package ru.cart.cart;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    List<Product> productList = new ArrayList<>();

{
        productList.add(new Product(1, "Шашлык", 500));
        productList.add(new Product(2, "Креветки", 700));
        productList.add(new Product(3, "Сухарики", 100));
        productList.add(new Product(4, "Чипсы", 200));
        productList.add(new Product(5, "Сырные шарики", 100));

    }
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Product streamGet(int w){
        List<Product> list1 = productList.stream().filter(p -> p.getId() == w).toList();

        for (Product product: list1){
            return product;
        }

        return null;
    }
}
