package com.anytec.babytun.dao;

import com.anytec.babytun.entity.GoodsCover;


import java.util.List;

public interface GoodsCoverDAO {
    public List<GoodsCover> findByGoodsId(Long goodsId);
}
