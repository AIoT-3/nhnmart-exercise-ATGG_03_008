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

import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import com.nhnacademy.customer.domain.Customer;
import com.nhnacademy.nhnmart.entring.EnteringQueue;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 회원생성 후 대기열에 등록합니다.
 */
@Slf4j
public class CustomerGenerator implements Runnable {

    //NhnMart 입장 대기열
    private EnteringQueue enteringQueue;

    //회원 번호 Id 생성
    private AtomicLong atomicId;

    //회원이 보유한 default money
    private final static int DEFAULT_MONEY=10_00000;

    public CustomerGenerator(EnteringQueue enteringQueue) {
        // TODO#4-1 enteringQueue null이면 'IllegalArgumentException'이 발생하는지 검증합니다.
        if(enteringQueue == null){
            throw new IllegalArgumentException();
        }
        // TODO#4-2 enteringQueue, atomicId를 0으로 초기화합니다.
        this.enteringQueue = enteringQueue;
        this.atomicId =  new AtomicLong(0);

    }

    @Override
    public void run() {

        /* TODO#4-4 generate() 메서드를 이용해서 customer를 생성하고 enteringQueue에 등록합니다.
            - while 조건을 수정하세요.
            - 1초 간격으로 회원을 enteringQueue의 대기열에 등록합니다.
        */
        while (true) {
            try {
                enteringQueue.addCustomer(generate());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    private Customer generate(){

                 /* TODO#4-3 Customer 객체를 생성 후 반환합니다.
            - Customer 객체의 id는 atomicId를 사용하여 구현
            - 회원이름은 랜덤으로 생성됩니다.
               - 회원이름 생성 시 https://github.com/Devskiller/jfairy 이용해서 구현합니다.
         */
        Fairy fairy = Fairy.create();


        return new Customer(atomicId.incrementAndGet(), fairy.person().getFullName(), DEFAULT_MONEY);
    }
}
