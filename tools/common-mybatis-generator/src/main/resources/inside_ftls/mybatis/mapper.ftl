 package ${config.packageName};

<#list metaInfo.importList as import>
import ${import};
</#list>
import java.util.List;

/** ${metaInfo.classComment}
* @author ${author}
* @since ${createDate}
*/
public interface ${metaInfo.className}{

    public boolean insert(${entityName} po);

    public boolean deleteById(Long id);

    public boolean updateById(${entityName} po);

    public boolean updateNotNullById(${entityName} po);

    public ${entityName} selectById(Long id);

    public ${entityName} select(${conditionName} condition);

    public List<${metaInfo.className}> selectList(${conditionName} condition);

}
