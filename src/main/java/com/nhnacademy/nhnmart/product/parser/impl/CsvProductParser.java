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
import com.nhnacademy.nhnmart.product.exception.CsvParsingException;
import com.nhnacademy.nhnmart.product.parser.ProductParser;
import com.nhnacademy.nhnmart.product.util.ProductIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class CsvProductParser implements ProductParser {

    // 제품의 기본 수량 = 100개
    private final int DEFAULT_QUANTITY = 100;
    private InputStream inputStream;

    public CsvProductParser() {
        // TODO#6-2-1 기본 생성자 구현, getProductsStream()을 이용해서 inputStream을 초기화합니다.
        inputStream = getProductsStream();
    }

    public CsvProductParser(InputStream inputStream) {
        // TODO#6-2-2 inputStream parameter로 전달됩니다. 초기화합니다.
        if(inputStream == null){
            throw new IllegalArgumentException();
        }
        this.inputStream = inputStream;
    }

    @Override
    public List<Product> parse() {
        /* TODO#6-2-3 parse() 메서드를 구현하세요
            [CSV Parser]
            - https://github.com/nhnacademy-bootcamp/java-dev-settings/blob/main/docs/06.maven/02.Maven/06.pom.xml.adoc 참고합니다.
            - ProductParser interface의 getProductsStream()를 이용해서 구현합니다.
         */
        List<Product> products = new ArrayList<>();

        try (Reader in = new InputStreamReader(inputStream)){
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader("품목","메이커","스펙","단위","가격")
                    .setSkipHeaderRecord(true)
                    .build();

            Iterable<CSVRecord> records = csvFormat.parse(in);

            for (CSVRecord record : records) {
                long id = ProductIdGenerator.getNewId();

                String item = record.get("품목");
                String maker = record.get("메이커");
                String specification = record.get("스펙");
                String unit = record.get("단위");

                String priceStr = record.get("가격").replace(",", "");
                int price = Integer.parseInt(priceStr);

                products.add(new Product(id, item, maker, specification, unit, price, DEFAULT_QUANTITY));
            }
        } catch (IOException | NumberFormatException e) {
            throw new CsvParsingException();
        }

        return products;
    }


    @Override
    public void close() throws IOException {
        // TODO#6-2-5 inputStream 객체가 존재하면 close() 메서드를 호출해서 자원을 해제합니다.
        if (inputStream != null) {
            inputStream.close();
        }
    }
}
