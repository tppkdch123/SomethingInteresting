package org.something.yellow.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * Created by huangshizhe on 2018/8/24
 */

@Component
public class TestScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestScheduler.class);

   /** @Scheduled(fixedRate = 10000)
    public void testScheduler(){
         LOGGER.info("每10s执行一次");
    }

    @Scheduled(cron = "0 35 18 * * ?")
    public void testScheduler2(){
        LOGGER.info("执行这个");
    }  */
}
