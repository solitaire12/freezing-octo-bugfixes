package com.org.tang.extendview;

import java.io.Serializable;
import java.util.List;

public class OrderState implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 9082299375869096075L;
    private String orderId;// 订单id
    private int payState;// 订单状�?
    private int dealState;// 付款状�?
    private float orderMoney;// 订单金额
    // private float total;// 订单金额
    private String orderTime;// 订单下单时间
    private int channel;// 区分线上线下渠道的订�?默认为线�? 0 线上 1 线下


    public String getOrderId() {
	return orderId;
    }

    public void setOrderId(String orderId) {
	this.orderId = orderId;
    }

    public int getPayState() {
	return payState;
    }

    public void setPayState(int payState) {
	this.payState = payState;
    }

    public int getDealState() {
	return dealState;
    }

    public void setDealState(int dealState) {
	this.dealState = dealState;
    }

    public float getOrderMoney() {
	return orderMoney;
    }

    public void setOrderMoney(float orderMoney) {
	this.orderMoney = orderMoney;
    }

    public String getOrderTime() {
	return orderTime;
    }

    public void setOrderTime(String orderTime) {
	this.orderTime = orderTime;
    }

    public int getChannel() {
	return channel;
    }

    public void setChannel(int channel) {
	this.channel = channel;
    }

}
