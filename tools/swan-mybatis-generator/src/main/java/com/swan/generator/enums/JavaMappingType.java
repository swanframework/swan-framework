package com.swan.generator.enums;

/**
 * @author zongf
 * @since 2019-11-30
 */
public enum JavaMappingType {

    INTEGER("Integer"),
    LONG("Long"),
    FLOAT("Float"),
    DOUBLE("Double"),
    BIGINTEGER("BigInteger"),
    BIGDECIMAL("BigDecimal"),
    DATE("Date"),
    BYTEARRAY("byte[]"),
    STRING("String"),
    BOOLEAN("Boolean"),
    UNSUPPORT("unsupport");

    private String type;

    JavaMappingType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
