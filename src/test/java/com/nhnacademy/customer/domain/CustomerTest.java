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

package com.nhnacademy.customer.domain;

import com.nhnacademy.customer.exception.InsufficientFundsException;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// CustomerTest를 통과해야 합니다.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerTest {

    Customer customer;
    @BeforeEach
    void setUp(){
        customer = new Customer(1L,"NHN아카데미",100_0000);
    }

    @Order(1)
    @Test()
    @DisplayName("id < 0")
    void testConstructor1(){
        // TODO#1-12 id < 1 이면 IllegalArgumentException이 발생하는지 검증합니다.
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(-1, "test", 1_000_000);
        });
    }

    @Order(2)
    @Test()
    @DisplayName("money < 0")
    void testConstructor3() {
        // TODO#1-13 Customer 생성 시 money < 0이면 IllegalArgumentException이 발생하는지 검증합니다.
        assertThrows(IllegalArgumentException.class, ()->{
            new Customer(1L, "test", -100_0000);
        });
    }

    @Order(3)
    @Test()
    @DisplayName("name is ( empty or null ) ")
    void testConstructor2(){
        // TODO#1-14 name이 "" 또는 null이면 IllegalArgumentException.class 예외가 발생하는지 검증합니다.
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(1L, "" , 100_0000);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(1L, null , 100_0000);
        });
    }

    @Order(4)
    @Test
    void getId() {
        // TODO#1-15 customer -> getId() 호출 시 1L을 반환하는지 검증합니다.
        assertEquals(1L, customer.getId());
    }

    @Order(5)
    @Test
    void getName() {
        // TODO#1-16 customer -> getName() 호출 시 "NHN아카데미"를 반환하는지 검증합니다.
        assertEquals("NHN아카데미", customer.getName());
    }

    @Order(6)
    @Test
    void getMoney() {
        // TODO#1-17 customer -> getMoney() 호출 시 100_0000을 반환하는지 검증합니다.
        assertEquals(100_0000, customer.getMoney());
    }

    @Order(7)
    @Test
    @DisplayName("결제 : 100_0000 - 10_0000 = 90_0000")
    void pay1() throws InsufficientFundsException {
        // TODO#1-18 10_0000 결제 후 보유 금액이 90_0000이 되는지 검증합니다.
        customer.pay(10_0000);
        assertEquals(90_0000, customer.getMoney());
    }

    @Order(8)
    @Test
    @DisplayName("결제 amount < 0 ")
    void pay2() throws InsufficientFundsException {
        // TODO#1-19 결제 금액이 0보다 작으면 IllegalArgumentException이 발생하는지 검증합니다.
        assertThrows(IllegalArgumentException.class, ()-> {
            customer.pay(-10_0000);
        });
    }

    @Order(9)
    @Test
    @DisplayName("customer money = 100만원, 200만원 결제 시도")
    void pay3(){
        // TODO#1-20 200만 원 결제 시 InsufficientFundsException.class 예외가 발생하는지 검증합니다.
        assertThrows(InsufficientFundsException.class, ()-> {
            customer.pay(200_0000);
        });
    }

    @Order(10)
    @Test
    @DisplayName("id와 name, money가 일치하면 동일한 객체로 식별")
    void testEquals1() {
        // TODO#1-21 id, name, money가 일치하면 equals가 true를 반환하는지 검증합니다.
        assertEquals(1L, customer.getId());
        assertEquals("NHN아카데미", customer.getName());
        assertEquals(100_0000, customer.getMoney());
    }

    @Order(11)
    @Test
    @DisplayName("name, money 일치, 아이디는 불일치")
    void testEquals2() {
        // TODO#1-22 id가 다르면 equals가 false를 반환하는지 검증합니다.
        assertFalse(new Customer(2L, "NHN아카데미", 1_000_000).equals(customer));

    }

}