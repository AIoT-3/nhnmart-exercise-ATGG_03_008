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

public class App {
    public static void main(String[] args) {

        //TODO#5-1 capacity를 100으로 enteringQueue를 초기화합니다.
        EnteringQueue enteringQueue = new EnteringQueue(100);

        //TODO#5-2 customerGenerator를 이용해서 thread를 생성합니다.
        CustomerGenerator customerGenerator = new CustomerGenerator(enteringQueue);
        Thread enteringThread = new Thread(customerGenerator);


        //TODO#5-3 enteringThread의 이름을 'entering-thread'로 설정, enteringThread를 시작합니다.
        enteringThread.setName("entering-thread");
        enteringThread.start();
    }
}
