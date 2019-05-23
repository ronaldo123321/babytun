package com.anytec.babytun.controller;

import com.anytec.babytun.entity.Goods;
import com.anytec.babytun.service.GoodsService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@Slf4j
public class GoodController {

    @Resource
    private GoodsService goodsService;

    @Value("${server.port}")
    private String port;


    @GetMapping("/goods")
    public ModelAndView showGoods(Long gid){
        log.info("port={}",port);
        ModelAndView mv = new ModelAndView("/good1");
        Goods goods = goodsService.getGoods(gid);
        mv.addObject("port",port);
        mv.addObject("goods",goods);
        mv.addObject("covers",goodsService.findCovers(gid));
        mv.addObject("details", goodsService.findDetails(gid));
        mv.addObject("params", goodsService.findParams(gid));
        return mv;

    }

    @GetMapping("/login")
    @ResponseBody
    public String login(String user, WebRequest request){
        request.setAttribute("user",user,WebRequest.SCOPE_SESSION);
        return "port:"+port+"login success";
    }

    @GetMapping("/check")
    @ResponseBody
    public String check(WebRequest request){
        String user = (String) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        if(user != null){
            return "port:"+ port + "login success";
        } else {
            return "port:"+port + "redirect to login!";
        }
    }

}
