package com.swan.mybatis.core;

import java.util.Map;

public interface ISqlTemplate {

    public String insert(Map<String, Object> dataMap);
    public String insertNotNull(Map<String, Object> dataMap);
    public String insertList(Map<String, Object> dataMap);

    public String delete(Map<String, Object> dataMap);
    public String deleteById(Map<String, Object> dataMap);
    public String deleteListInIds(Map<String, Object> dataMap);
    public String deleteOnCondition(Map<String, Object> dataMap);

    public String update(Map<String, Object> dataMap);
    public String updateById(Map<String, Object> dataMap);
    public String updateNotNull(Map<String, Object> dataMap);
    public String updateNotNullById(Map<String, Object> dataMap);
    public String updateOnCondition(Map<String, Object> dataMap);

    public String updateFields(Map<String, Object> dataMap);
    public String updateFieldsById(Map<String, Object> dataMap);
    public String updateFieldsOnCondition(Map<String, Object> dataMap);

    public String selectById(Map<String, Object> dataMap);
    public String selectListInIds(Map<String, Object> dataMap);
    public String selectListOnCondition(Map<String, Object> dataMap);
    public String selectSortListOnCondition(Map<String, Object> dataMap);
    public String selectFieldsById(Map<String, Object> dataMap);
    public String selectFieldsListInIds(Map<String, Object> dataMap);
    public String selectFieldsSortListOnCondition(Map<String, Object> dataMap);

    public String count(Map<String, Object> dataMap);
    public String countOnCondition(Map<String, Object> dataMap);

    public String containsById(Map<String, Object> dataMap);
    public String containsOnCondition(Map<String, Object> dataMap);



}
