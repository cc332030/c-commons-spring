package com.c332030.task.test.job;

import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Description: TestQuartzJob
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
@Service
public class TestQuartzJob {

    @SneakyThrows
    public void execute() {

        var i = 7;
        var random = new Random().nextInt();

        while (i > 0) {
            log.info("random: {}, i: {}", random, i--);
            Thread.sleep(1000L);
        }
    }
}
