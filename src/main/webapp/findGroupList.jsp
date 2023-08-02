<%@ page import="java.util.List" %>
<%@ page import="com.example.test4.model.ProductDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table, tr, th, td {
            border: black 1px solid;
        }
    </style>
</head>
<body>
    <h2>그룹별 재고 수량 조회</h2>
<table>
    <tr>
        <th>그룹이름</th>
        <th>재고 수량</th>
    </tr>
    <%
        // controller에서 request.setAttribute로 담은 데이터를 가져온다.
        // request.getAttribute는 Object 이기 때문에 강제 형변환 해야함.
        List<ProductDTO> productList = (List<ProductDTO>) request.getAttribute("productList");
        for (ProductDTO productDTO: productList) {
    %>
    <tr>
        <td><%=productDTO.getGname()%></td>
        <td><%=productDTO.getInum()%></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
