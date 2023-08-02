<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-08-05
  Time: 오전 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>생산관리 등록화면</h2>
<form action="save" method="post">
    제품코드: <input type="text" name="code"> <br>
    제품이름: <input type="text" name="pname"> <br>
    제품원가: <input type="text" name="cost"> <br>
    목표수량: <input type="text" name="pnum"> <br>
    재고수량: <input type="text" name="inum"> <br>
    출고가: <input type="text" name="sale"> <br>
    그룹이름: <select name="gcode">
                <option value="A">컴퓨터</option>
                <option value="B">냉장고</option>
                <option value="C">냉장고소모품</option>
            </select>
    <input type="submit" value="등록"><br>
    <input type="button" value="메인화면" onclick="location.href='index.jsp'">
</form>
</body>
</html>
