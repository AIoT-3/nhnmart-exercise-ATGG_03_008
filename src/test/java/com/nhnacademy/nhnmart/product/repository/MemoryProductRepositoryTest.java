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

package com.nhnacademy.nhnmart.product.repository;

import com.nhnacademy.nhnmart.product.domain.Product;
import com.nhnacademy.nhnmart.product.repository.impl.MemoryProductRepository;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemoryProductRepositoryTest {

    private static ProductRepository productRepository;

    @BeforeAll
    static void beforeAllSetUp() {
        productRepository = new MemoryProductRepository();
        productRepository.save(new Product(1L,"주방세제","LG","(750㎖) 자연퐁 스팀워시 레몬","개",9900,100));
    }

    @Test
    @Order(1)
    @DisplayName("Product 등록")
    void save() {
        Product actual = new Product(2L,"주방세제","헨켈","(750㎖) 프릴 베이킹소다 퓨어레몬","개",8900,100);
        productRepository.save(actual);

        // TODO#6-4-8 2L에 해당되는 Product가 정상 등록되었는지 검증합니다.
        assertTrue(productRepository.existById(2L));
    }

    @Test
    @Order(2)
    @DisplayName("1L -> Product 조회")
    void findById() {
        // TODO#6-4-9 1L에 해당되는 Product의 attribute를 검증합니다.

        Product expected = new Product(1L,"주방세제","LG","(750㎖) 자연퐁 스팀워시 레몬","개",9900,100);

        assertEquals(Optional.of(expected), productRepository.findById(1L));
    }

    @Test
    @Order(3)
    @DisplayName("ID:2 -> 삭제")
    void deleteById() {
        // TODO#6-4-10 ID: 2L인 Product를 삭제하고 정상 처리되었는지 검증합니다.
        productRepository.deleteById(2L);
        assertFalse(productRepository.existById(2L));
    }

    @Test
    @Order(4)
    @DisplayName("Product 존재 여부 체크")
    void existById() {
        // TODO#6-4-11 existById()를 이용해서 제품 존재 여부를 체크할 수 있도록 검증합니다.
        assertTrue(productRepository.existById(1L));
    }

    @Test
    @Order(5)
    @DisplayName("productRepository에 등록된 전체 Product count")
    void count() {
        // TODO#6-4-12 count() 검증, productRepository에 등록된 전체 제품 수
        assertEquals(1, productRepository.count());
    }

    @Test
    @Order(6)
    @DisplayName("특정 Product의 수량")
    void countQuantityById() {
        // TODO#6-4-13 countQuantityById() 검증, ID:1에 해당되는 제품 수량 검증
        assertEquals(100, productRepository.countQuantityById(1L));
    }

    @Test
    @Order(7)
    @DisplayName("Product 수량 변경")
    void updateQuantityById() {
        // TODO#6-4-14 ID:1에 해당되는 Product의 수량을 변경하고 변경된 결과가 반영되었는지 검증합니다.
        productRepository.updateQuantityById(1L, 50);
        assertEquals(50, productRepository.countQuantityById(1L));
    }
}