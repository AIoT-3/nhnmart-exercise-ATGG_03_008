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
import org.checkerframework.checker.units.qual.A;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.synchronizedList;

public class Cart implements Serializable {

    private List<CartItem> cartItems;

    public Cart() {
        // TODO#2-1 장바구니 아이템을 담을 수 있는 cartItems를 초기화합니다. 장바구니에 아이템이 추가될 때 동기화 처리를 위해 Collections.synchronizedList를 이용해 cartItems를 초기화합니다.
        // 즉 한 번에 한 아이템만 추가될 수 있고, 중복해서 추가될 수 없습니다.
        // https://docs.oracle.com/javase/6/docs/api/java/util/Collections.html#synchronizedList(java.util.List)
        cartItems = Collections.synchronizedList(new ArrayList<>());
    }

    public void tryAddItem(CartItem cartItem) throws ProductAlreadyExistsException {
        // TODO#2-2 장바구니에 아이템이 이미 존재한다면 ProductAlreadyExistsException 예외가 발생합니다.
        if(cartItems.contains(cartItem)){
            throw new ProductAlreadyExistsException(cartItem.getProductId());
        }
        // TODO#2-3 cartItem에 아이템을 추가하는 코드를 작성하세요.
        cartItems.add(cartItem);
    }

    public void clear(){
        // TODO#2-4 장바구니 cartItems를 초기화합니다.
        cartItems.clear();
    }

    public List<CartItem> getCartItems() {
        // TODO#2-5 장바구니 아이템을 반환합니다.
        return new ArrayList<>(cartItems);
        };
    }


