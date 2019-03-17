package com.anytec.babytunseckill.controller;

import com.anytec.babytunseckill.entity.PromotionSecKill;
import com.anytec.babytunseckill.exception.SecKillException;
import com.anytec.babytunseckill.service.PromotionSecKillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SecKillController {


    @Resource
    PromotionSecKillService promotionSecKill;

    @RequestMapping("/seckill")
    @ResponseBody
    public Map processSecKill(Long psid,String userid) throws SecKillException {
        Map result = new HashMap();
       try {
           promotionSecKill.processSecKill(psid,userid,1);
           result.put("code","0");
           result.put("message","success");
       } catch (Exception e){
           result.put("code","500");
           result.put("message",e.getMessage());
       }
       return result;


    }
}
