package com.swan.minio.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Map;

/** 文件状态
 * @author zongf
 * @since 2021-11-03
 */
@Setter @Getter @Builder
public class ObjectState {

    /** 唯一标识 */
    private String etag;

    /** 字节数, 中文及中文符号占3个字节, 英文及英文符号占1个字节*/
    private long size;

    /** 最后更新时间*/
    private ZonedDateTime lastModified;

    /** 删除标识 */
    private boolean deleteMarker;

    /** 自定义数据 */
    private Map<String, String> userMetadata;

}
