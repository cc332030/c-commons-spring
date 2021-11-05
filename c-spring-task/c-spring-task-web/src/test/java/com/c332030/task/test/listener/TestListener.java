package com.c332030.task.test.listener;

import javax.annotation.Nonnull;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Description: TestListener
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
@Component
public class TestListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(@Nonnull ContextRefreshedEvent event) {
        log.info("onApplicationEvent");
    }
}
