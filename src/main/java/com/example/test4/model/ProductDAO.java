package com.example.test4.model;

import java.sql.SQLException;
import java.util.List;

// CRUD 작업 등을 위한 메서드를 정의하고 있는 인터페이스(문제에서는 ProductDAO라고 정의함)

public interface ProductDAO {
    // 문제에서는 ProductVO라고 했으나 DTO 로 사용해도 괜찮음.
    int save(ProductDTO productDTO) throws SQLException;
    ProductDTO findProduct(String code) throws SQLException;
    List<ProductDTO> findByPriority() throws SQLException; // 우선 생산 제품 리스트
    List<ProductDTO> findByProfit() throws SQLException; // 이익 순위 제품 리스트
    List<ProductDTO> findByGroup() throws SQLException; // 그룹별 재고 수량 리스트
    int update(ProductDTO productDTO) throws SQLException;
    int delete(String code) throws SQLException;
}
