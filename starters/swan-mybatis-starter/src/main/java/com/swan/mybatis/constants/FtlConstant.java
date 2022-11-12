package com.swan.mybatis.constants;

/**
 * @author zongf
 * @since 2022-11-12
 **/
public class FtlConstant {

    private static final String BASIC_DIR = "mybatis";

    public static final String INSERT = "insert/insert.ftl";
    public static final String INSERT_NOT_NULL = "insert/insertNotNull.ftl";
    public static final String INSERT_LIST = "";

    public static final String DELETE = "";
    public static final String DELETE_BY_ID = "";
    public static final String DELETE_LIST_IN_IDS = "";
    public static final String DELETE_ON_CONDITION = "deleteOn";

    public static final String UPDATE = "";
    public static final String UPDATE_BY_ID = "";
    public static final String UPDATE_NOT_NULL = "";
    public static final String UPDATE_NOT_NULL_BY_ID = "";
    public static final String UPDATE_ON_CONDITION = "";
    public static final String UPDATE_FIELDS = "";
    public static final String UPDATE_FIELDS_BY_ID = "";
    public static final String UPDATE_FIELDS_ON_CONDITION = "";

    public static final String SELECT_BY_ID = "";
    public static final String SELECT_LIST_IN_IDS = "";
    public static final String SELECT_LIST_ON_CONDITION = "";
    public static final String SELECT_SORT_LIST_ON_CONDITION = "";
    public static final String SELECT_FIELDS_BY_ID = "";
    public static final String SELECT_FIELDS_LIST_IN_IDS = "";
    public static final String SELECT_FIELDS_SORT_LIST_ON_CONDITION = "";

    public static final String COUNT = BASIC_DIR + "/count/count";
    public static final String COUNT_ON_CONDITION = "/count/countOnCondition";

    public static final String CONTAINS_BY_ID = "";
    public static final String CONTAINS_ON_CONDITION = "";
    
}
