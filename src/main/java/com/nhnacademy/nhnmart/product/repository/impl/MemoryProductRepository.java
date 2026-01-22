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

package com.nhnacademy.nhnmart.product.repository.impl;

import com.nhnacademy.nhnmart.product.domain.Product;
import com.nhnacademy.nhnmart.product.repository.ProductRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MemoryProductRepository implements ProductRepository {
    /*
        MemoryProductRepository를 구현합니다.
        - List, Map, Set 등 Memory 기반의 저장소를 사용하여 구현합니다.
        - List, Map, Set의 구현체는 multi Thread 환경에서 Thread Safety해야 합니다.
        - MemoryProductRepository는 파싱한 Product 객체를 저장하는 저장소이며 Product 데이터 관련된 처리를 합니다.
    */

    private ConcurrentMap<Long, Product> productConcurrentMap = new ConcurrentHashMap<>();

    @Override
    public void save(Product product) {
        // TODO#6-4-1 Product 저장
        Objects.requireNonNull(product, "product is null");
        productConcurrentMap.put(product.getId(), product);
    }
    @Override
    public Optional<Product> findById(long id) {
        // TODO#6-4-2 id에 해당되는 Product 조회
        return Optional.ofNullable(productConcurrentMap.get(id));
    };

    @Override
    public void deleteById(long id) {
        // TODO#6-4-3 id에 해당하는 Product 삭제
        productConcurrentMap.remove(id);
    }

    @Override
    public boolean existById(long id) {
        // TODO#6-4-4 id에 해당하는 Product 존재 여부를 체크해서 반환합니다.
        return productConcurrentMap.containsKey(id);
    }

    @Override
    public long count() {
        // TODO#6-4-5 전체 Product 수 반환
        return productConcurrentMap.size();
    }

    @Override
    public int countQuantityById(long id) {
        // TODO#6-4-6 id에 해당되는 Product의 수량 반환(즉 재고 확인)\

        return productConcurrentMap.get(id).getQuantity();
    }

    @Override
    public void updateQuantityById(long id, int quantity) {
        // TODO#6-4-7 id에 해당되는 Product의 수량 변경
        productConcurrentMap.get(id).setQuantity(quantity);
    }

}
