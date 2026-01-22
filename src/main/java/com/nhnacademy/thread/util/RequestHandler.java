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


package com.nhnacademy.thread.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class RequestHandler implements Runnable {

    // 요청 대기열
    private RequestChannel channel;

    public RequestHandler(RequestChannel channel) {
        // TODO#8-3-1 RequestChannel == null이면 IllegalArgumentException이 발생합니다.
        if(channel == null){
            throw new IllegalArgumentException();
        }
        // TODO#8-3-2 channel을 초기화합니다.
        this.channel = channel;
    }

    @Override
    public void run() {
/* TODO#8-3-3 while 조건문을 수정하세요
            - Interrupt가 발생하면 while문이 종료되면서 Thread를 빠져나가게 됩니다.
         */
        while(!Thread.currentThread().isInterrupted()){
            try{
                // TODO#8-3-4 channel.getRequest()를 호출해서 execute() 메서드를 실행합니다.
                channel.getRequest().execute();

                Thread.sleep(100);
            }catch (InterruptedException e){
                // TODO#8-3-5 InterruptedException이 발생하면 interrupted flag 값이 false 상태로 설정됩니다. 'Thread.currentThread().interrupt()'를 호출하여 다시 true 상태로 변경 합니다.
                // 상위 레벨의 다른 코드 또는 Thread가 이 Thread가 인터럽트되었음을 인지할 수 있습니다.
                Thread.currentThread().interrupt();
                // 종료될 때 필요한 코드가 있다면 작성합니다.
                log.debug("RequestHandler error : {}",e.getMessage(),e);
            }
        }
    }

}

