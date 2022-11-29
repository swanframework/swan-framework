package com.swan.core.listener;

import com.swan.core.utils.JacksonUtil;
import com.swan.core.utils.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.*;

/** 事件派发
 * @author zongf
 * @since 2022-11-29
 **/
@Slf4j
public class EventMulticaster {

    private Map<Class<? extends Event> , List<IEventListener>> listenerMap;

    private ThreadPoolTaskExecutor executor;

    public EventMulticaster(List<IEventListener> eventListeners) {
        if (Objects.nonNull(eventListeners) && !eventListeners.isEmpty()) {
            listenerMap = ListUtil.groupToMap(eventListeners, IEventListener::eventType);
        }else {
            listenerMap = Collections.emptyMap();
        }

        executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setThreadGroupName("eventMulticaster-");
        executor.initialize();
    }

    /** 同步发送事件
     * @param event
     * @since 2022-11-29
     */
    public void publish(Event event) {
        if (Objects.isNull(event)) {
            return;
        }
        List<IEventListener> list = listenerMap.get(event.getClass());
        for (IEventListener eventListener : list) {
            try {
                eventListener.invoke(event);
            } catch (Throwable throwable) {
                log.error("事件派发失败, 事件:{}, listener:{}", JacksonUtil.toString(event), eventListener.getClass(), throwable);
            }
        }
    }

    /** 异步派发事件
     * @param event 事件
     * @since 2022-11-29
     */
    public void asyncPublish(Event event) {
        if (Objects.isNull(event)) {
            return;
        }
        List<IEventListener> list = listenerMap.get(event.getClass());
        for (IEventListener eventListener : list) {
            try {
                executor.submit(() -> eventListener.invoke(event));
            } catch (Throwable throwable) {
                log.error("事件派发失败, 事件:{}, listener:{}", JacksonUtil.toString(event), eventListener.getClass(), throwable);
            }
        }
    }


}
