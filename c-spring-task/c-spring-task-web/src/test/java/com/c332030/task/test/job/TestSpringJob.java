package com.c332030.task.test.job;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Description: JobTest
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
@Component
@EnableScheduling
public class TestSpringJob {

    // @SneakyThrows
    // @Scheduled(cron = "*/5 * * * * ?")
    // public void concurrentJobTest() {
    //
    //     var i = 7;
    //     var random = new Random().nextInt();
    //
    //     while (i > 0) {
    //         log.info("random: {}, i: {}", random, i--);
    //         Thread.sleep(1000L);
    //     }
    // }
}
