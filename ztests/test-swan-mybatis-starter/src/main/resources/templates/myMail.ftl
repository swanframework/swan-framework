自定义模板哦
${content!}
<#if attachmentList??>
    <div style="border: solid 1px #B2C9E0;margin-top: 20px;">
        <div style="padding: 5px; background-color: #B2C9E0">
            附件(${attachmentList?size})
        </div>
        <div style="padding: 10px">
            <#list attachmentList as attachment>
                <a href="${attachment.link}" target="_blank">${attachment.name}</a><br/>
            </#list>
        </div>
    </div>
</#if>
