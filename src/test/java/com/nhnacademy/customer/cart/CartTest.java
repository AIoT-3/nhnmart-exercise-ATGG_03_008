/*
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright 2024. NHN Academy Corp. All rights reserved.
 * + * While every precaution has been taken in the preparation of this resource,  assumes no
 * + responsibility for errors or omissions, or for damages resulting from the use of the information
 * + contained herein
 * + No part of this resource may be reproduced, stored in a retrieval system, or transmitted, in any
 * + form or by any means, electronic, mechanical, photocopying, recording, or otherwise, without the
 * + prior written permission.
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */

package com.nhnacademy.customer.cart;

import com.nhnacademy.customer.exception.ProductAlreadyExistsException;
import lombok.Synchronized;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    Cart cart;
    @BeforeEach
    void setUp() throws ProductAlreadyExistsException {
        cart = new Cart();
        cart.tryAddItem(new CartItem(1L, 1));
    }

    @Test
    @DisplayName("Serializable implements check")
    void constructorTest(){
        // TODO#2-12 Cart 객체가 Serializable을 구현했는지 검증합니다.
        assertInstanceOf(Serializable.class, cart);
    }

    @Test
    @DisplayName("장바구니(cart)에 제품(CartItem) 추가")
    void tryAddItem1() throws ProductAlreadyExistsException {
       CartItem item = new CartItem(2L, 2);

       cart.tryAddItem(item);

       assertEquals(2L, item.getProductId());
        assertEquals(2, item.getQuantity());
    }

    @Test
    @DisplayName("장바구니에 제품이 이미 추가되어 있다면 - ProductAlreadyExistsException 발생")
    void tryAddItem2() throws ProductAlreadyExistsException {
        // TODO#2-14 DisplayName에 작성된 요구사항이 만족하도록 검증합니다.
        assertThrows(ProductAlreadyExistsException.class, () -> {
            cart.tryAddItem(new CartItem(1L, 1));
        });
    }

    @Test
    @DisplayName("Cart 비우기 - 초기화")
    void clear() {
        // TODO#2-15 DisplayName에 작성된 요구사항이 만족하도록 검증합니다.
        cart.clear();
        assertEquals(0, cart.getCartItems().size());
    }

    @Test
    @DisplayName("Cart item 조회")
    void getCartItems() throws ProductAlreadyExistsException {
        // TODO#2-16 장바구니에 제품을 추가한 후, getCartItems() 메서드가 예상된 목록을 반환하는지 검증합니다.
        CartItem item = new CartItem(2L, 2);
        cart.tryAddItem(item);

        List<CartItem> items = cart.getCartItems();

        for(int i = 0 ; i < cart.getCartItems().size(); i++){
            assertEquals((long) i+1L , items.get(i).getProductId());
            assertEquals( i+1 , items.get(i).getQuantity());
        }

    }

}