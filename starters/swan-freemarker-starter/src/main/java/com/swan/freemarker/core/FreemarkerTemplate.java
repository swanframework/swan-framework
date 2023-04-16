package com.swan.freemarker.core;

import cn.hutool.core.io.IoUtil;
import com.swan.core.exception.SwanBaseException;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/** Freemarker 操作模板
 * @author zongf
 * @since 2021-10-31
 */
public class FreemarkerTemplate implements IFreemarkerTemplate{

    private Configuration freemarkerConfiguration;

    public FreemarkerTemplate(Configuration configuration) {
        this.freemarkerConfiguration = configuration;
    }

    @Override
    public String getContent(String ftlRelativePath, Map<String, Object> root) {
        ByteArrayOutputStream bos = null;
        OutputStreamWriter osw = null;
        try {
            // 加载模板
            Template template = freemarkerConfiguration.getTemplate(ftlRelativePath);

            bos = new ByteArrayOutputStream();
            osw = new OutputStreamWriter(bos);
            template.process(root, osw);
            osw.flush();
            return bos.toString();
        } catch (Exception ex) {
            throw new SwanBaseException(ExceptionCodeEnum.UNKNOWN.code(), "模板解析失败!", ex);
        } finally {
            IoUtil.close(bos);
            IoUtil.close(osw);
        }
    }

    @Override
    public String getContent(String ftlRelativePath, String rootName, Object rootValue) {
        HashMap<String, Object> root = new HashMap<>();
        root.put(rootName, rootValue);
        return getContent(ftlRelativePath, root);
    }


}
