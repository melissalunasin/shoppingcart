package com.demo.shopppingcart;

import com.demo.shopppingcart.model.Cart;
import com.demo.shopppingcart.model.CartItem;
import com.demo.shopppingcart.model.Product;
import com.demo.shopppingcart.repository.CartItemRepository;
import com.demo.shopppingcart.repository.CartRepository;
import com.demo.shopppingcart.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.*;

@SpringBootApplication
public class ShopppingcartApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopppingcartApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ShopppingcartApplication.class, args);
	}

	@Profile({"local"})
	@Bean
	public CommandLineRunner setupTestData(CartRepository cartRepository, ProductRepository productRepository,
										   CartItemRepository cartItemRepository){

		return (args) -> {

			productRepository.deleteAll();
			cartRepository.deleteAll();

			Product product1 = new Product();
//			product1.setId(UUID.randomUUID().toString());
			product1.setName("product1");
			product1.setDescription("This is product1");
			productRepository.save(product1);

			CartItem cartItem1 = new CartItem();
//			cartItem1.setId(UUID.randomUUID().toString());
			cartItem1.setProduct(product1);
			cartItem1.setQuantity(1);
//			cartItem1.setCart(newCart);
//			cartItemRepository.save(cartItem1);

			Product product2 = new Product();
//			product2.setId(UUID.randomUUID().toString());
			product2.setName("product2");
			product2.setDescription("This is product2");
			productRepository.save(product2);

			CartItem cartItem2 = new CartItem();
//			cartItem2.setId(UUID.randomUUID().toString());
			cartItem2.setProduct(product2);
			cartItem2.setQuantity(2);
//			cartItem2.setCart(newCart);
//			cartItemRepository.save(cartItem2);

			Set<CartItem> cartItems = new HashSet<>();
			cartItems.add(cartItem1);
			cartItems.add(cartItem2);
			Cart newCart = new Cart();
			newCart.setCartItems(cartItems);
//			String cartId = "75068d80-e92f-498d-ae4c-38ffb85fdc0c";
//			newCart.setId(cartId);
			cartRepository.save(newCart);

			List<Cart> carts = cartRepository.findAll();
			for(Cart cart : carts){
				LOGGER.debug("cart : {}", cart);
			}
		};
	}

}