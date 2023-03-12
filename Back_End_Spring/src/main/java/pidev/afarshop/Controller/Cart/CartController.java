package pidev.afarshop.Controller.Cart;


import org.webjars.NotFoundException;
import pidev.afarshop.Entity.*;
import pidev.afarshop.Repository.*;
import pidev.afarshop.Service.Cart.CartServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/api/cart/")
public class CartController {

    CartServices cartService;
    CartRepository cartRepository;
    ProductRepository productRepository;
    UserRepository userRepository;
    @GetMapping("/retrive_all_carts")
    public List<Cart> retrieveCartList(){
        return cartService.findAll();
    }

    @GetMapping("/retrive_cart/{cartId}")
    public Cart retrieveCart(@PathVariable("cartId") Long cartId){
        return cartService.retrieveItem(cartId);
    }

    @PostMapping("/add_cart")
    public Cart addCart(@RequestBody Cart cart){
        return cartService.add(cart);
    }
/*kk*/
    @PutMapping("/update_cart")
    public Cart updateCart(@RequestBody Cart cart){
        return cartService.update(cart);
    }

    @DeleteMapping("/delete_cart/{cartId}")
    public void deleteCart(@PathVariable("cartId") Long cartId){
        cartService.delete(cartId);
    }

    @PostMapping("/users/{userId}/{productId}")
    public List<Product> addProductToCartByUser(@PathVariable("userId") Long userId,
                                                @PathVariable("productId") Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id " + userId));
        Cart cart = user.getCart();

        if (cart == null) {
            cart = new Cart();
            user.setCart(cart);
        }
        Product product = productRepository.findById(productId).orElse(null);
        product.setCart(cart);
        cart.getProducts().add(product);

        cartRepository.save(cart);

        return cart.getProducts();
    }
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("productId") Long productId,
                            @RequestParam("quantity") int quantity,
                            Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).get();
        Product product = productRepository.findById(productId).orElseThrow();
        Cart cart = user.getCart();
        cartService.addItem(product, quantity);
        cartRepository.save(cart);
        return username;
    }




}
