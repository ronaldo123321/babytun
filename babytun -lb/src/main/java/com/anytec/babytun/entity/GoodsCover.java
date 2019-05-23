package com.anytec.babytun.entity;

import lombok.Data;

@Data
public class GoodsCover {
    private Long gcId;
    private Long goodsId;
    private String gcPicUrl;
    private String gcThumbUrl;
    private Long gcOrder;


}
