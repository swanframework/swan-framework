package com.swan.log.processor.message;

/** 日志信息处理器
 * @author zongf
 * @since 2022-11-07
 **/
public interface IMessageProcessor {

    /** 处理格式化后的信息.
     * @param formattedMessage 格式化后的信息，以将 {} 替换完
     * @author zongf
     */
    public String process(String formattedMessage);

}
