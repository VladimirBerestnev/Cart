package ru.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.cart.cart.Cart;
import ru.cart.cart.CartFactory;
import ru.cart.cart.Product;
import ru.cart.cart.ProductRepository;
import ru.cart.config.ApplicationConfiguration;
import java.util.*;

@Component
public class Main {

    @Autowired
    private CartFactory cartFactory;

    @Autowired
    public void setCartFactory(CartFactory cartFactory) {
        this.cartFactory = cartFactory;
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

            ProductRepository productRepository = context.getBean(ProductRepository.class);
            Cart cart = context.getBean(Cart.class);
            Main main = new Main();

             Scanner scanner = new Scanner(System.in);
            List<Product> listCart = cart.getCartList();

            while (true){

                System.out.println("Чтобы просмотреть список товаров в корзине, нажмите 1");
                System.out.println("Чтобы добавить товар, нажмите 2");
                System.out.println("Чтобы удалить товар, нажмите 3");
                System.out.println("Чтобы создать новую корзину, нажмите 4");

                String str = scanner.nextLine();

                if (str.equals("1")) {

                    if (listCart.isEmpty()) {
                        System.out.println("В корзине пусто");
                    } else {
                        for (Product product : listCart) {
                            System.out.println(product);
                        }
                    }
                }

                if (str.equals("2")){
                    main.printList(productRepository);
                    System.out.println("Выберите товар для добавления");
                    int str2 = Integer.parseInt(scanner.nextLine());
                    listCart.add(productRepository.streamGet(str2));
                }

                if (str.equals("3")){

                    if (listCart.isEmpty()) {
                        System.out.println("В корзине пусто");
                    } else {
                        for (Product product : listCart) {
                            System.out.println(product);
                        }
                        System.out.println("Введите ID товара, который нужно удалить");
                        int str2 = Integer.parseInt(scanner.nextLine());
                        listCart.remove(productRepository.streamGet(str2));
                    }
                }

                if (str.equals("4")){
                    cart = context.getBean(Cart.class);
                    listCart = cart.getCartList();
                }

                if (str.startsWith("/end")) {
                    break;
                }
            }
    }

    public void printList(ProductRepository productRepository){

        List<Product> list1 = productRepository.getProductList();
        for (Product product : list1){
            System.out.println(product);
        }
    }
}
