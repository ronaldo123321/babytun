package com.anytec.babytun.dao;

import com.anytec.babytun.entity.Goods;

public interface GoodsDAO {

    public Goods findById(Long goodsId);
}
