<%@ page import="com.example.test4.model.ProductDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h2>제품 조회 화면</h2>
    제품코드: ${product.code} <br>
    제품이름: ${product.pname} <br>
    제품원가: ${product.cost} <br>
    목표수량: ${product.pnum} <br>
    재고수량: ${product.inum} <br>
    출고가: ${product.sale} <br>
    그룹코드: ${product.gcode} <br>
    <button onclick="location.href='index.jsp'">메인화면</button>
</body>
</html>
