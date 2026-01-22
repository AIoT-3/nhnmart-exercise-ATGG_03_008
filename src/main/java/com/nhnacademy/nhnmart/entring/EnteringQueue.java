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

import java.util.LinkedList;
import java.util.Queue;

/**
 * NHNMart 입장 대기열을 구현합니다.
 * 대기열은 최대 100명까지 가능합니다.
 */

@Slf4j
public class EnteringQueue {

    //queue를 이용해서 mart 입장 대기열을 구현합니다.
    private Queue<Customer> queue;

    //기본 대기열 QueueSize = 100명
    private static final int DEFAULT_CAPACITY = 100;
    private int capacity;
    public EnteringQueue(){
        // TODO#3-1 기본 생성자 구현, capacity = DEFAULT_CAPACITY입니다.
        capacity = DEFAULT_CAPACITY;
        this.queue = new LinkedList<>();
    }

    public EnteringQueue(int capacity) {
        // TODO#3-2 capacity <= 0이면 IllegalArgumentException이 발생합니다.
        if(capacity <= 0){
            throw new IllegalArgumentException();
        }
        // TODO#3-3 capacity와 queue를 초기화합니다.
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public synchronized void addCustomer(Customer customer) throws InterruptedException {
        /* TODO#3-4 대기열에 고객을 추가하는 메서드를 구현합니다.
           - queue.size() >= capacity이면 대기할 수 있도록 구현합니다.
        */
        while (queue.size() >= capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // TODO#3-5 queue에 고객을 추가하고 대기하고 있는 Thread를 깨웁니다.
        queue.add(customer);
        notifyAll();
    }

    public synchronized Customer getCustomer() throws InterruptedException {
        // TODO#3-6 queue가 비어 있다면 대기합니다.
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // TODO#3-7 queue에서 Customer를 반환합니다. 대기하고 있던 Thread를 깨웁니다.
        try{
            return queue.remove();
        }finally {
            notifyAll();
        }
    }

    // TODO#3-8 queue size를 반환합니다.
    public int getQueueSize(){
        return queue.size();
    }

}
