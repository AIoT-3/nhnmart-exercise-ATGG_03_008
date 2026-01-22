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

package com.nhnacademy.customer.generator;

import com.nhnacademy.nhnmart.entring.EnteringQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

class CustomerGeneratorTest {
    CustomerGenerator customerGenerator;
    EnteringQueue enteringQueue;

    @BeforeEach
    void setUp() {
        // TODO#4-5 enteringQueue 대기열을 capacity = 5로 초기화합니다.
        enteringQueue = new EnteringQueue(5);
        // TODO#4-6 enteringQueue를 이용해서 CustomerGenerator 객체를 생성합니다.
        customerGenerator = new CustomerGenerator(enteringQueue);
    }

    @Test
    @DisplayName("enteringQueue is null")
    void constructorTest(){
        // TODO#4-7 enteringQueue == null 이면 IllegalArgumentException 발생하는지 검증합니다.

        assertThrows(IllegalArgumentException.class, () ->{
            new CustomerGenerator(null);
        });
    }

    @Test
    @DisplayName("10초 동안 customer 객체가 enteringQueue 대기열 등록")
    void generatorTest() throws InterruptedException {

        // TODO#4-8 customerGenerator를 이용해서 customerGeneratorThread 초기화하고, 실행합니다.
        Thread customerGeneratorThread = new Thread(customerGenerator);
        customerGeneratorThread.start();
        // TODO#4-9 10초 대기합니다.
        Thread.sleep(10000);
        // TODO#4-10 customerGeneratorThread를 종료합니다.
        customerGeneratorThread.interrupt();
        customerGeneratorThread.join();
        // assertions.assertAll 참고 ( ctrl + click or cmd + click)
        // https://junit.org/junit5/docs/current/api/org.junit.jupiter.api/org/junit/jupiter/api/Assertions.html#assertAll(java.lang.String,org.junit.jupiter.api.function.Executable...)

        Assertions.assertAll(
                ()->{
                    // TODO#4-11 interrupt 발생 시 customerGeneratorThread의 상태가 TERMINATED 상태인지 검증합니다.
                    assertEquals(Thread.State.TERMINATED, customerGeneratorThread.getState());
                },
                ()->{
                    // TODO#4-12 enteringQueue(대기열) 최대 Queue Size가 5 <-- 10초 동안 최대 5명의 고객이 대기열에 등록되었는지 검증합니다.
                    assertTrue(enteringQueue.getQueueSize() <= 5);
                }
        );
    }

}