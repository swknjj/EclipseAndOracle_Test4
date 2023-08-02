package com.example.test4.controller;

import com.example.test4.model.ProductDAOImpl;
import com.example.test4.model.ProductDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class ProductServlet extends HttpServlet {

    // get 요청만 받음.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청을 doProcess 메서드로 넘김.(get, post 요청을 모두 doProcess 메서드에서 처리하기 위해)
        try {
            doProcess(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // exception 처리가 이클립스에서 어떻게 되는지 확인!!

    // post 요청만 받음.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청을 doProcess 메서드로 넘김.(get, post 요청을 모두 doProcess 메서드에서 처리하기 위해)
        try {
            doProcess(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // get, post 요청을 모두 받을 수 있도록 메서드 정의
    public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8"); // 한글 깨짐 방지 등을 위해
        String uri = request.getRequestURI(); // 요청한 주소값을 가져옴.(주소가 'localhost:8080/프로젝트이름/요청주소' 라면 '/프로젝트이름/요청주소' 를 가져옴.)
        System.out.println("uri = " + uri); //
        int lastIndex = uri.lastIndexOf("/");  // '/' 가 마지막으로 있는 곳의 index 번호를 가져옴.
        String action = uri.substring(lastIndex); // 위에서 찾은 인덱스 이후의 값을 가져옴. (/요청주소)
        System.out.println("action = " + action); // 잘 나오는지 확인할 것.

        // DB 작업을 위한 DAO 클래스 객체 생성
        ProductDAOImpl productDAO = new ProductDAOImpl();

        // 각 처리가 끝난 후 보여줄 화면이름 또는 주소
        String resultPage = "";

        // 주소값에 따라 메서드 호출
        if (action.equals("/saveForm")) {
            resultPage = "saveProduct.jsp";
        } else if (action.equals("/save")) {
            // 제품저장 처리
            String code = request.getParameter("code");
            String pname = request.getParameter("pname");
            int cost = Integer.parseInt(request.getParameter("cost")); // cost는 정수로 형변환
            int pnum = Integer.parseInt(request.getParameter("pnum"));
            int inum = Integer.parseInt(request.getParameter("inum"));
            int sale = Integer.parseInt(request.getParameter("sale"));
            String gcode = request.getParameter("gcode");

            ProductDTO productDTO = new ProductDTO(code, pname, cost, pnum, inum, sale, gcode);
            int result = productDAO.save(productDTO);

            request.setAttribute("saveResult", result); // 화면에 데이터를 가져가는 경우(스프링에서 model에 담는 것과 비슷함. )
            resultPage = "index.jsp"; // 스프링에서 return 값 지정하는 것과 비슷함.
        } else if (action.equals("/findProductForm")) {
            // 조회용 폼
            resultPage = "findProductForm.jsp";
        } else if (action.equals("/findProduct")) {
            // 조회 처리
            String code = request.getParameter("code");
            ProductDTO productDTO = productDAO.findProduct(code);
            request.setAttribute("product", productDTO);
            resultPage = "findProduct.jsp";
        } else if (action.equals("/updateProduct")) {
            // 수정 처리
            String code = request.getParameter("code");
            String pname = request.getParameter("pname");
            int cost = Integer.parseInt(request.getParameter("cost"));
            int pnum = Integer.parseInt(request.getParameter("pnum"));
            int inum = Integer.parseInt(request.getParameter("inum"));
            int sale = Integer.parseInt(request.getParameter("sale"));
            String gcode = request.getParameter("gcode");

            ProductDTO productUpdateDTO = new ProductDTO(code, pname, cost, pnum, inum, sale, gcode);
            int result = productDAO.update(productUpdateDTO);
            resultPage = "index.jsp";
        } else if (action.equals("/deleteProduct")) {
            // 삭제 처리
            String code = request.getParameter("code");
            int result = productDAO.delete(code);
            resultPage = "index.jsp";
        } else if (action.equals("/findByPriority")) {
            // 우선생산 제품 목록 조회
            List<ProductDTO> priorityList = productDAO.findByPriority();
            request.setAttribute("productList", priorityList);
            resultPage = "findPriorityList.jsp";
        } else if (action.equals("/findByProfit")) {
            // 이익 순위 제품 목록 조회
            List<ProductDTO> profitList = productDAO.findByProfit();
            request.setAttribute("productList", profitList);
            resultPage = "findProfitList.jsp";
        } else if (action.equals("/findByGroup")) {
            // 그룹별 재고 수량 조회
            List<ProductDTO> groupList = productDAO.findByGroup();
            System.out.println("groupList = " + groupList);
            request.setAttribute("productList", groupList);
            resultPage = "findGroupList.jsp";
        }

        // if else문에서 각 작업이 끝난 후 resultPage로 보내주는 역할(dispatch forwarding). 반드시 아래 두줄이 실행되어야 화면이 바뀜.
        RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage); // 보여줄 화면 또는 주소 지정
        dispatcher.forward(request, response); // 포워딩 실행(위에서 지정한 곳으로 request에 담은 데이터도 가져감)
    }
}
