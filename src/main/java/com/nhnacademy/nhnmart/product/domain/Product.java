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

package com.nhnacademy.nhnmart.product.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * Mart에서 판매되는 제품
 */
public class Product {

    // Product ID
    private long id;

    // 품목
    private String item;

    // 메이커
    private String maker;

    // 스펙
    private String specification;

    // 단위
    private String unit;

    // 가격
    private int price;

    // 수량
    private int quantity;

    public Product(long id, String item, String maker, String specification, String unit, int price, int quantity) {
        // TODO#6-1-1 Product 생성자의 parameter 검증을 통과하지 못한다면 IllegalArgumentException이 발생합니다.
        if(id <= 0 || item == null || item.isEmpty() || maker == null || maker.isEmpty() || specification == null || specification.isEmpty()
                || unit == null || unit.isEmpty() || price <= 0 || quantity < 0 ){
            throw new IllegalArgumentException();
        }

        // TODO#6-1-2 Product attribute를 초기화합니다.
        this.id = id;
        this.item = item;
        this.maker = maker;
        this.specification = specification;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
    }

    public long getId() {
        // TODO#6-1-3 Product ID 반환
        return this.id;

    }

    public String getItem() {
        // TODO#6-1-4 item 반환
        return this.item;
    }

    public String getMaker() {
        // TODO#6-1-5 maker 반환
        return this.maker;
    }

    public String getSpecification() {
        // TODO#6-1-6 specification 반환
        return this.specification;
    }

    public String getUnit() {
        // TODO#6-1-7 unit 반환
        return this.unit;
    }

    public int getPrice() {
        // TODO#6-1-8 price 반환
        return this.price;
    }

    public int getQuantity() {
        // TODO#6-1-9 quantity 반환
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        // TODO#6-1-10 quantity 수정, quantity < 0이면 IllegalArgumentException 발생
        if(quantity <0 ) throw new IllegalArgumentException();
        this.quantity = quantity;
    }

    // TODO#6-1-11 equals를 구현합니다.
    @Override
    public boolean equals(Object o) {
        if ( this == o )return true;
        if( o == null || getClass() != o.getClass()) return false;

        Product pd = (Product) o;

        return id == pd.id
                && price == pd.price
                && quantity == pd.quantity
                && Objects.equals(item, pd.item)
                && Objects.equals(maker, pd.maker)
                && Objects.equals(specification, pd.specification)
                && Objects.equals(unit, pd.unit);
    }


    // TODO#6-1-12 hashCode를 구현합니다.
    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31* result + (item != null ? item.hashCode() : 0);
        result = 31* result + (maker != null ? maker.hashCode() : 0);
        result = 31* result + (specification != null ? specification.hashCode() : 0);
        result = 31* result + (unit != null ? unit.hashCode() : 0);
        result = 31* result + price;
        result = 31* result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", maker='" + maker + '\'' +
                ", specification='" + specification + '\'' +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
