package com.swan.mybatis.core;

import com.swan.freemarker.core.IFreemarkerTemplate;
import com.swan.mybatis.constants.FtlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;

/**
 * @author zongf
 * @since 2022-11-12
 **/
public class SqlTemplate implements ISqlTemplate{

    @Autowired
    @Qualifier("freemarkerTemplateInside")
    private IFreemarkerTemplate freemarkerTemplate;

    @Override
    public String insert(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.INSERT, dataMap);
    }

    @Override
    public String insertNotNull(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.INSERT_NOT_NULL, dataMap);
    }

    @Override
    public String insertList(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.INSERT_LIST, dataMap);
    }

    @Override
    public String delete(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.DELETE, dataMap);
    }

    @Override
    public String deleteById(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.DELETE_BY_ID, dataMap);
    }

    @Override
    public String deleteListInIds(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.DELETE_LIST_IN_IDS, dataMap);
    }

    @Override
    public String deleteOnCondition(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.DELETE_ON_CONDITION, dataMap);
    }

    @Override
    public String update(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.UPDATE, dataMap);
    }

    @Override
    public String updateById(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.UPDATE_BY_ID, dataMap);
    }

    @Override
    public String updateNotNull(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.UPDATE_NOT_NULL, dataMap);
    }

    @Override
    public String updateNotNullById(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.UPDATE_NOT_NULL_BY_ID, dataMap);
    }

    @Override
    public String updateOnCondition(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.UPDATE_ON_CONDITION, dataMap);
    }

    @Override
    public String updateFields(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.UPDATE_FIELDS, dataMap);
    }

    @Override
    public String updateFieldsById(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.UPDATE_FIELDS_BY_ID, dataMap);
    }

    @Override
    public String updateFieldsOnCondition(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.UPDATE_FIELDS_ON_CONDITION, dataMap);
    }

    @Override
    public String selectById(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.SELECT_BY_ID, dataMap);
    }

    @Override
    public String selectListInIds(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.SELECT_LIST_IN_IDS, dataMap);
    }

    @Override
    public String selectListOnCondition(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.SELECT_LIST_ON_CONDITION, dataMap);
    }

    @Override
    public String selectSortListOnCondition(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.SELECT_SORT_LIST_ON_CONDITION, dataMap);
    }

    @Override
    public String selectFieldsById(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.SELECT_FIELDS_BY_ID, dataMap);
    }

    @Override
    public String selectFieldsListInIds(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.SELECT_FIELDS_LIST_IN_IDS, dataMap);
    }

    @Override
    public String selectFieldsSortListOnCondition(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.SELECT_SORT_LIST_ON_CONDITION, dataMap);
    }

    @Override
    public String count(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.COUNT, dataMap);
    }

    @Override
    public String countOnCondition(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.COUNT_ON_CONDITION, dataMap);
    }

    @Override
    public String containsById(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.CONTAINS_BY_ID, dataMap);
    }

    @Override
    public String containsOnCondition(Map<String, Object> dataMap) {
        return this.freemarkerTemplate.getContent(FtlConstant.CONTAINS_ON_CONDITION, dataMap);
    }
}
