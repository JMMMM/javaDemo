package com.zhuizhi.sc.center.dto.receive;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReceiveSkuDto implements Serializable {

    private String skuCode;
    private String receiveNo;
    private String receiveUnit;
    private BigDecimal receivePrice;
    private BigDecimal receiveQty;

    private Boolean orderNotSale; // 可订不可售

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getReceiveUnit() {
        return receiveUnit;
    }

    public void setReceiveUnit(String receiveUnit) {
        this.receiveUnit = receiveUnit;
    }

    public BigDecimal getReceivePrice() {
        return receivePrice;
    }

    public void setReceivePrice(BigDecimal receivePrice) {
        this.receivePrice = receivePrice;
    }

    public BigDecimal getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(BigDecimal receiveQty) {
        this.receiveQty = receiveQty;
    }

    public Boolean getOrderNotSale() {
        return orderNotSale;
    }

    public void setOrderNotSale(Boolean orderNotSale) {
        this.orderNotSale = orderNotSale;
    }
}
