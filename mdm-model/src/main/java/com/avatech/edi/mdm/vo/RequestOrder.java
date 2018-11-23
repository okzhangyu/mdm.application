package com.avatech.edi.mdm.vo;

/**
 * 已申请的BOM数量
 */
public class RequestOrder {

    private Integer baseLineNum;

    private String itemCode;

    private Double quantity;

    public String getItemCode() {
        return itemCode;
    }

    public Integer getBaseLineNum() {
        return baseLineNum;
    }

    public void setBaseLineNUum(Integer baseLineNum) {
        this.baseLineNum = baseLineNum;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
