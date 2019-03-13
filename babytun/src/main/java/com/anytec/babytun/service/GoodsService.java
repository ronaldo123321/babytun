package com.anytec.babytun.service;

import com.anytec.babytun.dao.GoodsCoverDAO;
import com.anytec.babytun.dao.GoodsDAO;
import com.anytec.babytun.dao.GoodsDetailDAO;
import com.anytec.babytun.dao.GoodsParamDAO;
import com.anytec.babytun.entity.Goods;
import com.anytec.babytun.entity.GoodsCover;
import com.anytec.babytun.entity.GoodsDetail;
import com.anytec.babytun.entity.GoodsParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsService {

    @Resource
    private GoodsCoverDAO goodsCoverDAO;
    @Resource
    private GoodsDAO goodsDAO;
    @Resource
    private GoodsDetailDAO goodsDetailDAO;
    @Resource
    private GoodsParamDAO goodsParamDAO;

    public Goods getGoods(Long goodsId){
        return goodsDAO.findById(goodsId);
    }
    public List<GoodsCover> findCovers(Long goodsId){
        return goodsCoverDAO.findByGoodsId(goodsId);
    }

    public List<GoodsDetail> findDetails(Long goodsId){
        return  goodsDetailDAO.findByGoodsId(goodsId);
    }

    public List<GoodsParam> findParams(Long goodsId){
        List list =  goodsParamDAO.findByGoodsId(goodsId);
        return list;
    }
}
