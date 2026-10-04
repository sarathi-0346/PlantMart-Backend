package com.example.plantio1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.plantio1.Entity.Card;
import com.example.plantio1.Services.CartServices;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServices cartServices;

    // 1. Get all cart items
    @GetMapping("/getAllCart")
    public ResponseEntity<?> getAllCart() {

        try {

            List<Card> cartList = cartServices.getAllCard();

            if (cartList.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Cart is empty");
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(cartList);

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to get cart items");
        }
    }


    // 2. Add product to cart
    @PostMapping("/addcartItem")
    public ResponseEntity<?> addCartItem(
            @RequestParam Long userid,
            @RequestParam Long productId,
            @RequestParam int qty) {

        try {

            if (qty <= 0) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Quantity must be greater than 0");
            }

            Card cart = cartServices.addCardItem(userid, productId, qty);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(cart);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }


    // 3. Increase quantity
    @PostMapping("/increase/{cartId}")
    public ResponseEntity<?> increaseItemQuantity(
            @PathVariable Long cartId) {

        try {

            cartServices.increaseItemQuantity(cartId);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Quantity increased successfully");

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }


    // 4. Decrease quantity
    @PostMapping("/decrease/{cartId}")
    public ResponseEntity<?> decreaseItemQuantity(
            @PathVariable Long cartId) {

        try {

            cartServices.decreaseItemQuantity(cartId);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Quantity decreased successfully");

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }


    // 5. Remove one cart item
    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<?> removeCartItem(
            @PathVariable Long cartId) {

        try {

            cartServices.removeCartItem(cartId);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Cart item removed successfully");

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }


    // 6. Delete all cart items of a user
    @DeleteMapping("/deleteAll/{userid}")
    public ResponseEntity<?> deleteAllCart(
            @PathVariable Long userid) {

        try {

            cartServices.deleteAllCart(userid);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("All cart items deleted successfully");

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}