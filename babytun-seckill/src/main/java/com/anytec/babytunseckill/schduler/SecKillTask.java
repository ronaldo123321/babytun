package com.anytec.babytunseckill.schduler;

import com.anytec.babytunseckill.dao.PromotionSecKillDAO;
import com.anytec.babytunseckill.entity.PromotionSecKill;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SecKillTask {

     @Resource
     private PromotionSecKillDAO promotionSecKillDAO;

     @Resource
     private RedisTemplate redisTemplate;


     @Scheduled(cron = "0/5 * * * * ?")
     public void startSecKill(){

         List<PromotionSecKill> list = promotionSecKillDAO.findUnstartSecKill();
         for(PromotionSecKill ps: list){
             System.out.println("秒杀活动已启动,psId=" + ps.getPsId());
             //删除掉以前重复活动缓存任务
             redisTemplate.delete("seckill:count:"+ ps.getPsId());
             //有几个库存商品，则初始化几个list对象
             for (int i = 0; i < ps.getPsCount(); i++) {
                 redisTemplate.opsForList().rightPush("seckill:count:" + ps.getPsId(),ps.getGoodsId());
             }
             ps.setStatus(1);
             promotionSecKillDAO.update(ps);
         }


     }

     @Scheduled(cron = "0/5 * * * * ?")
     public  void  endSeckill(){
        List<PromotionSecKill> psList = promotionSecKillDAO.findExpireSecKill();
        for (PromotionSecKill ps: psList) {
            System.out.println(ps.getPsId() + "秒杀活动已结束");
            ps.setStatus(2);
            promotionSecKillDAO.update(ps);
            redisTemplate.delete("seckill:count:"+ps.getPsId());
        }
     }
}
