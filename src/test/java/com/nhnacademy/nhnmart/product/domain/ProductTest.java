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

package com.nhnacademy.nhnmart.product.domain;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class ProductTest {
    static Product product;
    static final long id = 1L;
    static final String item = "주방세제";
    static final String maker = "LG";
    static final String specification ="(750㎖) 자연퐁 스팀워시 레몬";
    static final String unit = "개";
    static final int price = 9900;
    static final int quantity = 100;

    @BeforeAll
    static void setUp(){
        product = new Product(id,item,maker,specification,unit,price,quantity);
    }

    @Test
    @Order(1)
    @DisplayName("null 또는 empty 체크 : item, maker, specification, unit")
    void constructorTest1(){

    }

    @Test
    @Order(2)
    @DisplayName("{ id, price, quantity } < 0 ")
    void constructorTest2(){

    }

    @Test
    @Order(3)
    void getId() {

    }

    @Test
    @Order(4)
    void getItem() {

    }

    @Test
    @Order(5)
    void getMaker() {

    }

    @Test
    @Order(6)
    void getSpecification() {

    }

    @Test
    @Order(7)
    void getUnit() {

    }

    @Test
    @Order(8)
    void getPrice() {

    }

    @Test
    @Order(9)
    void getQuantity() {

    }

    @Test
    @Order(10)
    void setQuantity() {
        // TODO#6-1-13 Product의 quantity를 50으로 변경하고, 검증합니다.
        product.setQuantity(50);
        assertEquals(product.getQuantity(), 50);
    }

    @Test
    @Order(11)
    void setQuantity_negativeParam(){
        // TODO#6-1-14 Product의 quantity를 -1으로 변경하고, IllegalArgumentException 발생하는지 검증합니다.
        assertThrows(IllegalArgumentException.class, ()->{
            product.setQuantity(-1);
        });
    }
}