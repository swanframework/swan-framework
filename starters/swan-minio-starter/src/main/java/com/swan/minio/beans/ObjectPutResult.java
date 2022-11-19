package com.swan.minio.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zongf
 * @date 2021-11-03
 */
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class ObjectPutResult {

    private String etag;

    private String version;

}
