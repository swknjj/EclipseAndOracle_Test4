package com.example.test4.model;

import java.sql.*;

// DB 접속을 위한 Connectio, 접속 종료 등을 위한 close 메서드 등을 정의하고 있는 클래스
// DAO 인터페이스를 구현하고 있음.
public class DAOBase implements DAO {
    public Connection getConnection() throws SQLException {
        String jdbc_driver = "oracle.jdbc.OracleDriver";
        String db_url = "jdbc:oracle:thin:@localhost:1521:XE";
        try {
            Class.forName(jdbc_driver);
            Connection conn =
                    DriverManager.getConnection(db_url, "user1", "1234");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeDBResources(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void closeDBResources(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void closeDBResources(PreparedStatement pstmt, Connection conn) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
