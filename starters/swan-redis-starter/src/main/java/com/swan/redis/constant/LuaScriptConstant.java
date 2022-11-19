package com.swan.redis.constant;

import org.springframework.data.redis.core.script.DefaultRedisScript;

/** lua 脚本常量
 * @author zongf
 * @date 2021-05-14
 */
public class LuaScriptConstant {

    /** 释放锁脚本 */
    public static final DefaultRedisScript UN_LOCK = initUnLock();

    /** 锁续约脚本 */
    public static final DefaultRedisScript RENEWAL_LOCK = initRenewalUnLock();

    private static final DefaultRedisScript initUnLock() {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ").append("\n");
        sb.append("then ").append("\n");
        sb.append("    return redis.call(\"del\",KEYS[1]) ").append("\n");
        sb.append("else ").append("\n");
        sb.append("    return 0 ").append("\n");
        sb.append("end ").append("\n");

        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setScriptText(sb.toString());
        redisScript.setResultType(Long.class);
        return redisScript;
    }

    // 参数三个: 值(uuid), 续约时间, 临界时间(小于多少时进行续约)
    // 返回值: OK-锁已过期，重置  1-锁未过期，延长时间 -1 无需延长
    private static DefaultRedisScript initRenewalUnLock() {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"ttl\",KEYS[1]) < tonumber(ARGV[3])").append("\n");
        sb.append("then ").append("\n");
        sb.append("    if redis.call(\"exists\",KEYS[1]) == 0").append("\n");
        sb.append("    then").append("\n");
        sb.append("        return redis.call(\"set\", KEYS[1], ARGV[1], \"nx\", \"ex\", ARGV[2])").append("\n");
        sb.append("    else").append("\n");
        sb.append("        return redis.call(\"expire\", KEYS[1], ARGV[2])").append("\n");
        sb.append("    end").append("\n");
        sb.append("else").append("\n");
        sb.append("    return -1").append("\n");
        sb.append("end");

        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setScriptText(sb.toString());
        redisScript.setResultType(Long.class);
        return redisScript;

    }

}
