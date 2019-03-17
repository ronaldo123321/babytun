package com.anytec.babytunseckill.service;

import com.anytec.babytunseckill.dao.PromotionSecKillDAO;
import com.anytec.babytunseckill.entity.PromotionSecKill;
import com.anytec.babytunseckill.exception.SecKillException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PromotionSecKillService {

    @Resource
    private PromotionSecKillDAO promotionSecKillDAO;

    @Resource
    private RedisTemplate redisTemplate;

    public void processSecKill(Long psId,String userId,Integer num) throws SecKillException {

        PromotionSecKill ps = promotionSecKillDAO.findById(psId);
        if(ps.getStatus() == 0){
            throw  new SecKillException("秒杀活动还未开始");
        }else if(ps.getStatus() == 2){
            throw  new SecKillException("秒杀活动已经结束");
        } else if(ps == null){
            throw new SecKillException("秒杀活动不存在！");
        }

         Integer goodsId = (Integer) redisTemplate.opsForList().leftPop("seckill:count:"+ps.getPsId());
         if(goodsId != null){

             boolean isExisted = redisTemplate.opsForSet().isMember("seckill:users:"+ps.getPsId(),userId);
             if(!isExisted){
                 System.out.println("恭喜,"+userId+"，抢到商品了。");
                 redisTemplate.opsForSet().add("seckill:users:"+ps.getPsId(),userId);
             }else {
                 //做补偿操作，重新将商品数据添加到队列尾部
                 redisTemplate.opsForList().rightPush("seckill:count:"+ps.getPsId(),ps.getGoodsId());
                 throw new SecKillException("抱歉，您已经参加过此活动了~");
             }
         } else {
             throw new SecKillException("抱歉该商品已经被抢光了，请下次再来~");
         }

    }

}
