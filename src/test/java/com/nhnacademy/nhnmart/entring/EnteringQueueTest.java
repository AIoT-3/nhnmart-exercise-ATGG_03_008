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

package com.nhnacademy.nhnmart.entring;

import com.nhnacademy.customer.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.function.Try;
import org.junit.platform.commons.util.ReflectionUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class EnteringQueueTest {
    EnteringQueue enteringQueue;
    @BeforeEach
    void setUp() throws InterruptedException {
        enteringQueue = new EnteringQueue();

        /* TODO#3-9
            Customer{id=1, name='NHN아카데미1', money=1000000}
            ~
            Customer{id=99, name='NHN아카데미99', money=1000000}
            1~99 고객을 생성 후 enteringQueue 대기열에 등록합니다.
         */
        for(int i = 1; i< 100; i++){
            String name ="NHN아카데미" + i;
            enteringQueue.addCustomer(new Customer(i, name, 1000000));
        }
    }

    @Test
    @DisplayName("default queue capacity = 100")
    void constructorTest_InitCapacity() throws Exception {
        Try<Object> capacity = ReflectionUtils.tryToReadFieldValue(EnteringQueue.class,"capacity",enteringQueue);
        assertEquals(100, (int)capacity.get() );
    }

    @Test
    void addCustomer() throws Exception {
        // TODO#3-10 id=100인 고객을 enteringQueue에 등록하고 검증합니다.
        enteringQueue.addCustomer(new Customer(100L, "NHN아카데미100", 1_000_000));
        assertEquals(100, enteringQueue.getQueueSize());
    }

    @Test
    @DisplayName("queue - poll test")
    void getCustomer() throws Exception{
        // TODO#3-11 enteringQueue에서 enteringQueue.getCustomer() 호출 시 반환되는 값을 검증합니다.
        Customer customer = enteringQueue.getCustomer();
        assertEquals(1L, customer.getId());
    }

    @Test
    @DisplayName("blocking queue test : queue size : 100, 101번째 Customer를 추가한다면, Consumer에 의해서 소비될 때까지 대기합니다.")
    void blockingTest() throws Exception {

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                Customer customer100= new Customer(100L, "NHN아카데미100", 100_0000);
                log.debug("2초 대기 후 101-customer 추가됨");
                Customer customer101= new Customer(101L, "NHN아카데미101", 100_0000);

                try {
                    enteringQueue.addCustomer(customer100);
                    enteringQueue.addCustomer(customer101);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        producer.start();

        // TODO#3-12 2초 대기 후 enteringQueue.getCustomer() 호출해서 소비할 수 있도록 consumer Thread를 구현합니다.
        Thread consumer = new Thread(() -> {
            try{
                Thread.sleep(2000);
                enteringQueue.getCustomer();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });
        consumer.start();
        // TODO#3-13 producer 또는 consumer 실행 중이라면 대기합니다. yield()를 이용해서 구현하세요.
        while (producer.isAlive() || consumer.isAlive()) {
            Thread.yield();
        } // TODO#3-14 producer 또는 consumer가 실행 중이라면 대기합니다. yield()를 이용해서 구현하세요.
        int actual = enteringQueue.getQueueSize();
        // TODO#3-15 enteringQueue의 size가 100인지 검증합니다.
        assertEquals(100,actual);
    }
}

