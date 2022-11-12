package com.swan.mybatis.mapper;

import com.swan.mybatis.util.InterfaceUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.swan.mybatis.mapper.methods.*;

import java.util.Set;

/** 方法标识
 * @author zongf
 * @date 2021-02-26
 */
@Setter @Getter @ToString
public class MapperMethodsMetaInfo {

    private final boolean insert;
    private final boolean insertNotNull;
    private final boolean insertList;

    private final boolean delete;
    private final boolean deleteById;
    private final boolean deleteInIds;
    private final boolean deleteList;
    private final boolean deleteOnCondition;

    private final boolean update;
    private final boolean updateById;
    private final boolean updateNotNullById;
    private final boolean updateNotNull;

    private final boolean selectAll;
    private final boolean selectById;
    private final boolean selectListInIds;
    private final boolean selectListOnCondition;

    private final boolean count;
    private final boolean countOnCondition;




    public MapperMethodsMetaInfo(Class type) {
        Set<Class> interfaces = InterfaceUtil.getInterfaces(type);

        this.count = interfaces.contains(CountMethod.class);
        this.countOnCondition = interfaces.contains(CountOnConditionMethod.class);

        this.deleteById = interfaces.contains(DeleteByIdMethod.class);
        this.deleteInIds = interfaces.contains(DeleteByIdsMethod.class);
        this.deleteList = interfaces.contains(DeleteListMethod.class);
        this.delete = interfaces.contains(DeleteMethod.class);
        this.deleteOnCondition = interfaces.contains(DeleteOnConditionMethod.class);

        this.insertList = interfaces.contains(InsertListMethod.class);
        this.insert = interfaces.contains(InsertMethod.class);
        this.insertNotNull = interfaces.contains(InsertNotNullMethod.class);

        this.selectAll = interfaces.contains(SelectAllMethod.class);
        this.selectById = interfaces.contains(SelectByIdMethod.class);
        this.selectListInIds = interfaces.contains(SelectListByIdsMethod.class);
        this.selectListOnCondition = interfaces.contains(SelectListOnConditionMethod.class);

        this.updateById = interfaces.contains(UpdateByIdMethod.class);
        this.update = interfaces.contains(UpdateMethod.class);
        this.updateNotNullById = interfaces.contains(UpdateNotNullByIdMethod.class);
        this.updateNotNull = interfaces.contains(UpdateNotNullMethod.class);

    }
}
