package com.anytec.babytun.controller;

import com.anytec.babytun.entity.Goods;
import com.anytec.babytun.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class GoodController {

    @Resource
    private GoodsService goodsService;


    @GetMapping("/goods")
    public ModelAndView showGoods(Long gid){
        ModelAndView mv = new ModelAndView("/goods");
        Goods goods = goodsService.getGoods(gid);
        mv.addObject("goods",goods);
        mv.addObject("covers",goodsService.findCovers(gid));
        mv.addObject("details", goodsService.findDetails(gid));
        mv.addObject("params", goodsService.findParams(gid));
        return mv;

    }
}
