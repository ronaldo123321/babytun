package com.anytec.babytun.entity;

import lombok.Data;

@Data
public class Goods {


    private Long goodsId;
    private String title;
    private String subTitle;
    private Float originalCost;
    private Float currentPrice;
    private Float discount;
    private Integer isFreeDelivery;
    private Long categoryId;

}
