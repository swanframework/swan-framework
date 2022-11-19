package com.swan.core.utils;

/** 线程工具类
 * @author zongf
 * @since 2021-05-14
 */
public final class ThreadUtil {

    /** 线程休眠
     * @param mSecond 单位 毫秒
     * @author zongf
     * @since 2021-05-14
     */
    public static final void sleep(Long mSecond) {
        try {
            Thread.sleep(mSecond);
        } catch (InterruptedException e) {
        }
    }

    /** 线程休眠
     * @param second 单位 秒
     * @author zongf
     * @since 2021-05-14
     */
    public static final void sleep(Integer second) {
        sleep(1000l * second);
    }

    /** 判断是否是当前线程
     * @param threadId 线程id
     * @return boolean
     * @author zongf
     * @since 2021-05-17
     */
    public static final boolean isCurrentThread(Long threadId) {
        return Long.valueOf(Thread.currentThread().getId()).equals(threadId);
    }

}
