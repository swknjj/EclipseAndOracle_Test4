## 프로젝트 기능
1. 제품등록 
2. 제품조회, 수정, 삭제 
   1. 제품조회, 수정, 삭제 기능을 한화면(findProductForm.jsp)에서 모두 요청함. 
   2. 서로 다른 요청을 위해 formaction 속성 사용. 
3. 각 조건에 따른 조회 화면
   1. 우선 생산 제품 조회
   2. 이익 순위 조회
   3. 그룹별 재고 수량 조회

## 프로젝트 특징
1. 본 예제는 controller를 사용함. (Spring Framework와 비슷한 구조라 생각하면 됨.)
   1. jsp 에서 특정 주소로 요청을 하면 controller에서 요청을 받고 ProductDAOImpl 클래스 객체 메서드를 호출함. 
   2. 호출 결과를 리턴 받아서 request.setAttribute()를 이용하여 데이터를 담고 resultPage를 지정함. 
   3. 각 jsp에서는 가져온 데이터를 화면에 출력함. 
2. DAO 관련 클래스 설명(클래스 갯수가 많지만 흐름을 잘 따라가면 됩니다.) 
   1. DAO Interface
      1. getConnection 메서드를 가지고 있음. 
   2. DAO Base Class
      1. DAO 인터페이스의 구현클래스로 getConnection 메서드의 실행블록을 정의함. 
      2. Connection, PreparedStatement, ResultSet 객체를 종료하기 위한 메서드 정의하고 있음. 
   3. ProductDAO Interface
      1. CRUD 작업 등을 위한 메서드 구조 정의하고 있음. 
   4. ProductDAOImpl Class(실제 필요한 내용을 담고 있는 클래스)
      1. ProductDAO Interface를 구현한 클래스로 구체적인 실행내용을 가지고 있음. 
      2. 컨트롤러에서는 이 클래스에 대한 객체를 만들어서 호출하고 있음. 

## 사용 쿼리
1. 본 예제는 클래스 구조는 조금 복잡할 수 있지만 쿼리는 단순함. 
2. 특히 insert 쿼리를 DB에 직접 수행하게 되면 commit을 반드시 해야함. 

```
-- 생산관리 테이블 생성(외래키 고려)
drop table product;
create table product(
    code char(3) not null primary key,
    pname varchar2(30) not null, -- 문제 요구사항대로 20으로 하면 냉장고냉장박스 저장 불가 30으로 변경 
    cost number,
    pnum number,
    inum number,
    sale number,
    gcode char(3) not null, -- 문제 요구사항대로 unique 지정하면 안됨. 
    -- 외래키 지정 문법 constraint [외래키이름] foreign key(현재테이블에서 지정할 컬럼명) references 부모테이블(참조할 컬럼명) on delete cascade(부모컬럼 삭제시 자식 데이터도 삭제)
    constraint fk_product foreign key(gcode) references groupcode(gcode) on delete cascade -- 외래키 설정(groupcode 테이블의 gcode 참조), fk_product는 외래키 이름
    );
-- 샘플 데이터 
select * from product;
insert into product values('A01', '컴퓨터DVD', 1500, 300, 50, 2000, 'A');
insert into product values('A02', '컴퓨터CDROM', 1200, 500, 100, 1500, 'A');
insert into product values('A03', '컴퓨터모니터', 50000, 400, 50, 55000, 'A');
insert into product values('A04', '컴퓨터프린터', 20000, 400, 100, 23000, 'A');
insert into product values('B01', '모바일케이스', 1000, 500, 1200, 900, 'B');
insert into product values('B02', '모바일액정필름', 500, 1000, 2000, 400, 'B');
insert into product values('C01', '냉장고손잡이', 300, 1000, 300, 15000, 'C');
insert into product values('C02', '냉장고도어', 40000, 300, 50, 47000, 'C');
insert into product values('C03', '냉장고냉장박스', 3000, 200, 20, 3500, 'C');
insert into product values('C04', '냉장고냉동박스', 3500, 300, 80, 4000, 'C');
    
-- 그룹코드 테이블 생성 
create table groupcode(
    gcode char(3) not null primary key, -- 문제에서는 unique를 따로 주라고 되어있는데 primary key 이기 때문에 중복이라는 오류발생함.(unique는 주지 않아도 됨)
    gname varchar2(20) not null
    );
-- 샘플 데이터
insert into groupcode values('A', '컴퓨터');
insert into groupcode values('B', '냉장고');
insert into groupcode values('C', '냉장소모품');

-- 우선 생산 제품 조회(재고수량(inum)이 목표수량(pnum)의 20%미만인 제품)
select * from product where (inum < (pnum*0.2));
-- 조회할 값은 제품이름, 생산해야할 수량(목표수량-재고수량)
select pname, (pnum-inum) from product where (inum < (pnum*0.2));

-- 이익 순위 제품 조회(총이익 내림차순 정렬 조회)
-- 이익은 제품별 출고가-원가(cost)를 뺀금액. 총이익은 여기에 수량을 곱함.
select pname, inum*(sale-cost) from product order by inum*(sale-cost) desc; 

-- 그룹별 재고 수량 조회(gname으로 grouping, 재고수량은 inum의 합)
select g.gname, sum(p.inum) from product p, groupcode g where p.gcode=g.gcode group by g.gname;
```