 package ${config.packageName};

<#list metaInfo.importList as import>
import ${import};
</#list>

/** ${metaInfo.classComment}
* @author ${author}
* @since ${createDate}
*/
@Getter @Setter
@Table(name = "${metaInfo.tableName}")
public class ${metaInfo.className} <#if config.parentClass??>extends ${config.parentClass.simpleName}</#if>{
<#list metaInfo.fields as field>

    <#if commentType == 1 || commentType == 3>
    /** ${field.comment} */
    </#if>
    <#if (field.pkColumn?string('true','false'))=="true">
    @Id
    </#if>
    <#if commentType == 2 || commentType == 3>
    @ApiModelProperty("${field.comment}")
    </#if>
    private ${field.type} ${field.name};
</#list>

}