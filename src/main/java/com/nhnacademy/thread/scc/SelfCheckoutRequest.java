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

package com.nhnacademy.thread.scc;

import com.nhnacademy.customer.cart.Cart;
import com.nhnacademy.customer.cart.CartItem;
import com.nhnacademy.customer.domain.Customer;
import com.nhnacademy.customer.exception.InsufficientFundsException;
import com.nhnacademy.nhnmart.product.domain.Product;
import com.nhnacademy.nhnmart.product.service.ProductService;
import com.nhnacademy.thread.util.Executable;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class SelfCheckoutRequest implements Executable {

    private final Customer customer;
    private final Cart cart;
    private final ProductService productService;

    public SelfCheckoutRequest(Customer customer, Cart cart, ProductService productService) {
        // TODO#9-2-1 customer, cart, productService가 null이면 IllegalArgumentException 발생
        if(customer == null || cart == null || productService == null){
            throw new IllegalArgumentException();
        }

        // TODO#9-2-2 customer, cart, productService 초기화
        this.customer = customer;
        this.cart = cart;
        this.productService = productService;
    }

    @Override
    public void execute()  {

        /* TODO#9-2-3 execute 메서드를 구현합니다.
           - getTotalAmountFromCart() - 결제 금액을 계산하는 메서드
           - customer.pay() 메서드를 이용해서 결제를 진행합니다.
           - 결제할 총 금액이 < customer.money 이면 모든 제품을 반납합니다. productService.returnProduct()를 이용해서 구현합니다.
        */


        try {
            customer.pay(getTotalAmountFromCart());
            Thread.sleep(1000);
        } catch (InsufficientFundsException e) {
            for (CartItem c : cart.getCartItems()) {
                productService.returnProduct(c.getProductId(), c.getQuantity());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public int getTotalAmountFromCart(){
        // TODO#9-2-4 결제 금액을 계산 후 반환합니다.
        int totalAmount = 0;
        for(CartItem c : cart.getCartItems()){
            totalAmount +=productService.getProduct(c.getProductId()).getPrice() * c.getQuantity();
        }
        return totalAmount;
    }
}
