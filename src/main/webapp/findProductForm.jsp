<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-08-05
  Time: 오후 2:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>생산관리 조회 & 수정 화면</h2>
    <form action="findProduct" method="post">
        제품코드: <input type="text" name="code" required> <br>
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
        <input type="submit" value="조회">
        <%-- form에 작성한 내용을 다른 주소로 보내고 싶을 때는 formaction 속성 사용 --%>
        <input type="submit" formaction="updateProduct" value="수정">
        <input type="submit" formaction="deleteProduct" value="삭제">
        <input type="button" value="메인화면" onclick="location.href='index.jsp'">
    </form>
</body>
</html>
