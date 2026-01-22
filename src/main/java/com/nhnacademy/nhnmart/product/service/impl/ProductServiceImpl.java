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

package com.nhnacademy.nhnmart.product.service.impl;

import com.nhnacademy.nhnmart.product.domain.Product;
import com.nhnacademy.nhnmart.product.exception.OutOfStockException;
import com.nhnacademy.nhnmart.product.exception.ProductAlreadyExistsException;
import com.nhnacademy.nhnmart.product.exception.ProductNotFoundException;
import com.nhnacademy.nhnmart.product.parser.ProductParser;
import com.nhnacademy.nhnmart.product.repository.ProductRepository;
import com.nhnacademy.nhnmart.product.service.ProductService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/*
    - ProductServiceImpl은 ProductService의 구현체
    - ProductRepository에 직접 호출해서 수량을 변경하기보다는 Service -> Repository에 접근합니다.
    - Service에서는 Repository로부터 데이터의 CRUD 작업을 수행합니다. 수행하는 과정에서 발생할 수 있는 로직 및 예외를 처리하는 역할을 합니다.

*/
public class ProductServiceImpl implements ProductService {

    // Product 저장소
    private ProductRepository productRepository;

    // Product 파서
    private ProductParser productParser;

    public ProductServiceImpl(ProductRepository productRepository, ProductParser productParser) {
        // TODO#6-5-1 productRepository 또는 productParser가 null이면 IllegalArgumentException이 발생합니다.
        if(productRepository == null || productParser == null){
            throw new IllegalArgumentException();
        }

        // TODO#6-5-2 productRepository, productParser를 초기화합니다.
        this.productRepository = productRepository;
        this.productParser = productParser;
        // TODO#6-5-3 init() 메서드를 호출하여 초기화합니다.
        init();
    }

    private void init(){
        // TODO#6-5-4 productParser.parse()를 호출하고 반환된 List<Product> products를 productRepository를 통해서 Memory 저장소에 저장합니다.
        List<Product> products = productParser.parse();

        for(Product pd : products){
            productRepository.save(pd);
        }
    }

    @Override
    public Product getProduct(long id) {
        /* TODO#6-5-5 id에 해당되는 Product를 반환합니다.
            - Product가 존재하지 않는다면 ProductNotFoundException이 발생합니다.
        */
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

    }

    @Override
    public void saveProduct(Product product) {
        /* TODO#6-5-6 Product를 저장합니다.
           - Product ID에 해당되는 제품이 이미 존재한다면 ProductAlreadyExistsException이 발생합니다.
        */
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        /* TODO#6-5-7 id에 해당되는 Product를 삭제합니다.
            - id에 해당되는 제품이 존재하지 않는다면 ProductNotFoundException이 발생합니다.
        */
        if(!productRepository.existById(id)){
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public long getTotalCount() {
        // TODO#6-5-8 전체 Product의 수를 반환합니다.
        return productRepository.count();
    }

    @Override
    public void updateQuantity(long id, int quantity) {
        /* TODO#6-5-9 id에 해당되는 제품의 수량을 수정합니다.
            - id에 해당되는 제품이 존재하지 않는다면 ProductNotFoundException이 발생합니다.
        */
        if(!productRepository.existById(id)){
            throw new ProductNotFoundException(id);
        }
        productRepository.updateQuantityById(id,quantity);
    }

    @Override
    public void pickProduct(long id, int quantity) {
        /* TODO#6-5-10 제품을 장바구니에 담습니다.
            - 제품의 수량이 parameter로 전달된 quantity보다 작다면 OutOfStockException이 발생합니다.
            - updateQuantity 메서드를 호출해서 quantity만큼 차감한 수량으로 변경합니다.
            - 조회 : getProduct(id)
            - 수량 변경 : updateQuantity(id, product.getQuantity()-quantity)
         */
        Product pd = getProduct(id);
        if(pd.getQuantity() < quantity){
            throw new OutOfStockException(id);
        }

        productRepository.updateQuantityById(id, pd.getQuantity()-quantity);
    }

    @Override
    public int returnProduct(long id, int quantity) {
        /* TODO#6-5-11 장바구니에 담았던 quantity(수량)만큼 제품 저장소에 반납합니다.
            - updateQuantity 메서드를 호출해서 quantity만큼 증가한 수량으로 변경합니다.
            - 합산된 수량을 반환합니다.
            - 조회 : getProduct(id)
            - 수량 변경 : updateQuantity(id, product.getQuantity()+quantity)
         */
        Product pd = getProduct(id);

        productRepository.updateQuantityById(id, pd.getQuantity()+quantity);
        return pd.getQuantity()+quantity;
    }

}
