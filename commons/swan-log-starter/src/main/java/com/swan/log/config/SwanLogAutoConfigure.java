package com.swan.log.config;

import com.swan.log.processor.args.ArgOverrideProcessor;
import com.swan.log.processor.message.MessageOverrideProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/** 自动化配置
 * @author zongf
 * @since 2022-11-07
 **/
@Configuration
@Import({SwanLogProperty.class, MessageOverrideProcessor.class, ArgOverrideProcessor.class})
public class SwanLogAutoConfigure {


}
