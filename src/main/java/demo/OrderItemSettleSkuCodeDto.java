package com.zhuizhi.order.search.center.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * com.zhuizhi.order.search.center.dto
 *
 * @author JM
 * @date 2019-09-23
 */
public class OrderItemSettleSkuCodeDto implements Serializable {

    private static final long serialVersionUID = 5886659519977544817L;

    private String skuCode;
    private BigDecimal totalWeight;
    private BigDecimal totalPspAmt;
    private BigDecimal totalDiscountAmt;
    private BigDecimal totalDiscountAmtAfterSeven;
    private BigDecimal totalDiscountAmtBeforeSeven;
    private BigDecimal totalPlpAmtBeforeSeven;
    private BigDecimal totalPlpAmtAfterSeven;
    private BigDecimal totalPlpAmt;

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getTotalPspAmt() {
        return totalPspAmt;
    }

    public void setTotalPspAmt(BigDecimal totalPspAmt) {
        this.totalPspAmt = totalPspAmt;
    }

    public BigDecimal getTotalDiscountAmt() {
        return totalDiscountAmt;
    }

    public void setTotalDiscountAmt(BigDecimal totalDiscountAmt) {
        this.totalDiscountAmt = totalDiscountAmt;
    }

    public BigDecimal getTotalDiscountAmtAfterSeven() {
        return totalDiscountAmtAfterSeven;
    }

    public void setTotalDiscountAmtAfterSeven(BigDecimal totalDiscountAmtAfterSeven) {
        this.totalDiscountAmtAfterSeven = totalDiscountAmtAfterSeven;
    }

    public BigDecimal getTotalDiscountAmtBeforeSeven() {
        return totalDiscountAmtBeforeSeven;
    }

    public void setTotalDiscountAmtBeforeSeven(BigDecimal totalDiscountAmtBeforeSeven) {
        this.totalDiscountAmtBeforeSeven = totalDiscountAmtBeforeSeven;
    }

    public BigDecimal getTotalPlpAmtBeforeSeven() {
        return totalPlpAmtBeforeSeven;
    }

    public void setTotalPlpAmtBeforeSeven(BigDecimal totalPlpAmtBeforeSeven) {
        this.totalPlpAmtBeforeSeven = totalPlpAmtBeforeSeven;
    }

    public BigDecimal getTotalPlpAmtAfterSeven() {
        return totalPlpAmtAfterSeven;
    }

    public void setTotalPlpAmtAfterSeven(BigDecimal totalPlpAmtAfterSeven) {
        this.totalPlpAmtAfterSeven = totalPlpAmtAfterSeven;
    }

    public BigDecimal getTotalPlpAmt() {
        return totalPlpAmt;
    }

    public void setTotalPlpAmt(BigDecimal totalPlpAmt) {
        this.totalPlpAmt = totalPlpAmt;
    }
}
