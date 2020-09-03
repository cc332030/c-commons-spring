package com.c332030.service.listener.impl;

import javax.annotation.Nonnull;

import org.springframework.context.ApplicationEvent;

import com.c332030.service.listener.IListener;

/**
 * <p>
 * Description: AbstractTaskListener
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public abstract class AbstractListener<E extends ApplicationEvent> implements IListener<E> {

    @Override
    public abstract void onApplicationEvent(@Nonnull E event);

}
