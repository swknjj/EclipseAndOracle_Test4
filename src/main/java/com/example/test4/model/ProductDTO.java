package com.example.test4.model;

public class ProductDTO {
    private String code; // 제품코드
    private String pname; // 제품이름
    private int cost; // 제품원가
    private int pnum; // 목표수량
    private int inum; // 재고수량
    private int sale; // 출고가
    private String gcode; // 그룹코드
    private String gname; // 그룹이름

    public ProductDTO() {

    }
    public ProductDTO(String code, String pname, int cost, int pnum, int inum, int sale, String gcode) {
        this.code = code;
        this.pname = pname;
        this.cost = cost;
        this.pnum = pnum;
        this.inum = inum;
        this.sale = sale;
        this.gcode = gcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public int getInum() {
        return inum;
    }

    public void setInum(int inum) {
        this.inum = inum;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String getGcode() {
        return gcode;
    }

    public void setGcode(String gcode) {
        this.gcode = gcode;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "code='" + code + '\'' +
                ", pname='" + pname + '\'' +
                ", cost=" + cost +
                ", pnum=" + pnum +
                ", inum=" + inum +
                ", sale=" + sale +
                ", gcode='" + gcode + '\'' +
                ", gname='" + gname + '\'' +
                '}';
    }
}
