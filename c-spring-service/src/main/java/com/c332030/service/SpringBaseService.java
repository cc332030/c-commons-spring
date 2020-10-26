package com.c332030.service;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Description: BaseSpringService
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
public abstract class SpringBaseService extends BaseService {

    private static final Object EMPTY_OBJECT = new Object();

    @Autowired
    protected ApplicationContext applicationContext;

    private Object thisProxy = null;

    @SuppressWarnings("unchecked")
    protected <E> E currentProxy() {

        if(EMPTY_OBJECT == thisProxy) {
            return null;
        }

        if(null == thisProxy) {
            synchronized (this) {
                if(null == thisProxy) {

                    thisProxy = EMPTY_OBJECT;
                    try {
                        thisProxy = applicationContext.getBean(getClass());
                    } catch (NoSuchBeanDefinitionException ignored) {
                    } catch (Throwable e) {
                        log.error("get proxy instance failure", e);
                    }
                }
            }
        }
        return (E) thisProxy;
    }
}
