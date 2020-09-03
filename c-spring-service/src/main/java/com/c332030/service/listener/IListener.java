package com.c332030.service.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * <p>
 * Description: ITaskListener
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
public interface IListener<E extends ApplicationEvent> extends ApplicationListener<E> {

}
