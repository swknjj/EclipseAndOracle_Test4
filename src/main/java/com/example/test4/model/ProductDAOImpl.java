package com.example.test4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DAOBase 클래스 상속 & ProductDAO 인터페이스 구현
public class ProductDAOImpl extends DAOBase implements ProductDAO {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    @Override
    public int save(ProductDTO productDTO) throws SQLException {
        // getConnection() 메서드를 부모클래스인 DAOBase가 가지고 있기 때문에 그대로 사용가능.
        con = getConnection();
        String sql = "insert into product values(?,?,?,?,?,?,?)";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, productDTO.getCode());
        pstmt.setString(2, productDTO.getPname());
        pstmt.setInt(3, productDTO.getCost());
        pstmt.setInt(4, productDTO.getPnum());
        pstmt.setInt(5, productDTO.getInum());
        pstmt.setInt(6, productDTO.getSale());
        pstmt.setString(7, productDTO.getGcode());
        int result = pstmt.executeUpdate();
        closeDBResources(pstmt, con); // DAOBase에서 정의한 close용 메서드 호출
        return result;
    }

    @Override
    public ProductDTO findProduct(String code) throws SQLException {
        con = getConnection();
        String sql = "select * from product where code=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, code);
        rs = pstmt.executeQuery();
        ProductDTO productDTO = new ProductDTO();
        if (rs.next()) {
            productDTO.setCode(rs.getString(1));
            productDTO.setPname(rs.getString(2));
            productDTO.setCost(rs.getInt(3));
            productDTO.setPnum(rs.getInt(4));
            productDTO.setInum(rs.getInt(5));
            productDTO.setSale(rs.getInt(6));
            productDTO.setGcode(rs.getString(7));
        }
        closeDBResources(pstmt, con);
        return productDTO;
    }

    @Override
    public List<ProductDTO> findByPriority() throws SQLException {
        con = getConnection();
        String sql = "select pname, (pnum-inum) from product where (inum < (pnum*0.2))";
        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();
        List<ProductDTO> priorityList = new ArrayList<>();
        while (rs.next()) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setPname(rs.getString(1));
            productDTO.setPnum(rs.getInt(2)); // pnum-inum 값
            priorityList.add(productDTO);
        }
        closeDBResources(pstmt, con);
        return priorityList;
    }

    @Override
    public List<ProductDTO> findByProfit() throws SQLException {
        con = getConnection();
        String sql = "select pname, inum*(sale-cost) from product order by inum*(sale-cost) desc";
        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();
        List<ProductDTO> profitList = new ArrayList<>();
        while (rs.next()) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setPname(rs.getString(1));
            productDTO.setSale(rs.getInt(2)); // inum*(sale-cost) 값
            profitList.add(productDTO);
        }
        closeDBResources(pstmt, con);
        return profitList;
    }

    @Override
    public List<ProductDTO> findByGroup() throws SQLException {
        con = getConnection();
        String sql = "select g.gname, sum(p.inum) from product p, groupcode g where p.gcode=g.gcode group by g.gname";
        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();
        List<ProductDTO> groupList = new ArrayList<>();
        while (rs.next()) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setGname(rs.getString(1));
            productDTO.setInum(rs.getInt(2)); // sum(p.inum) 값
            groupList.add(productDTO);
        }
        closeDBResources(pstmt, con);
        return groupList;
    }

    @Override
    public int update(ProductDTO productUpdateDTO) throws SQLException {
        con = getConnection();
        String sql = "update product set pname=?, cost=?, pnum=?, inum=?, sale=?, gcode=? where code=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, productUpdateDTO.getPname());
        pstmt.setInt(2, productUpdateDTO.getCost());
        pstmt.setInt(3, productUpdateDTO.getPnum());
        pstmt.setInt(4, productUpdateDTO.getInum());
        pstmt.setInt(5, productUpdateDTO.getSale());
        pstmt.setString(6, productUpdateDTO.getGcode());
        pstmt.setString(7, productUpdateDTO.getCode());
        int result = pstmt.executeUpdate();
        closeDBResources(pstmt, con);
        return result;
    }

    @Override
    public int delete(String code) throws SQLException {
        con = getConnection();
        String sql = "delete from product where code=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, code);
        int result = pstmt.executeUpdate();
        closeDBResources(pstmt, con);
        return result;
    }
}
