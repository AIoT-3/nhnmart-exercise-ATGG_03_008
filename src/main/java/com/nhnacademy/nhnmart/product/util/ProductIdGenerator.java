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

package com.nhnacademy.nhnmart.product.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Product ID를 생성하는 유틸리티
 */
public final class ProductIdGenerator {

    // TODO#6-3-1 idGenerator를 0으로 초기화합니다.
    private static AtomicLong idGenerator = new AtomicLong(0);

    public static long getNewId(){
        // TODO#6-3-2 idGenerator를 1 증가시키고 반환합니다.
        return idGenerator.incrementAndGet();
    }
}
