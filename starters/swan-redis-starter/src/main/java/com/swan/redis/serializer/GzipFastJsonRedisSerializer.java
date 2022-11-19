package com.swan.redis.serializer;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.swan.core.utils.GzipUtil;
import org.springframework.data.redis.serializer.SerializationException;

/** gzip 压缩序列化方式
 * @author zongf
 * @date 2021-05-14
 */
public class GzipFastJsonRedisSerializer extends FastJsonRedisSerializer {

    public GzipFastJsonRedisSerializer() {
        super(String.class);
    }

    @Override
    public byte[] serialize(Object source) throws SerializationException {

        // 先序列化java对象为json字节数组
        byte[] bytes = super.serialize(source);

        // 再将json 字节数组进行gzip压缩
        return GzipUtil.compress(bytes);
    }

    @Override
    public Object deserialize(byte[] source) throws SerializationException {

        // 先解压字节数组为json字节数组
        byte[] bytes = GzipUtil.unCompress(source);

        // 在序列化json字节数组为java 对象
        return super.deserialize(bytes);
    }

}
