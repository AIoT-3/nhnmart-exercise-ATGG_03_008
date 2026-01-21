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

package com.nhnacademy.customer.cart;

import java.io.Serializable;
import java.util.Objects;

public class CartItem implements Serializable {

    // 제품 아이디
    private long productId;
    // 제품 수량
    private int quantity;

    public CartItem(long productId, int quantity) {
        // TODO#2-6 productId < 0 또는 quantity < 0이면 IllegalArgumentException이 발생합니다.
        if(productId < 0 || quantity < 0){
            throw new IllegalArgumentException();
        }
        this.productId = productId;
        this.quantity = quantity;
        // TODO#2-7 productId, quantity를 초기화합니다.
    }

    public long getProductId() {
        // TODO#2-8 productId를 반환합니다.
        return this.productId;
    }

    public int getQuantity() {
        // TODO#2-9 quantity를 반환합니다.
        return this.quantity;
    }

    // TODO#2-10 (productId, quantity)를 기준으로 객체 비교를 하기 위해 equals()를 구현합니다.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartitem = (CartItem)o;

        if (cartitem.getProductId() != this.getProductId()) return false;
        return cartitem.getQuantity() == this.getQuantity();
    }

    // TODO#2-11 (productId, quantity)를 기준으로 hashCode()를 구현합니다.
    @Override
    public int hashCode() {
        int result = (int) this.getProductId();
        result = 31 * result + this.getQuantity();
        return result;
    }
}
