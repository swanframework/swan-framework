 package ${config.packageName};

<#list metaInfo.importList as import>
import ${import};
</#list>

/** ${metaInfo.classComment}
* @author ${author}
* @date ${createDate}
*/
<#--@Mapper-->
public interface ${metaInfo.className} extends ${parentMapperName}<${config.idType}, ${entityName}>{


}
