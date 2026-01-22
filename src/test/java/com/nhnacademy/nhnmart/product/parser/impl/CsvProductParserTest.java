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

package com.nhnacademy.nhnmart.product.parser.impl;

import com.nhnacademy.nhnmart.product.domain.Product;
import com.nhnacademy.nhnmart.product.parser.ProductParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CsvProductParserTest {
    public static ProductParser productParser;
    @BeforeAll
    static void beforeSetUp(){
        // @BeforeAll은 CsvProductParserTest에서 테스트 시작 전 한 번 실행됩니다.
        // TODO#6-2-6 CsvProductParser 객체를 생성합니다.
        productParser = new CsvProductParser();
    }
    @AfterAll
    static void tearDown() throws IOException {
        // @AfterAll은 CsvProductParserTest 테스트 종료 시점에 한 번 실행됩니다.
        // TODO#6-2-7 CsvProductParserTest 종료되면 productParser.close()를 호출하여 자원을 해제합니다.
        productParser.close();
    }

    @Test
    @Order(1)
    @DisplayName("instance of ProductParser")
    void constructorTest1(){
    assertInstanceOf(ProductParser.class, productParser);
    }

    @Test
    @Order(2)
    @DisplayName("inputStream is null")
    void constructorTest2(){
        // TODO#6-2-8 CsvProductParser 객체 생성 시 inputStream == null이면 IllegalArgumentException이 발생하는지 검증합니다.

        assertThrows(IllegalArgumentException.class, ()-> {
            new CsvProductParser(null);
        });
    }

    @Test
    @Order(3)
    @DisplayName("/resources/product_data.csv 존재하는지 체크")
    void getStreamTest(){

    }

    @Test
    @Order(4)
    @DisplayName("parsing from /test/resources/product_data.csv")
    void parse() {

        // TODO#6-2-9 actual과 excepted가 일치하는지 검증합니다.
        List<Product> actual = productParser.parse();

        Product p = actual.get(0);

        assertEquals("주방세제", p.getItem());
        assertEquals("LG", p.getMaker());
        assertEquals("(750㎖) 자연퐁 스팀워시 레몬", p.getSpecification());
        assertEquals("개", p.getUnit());
        assertEquals(9900, p.getPrice());
    }

}