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

package com.nhnacademy;

import com.nhnacademy.customer.generator.CustomerGenerator;
import com.nhnacademy.nhnmart.entring.EnteringQueue;
import com.nhnacademy.nhnmart.product.parser.ProductParser;
import com.nhnacademy.nhnmart.product.parser.impl.CsvProductParser;
import com.nhnacademy.nhnmart.product.repository.ProductRepository;
import com.nhnacademy.nhnmart.product.repository.impl.MemoryProductRepository;
import com.nhnacademy.nhnmart.product.service.ProductService;
import com.nhnacademy.nhnmart.product.service.impl.ProductServiceImpl;

public class App
{
    public static void main( String[] args )
    {
        //capacity를 100으로 enteringQueue를 초기화합니다.
        EnteringQueue enteringQueue = new EnteringQueue(100);

        // CustomerGenerator를 이용해서 Thread를 생성합니다.
        CustomerGenerator customerGenerator = new CustomerGenerator(enteringQueue);
        Thread enteringThread = new Thread(customerGenerator);

        // enteringThread의 이름을 'entering-thread'로 설정, enteringThread를 시작합니다.
        enteringThread.setName("entering-thread");
        // TODO#7-1 MemoryProductRepository 구현체를 이용해서 ProductRepository 객체를 생성합니다.
        ProductRepository productRepository = new MemoryProductRepository();
        // TODO#7-2 CsvProductParser 구현체를 이용해서 ProductParser 객체를 생성합니다.
        ProductParser productParser = new CsvProductParser();
        // TODO#7-3 ProductServiceImpl 구현체를 이용해서 ProductService 객체를 생성합니다.
        ProductService productService = new ProductServiceImpl(productRepository, productParser);

    }
}
