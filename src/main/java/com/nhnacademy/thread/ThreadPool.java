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

package com.nhnacademy.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ThreadPool {

    //thread-pool 기본 size
    private static final int DEFAULT_POOL_SIZE = 10;

    //thread pool size
    private int poolSize;

    //thread에 의해서 실행될 Runnable 구현체
    private Runnable runnable;

    //thread-pool에 생성된 thread list
    private List<Thread> threadList;

    public ThreadPool(Runnable runnable){
        //TODO#8-1-1 default 생성자 구현, poolSize = DEFAULT_POOL_SIZE를 사용합니다.
        this(DEFAULT_POOL_SIZE, runnable);
    }

    public ThreadPool(int poolSize, Runnable runnable) {
        // TODO#8-1-2 Thread Pool Size < 0 이라면 IllegalArgumentException이 발생합니다.
        if( poolSize < 0 ){
            throw new IllegalArgumentException();
        }

        // TODO#8-1-3 runnable == null 이면 IllegalArgumentException이 발생합니다.
        if(runnable == null ){
            throw new IllegalArgumentException();
        }

        // TODO#8-1-4 runnable이 Runnable의 구현체가 아니라면 IllegalArgumentException이 발생합니다.
        if(!(runnable instanceof Runnable)){
            throw new IllegalArgumentException();
        }

        // TODO#8-1-5 poolSize, runnable, threadList 초기화
        this.poolSize = poolSize;
        this.runnable = runnable;
        this.threadList = new ArrayList<>();
        createThread();
    }

    private synchronized void createThread(){
         /* TODO#8-1-6 Thread 생성
          - Thread가 생성되는 과정은 동기화되어야 합니다.
          - mutex, semaphore, synchronized 등등.. 적절히 구현합니다.
        */
            for (int i = 0; i < poolSize; i++) {
                threadList.add(new Thread(runnable));
            }
        }



    public synchronized void start(){
        // TODO#8-1-7 생성된 Thread를 시작합니다.
        for(int i=0;i<poolSize;i++) {
            threadList.get(i).start();
        }
    }

    public synchronized void stop() throws InterruptedException {
        /* TODO#8-1-8 interrupt()를 실행해서 Thread를 종료합니다.
            - Thread가 종료되는 과정에서 동기화되어야 합니다.
            - 우선 모든 Thread interrupt 호출
         */

        for(int i=0;i<poolSize;i++) {
            threadList.get(i).interrupt();
        }


        // TODO#8-1-9 join()를 이용해서 모든 Thread가 종료될 때까지 대기 상태로 만듭니다.
        for(int i=0;i<poolSize;i++) {
            try {
                threadList.get(i).join();
            } catch(InterruptedException e) {
                log.error("Thread join 중 예외 발생", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}
