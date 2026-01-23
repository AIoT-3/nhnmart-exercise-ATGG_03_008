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

package com.nhnacademy.thread.customer;

import com.nhnacademy.customer.cart.CartItem;
import com.nhnacademy.customer.domain.Customer;
import com.nhnacademy.nhnmart.entring.EnteringQueue;
import com.nhnacademy.nhnmart.product.service.ProductService;
import com.nhnacademy.nhnmart.product.service.impl.ProductServiceImpl;
import com.nhnacademy.thread.util.RequestChannel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerShoppingHandlerTest {

    EnteringQueue enteringQueue;
    ProductService productService;
    RequestChannel checkoutChannel;
    CustomerShoppingHandler customerShoppingHandler;

    @BeforeEach
    void setUp(){
        enteringQueue = Mockito.mock(EnteringQueue.class);
        productService = Mockito.mock(ProductServiceImpl.class);
        checkoutChannel = Mockito.mock(RequestChannel.class);
        customerShoppingHandler = new CustomerShoppingHandler(enteringQueue,productService,checkoutChannel);
        Mockito.when(productService.getTotalCount()).thenReturn(5l);
    }

    @AfterEach
    void release(){
        CartLocal.reset();
    }

    @Test
    @Order(1)
    @DisplayName("파라미터 null 체크")
    void constructorTest1(){
        // EnteringQueue enteringQueue, ProductService productService, RequestChannel checkoutChannel null check

        // TODO#9-1-11 CustomerShoppingHandler 객체가 생성될 때 파라미터의 null 여부를 검증하는 코드를 작성하세요.
        // - enteringQueue, productService, checkoutChannel

        Assertions.assertAll(
            ()-> assertThrows( IllegalArgumentException.class, ()->{
                new CustomerShoppingHandler(null,productService,checkoutChannel);
            }),
            ()-> assertThrows( IllegalArgumentException.class, ()->{
                 new CustomerShoppingHandler(enteringQueue,null,checkoutChannel);
            }),
            ()-> assertThrows( IllegalArgumentException.class, ()->{
                new CustomerShoppingHandler(enteringQueue,productService,null);
            })
        );
    }

    @Test
    @Order(2)
    @DisplayName("쇼핑 후 결제 대기열 등록")
    void joinCheckoutChannel() throws InvocationTargetException, IllegalAccessException {

        CartLocal.initialize(new Customer(1L,"NHN아카데미1",100_0000));
        CartLocal.getCart().tryAddItem(new CartItem(1L,1));
        Optional<Method> methodOptional = ReflectionUtils.findMethod(CustomerShoppingHandler.class,"joinCheckoutChannel");

        if(methodOptional.isEmpty()){
            fail("joinCheckoutQueue() not found");
        }

        methodOptional.get().setAccessible(true);
         methodOptional.get().invoke(customerShoppingHandler);

        // TODO#9-1-12 Mockito.verify()를 이용해서 checkoutChannel.addRequest()가 1회 호출되었는지 검증합니다.
        // checkoutChannel.addRequest()를 호출해서 결제 대기열에 등록합니다.
        Mockito.verify(checkoutChannel, Mockito.times(1)).addRequest(Mockito.any());
    }

    @Order(3)
    @RepeatedTest(5) // 5회 테스트 시도
    @DisplayName("장바구니에 담는 제품의 수량 1-5 랜덤 숫자 반환")
    void getBuyCountByRandTest() throws Exception {

        Optional<Method> methodOptional = ReflectionUtils.findMethod(CustomerShoppingHandler.class,"getBuyCountByRand");

        if(methodOptional.isEmpty()){
            fail("getBuyCountByRand() not found");
        }

        // private 메서드 접근을 위해서 true로 설정
        methodOptional.get().setAccessible(true);

        int actual = (int)methodOptional.get().invoke(customerShoppingHandler);
        log.debug("{actual:{}}",actual);

        // TODO#9-1-13 1 <= actual <= 5 검증합니다.
        assertTrue(1 <= actual && actual <= 5);
    }

    @Order(4)
    @RepeatedTest(5) // 5회 반복
    @DisplayName("장바구니에 담는 제품의 개수 1-10 랜덤 숫자 반환")
    void getShoppingCountByRandTest() throws InvocationTargetException, IllegalAccessException {
        Optional<Method> methodOptional = ReflectionUtils.findMethod(CustomerShoppingHandler.class,"getShoppingCountByRand");
        if(methodOptional.isEmpty()){
            fail("getShoppingCountByRand() not found");
        }
        // private 메서드 접근을 위해 true로 설정
        methodOptional.get().setAccessible(true);

        int actual = (int)methodOptional.get().invoke(customerShoppingHandler);
        log.debug("{actual:{}}",actual);

        // TODO#9-1-14 1 <= actual <= 10 검증합니다.
        assertTrue(1 <= actual && actual <= 10);
    }

    @Order(5)
    @RepeatedTest(5)
    @DisplayName("쇼핑할 제품의 ID - 1 ~ productService.getTotalCount() 범위의 랜덤 숫자 반환")
    void getProductIdByRand() throws InvocationTargetException, IllegalAccessException {

        Optional<Method> methodOptional = ReflectionUtils.findMethod(CustomerShoppingHandler.class,"getProductIdByRand");
        if (methodOptional.isEmpty()){
            fail("getProductIdByRand() not found");
        }

        // private 메서드 접근을 위해 true로 설정
        methodOptional.get().setAccessible(true);

        long totalCount = productService.getTotalCount();
        long actual = (long)methodOptional.get().invoke(customerShoppingHandler);

        log.debug("totalCount:{}, actual:{}",totalCount, actual);

        // TODO#9-1-15 actual <= totalCount인지 검증합니다.
        assertTrue(actual<= totalCount) ;
    }

}