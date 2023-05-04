package comswan.generator.test.mybatis;

import com.swan.generator.enums.CommentTypeEnum;
import com.swan.generator.service.api.IMybatisGeneratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ActiveProfiles("swan")
@SpringBootTest
class CodeGeneratorTest extends AbsBaseTest{

    @Autowired
    private IMybatisGeneratorService mybatisGeneratorService;

    @Test
    void generateEntity() {
        String schemaName = "swan-mybatis";
        String tableName = "bytesbc_account";
//        this.mybatisGeneratorService.generateEntity(schemaName, tableName, CommentTypeEnum.DOC);
//        this.mybatisGeneratorService.generateMapperXml(schemaName, tableName);
        this.mybatisGeneratorService.generateMapper(schemaName, tableName);
    }

    @Test
    public void batchGenerate() {
        List<String> tables = Arrays.asList(
            "bytesbc_account",
            "bytesbc_allow_list",
            "bytesbc_carrier",
            "bytesbc_deny_list",
            "bytesbc_number",
            "bytesbc_number_attribution",
            "bytesbc_pstn",
            "bytesbc_pstn_group",
            "bytesbc_risk_limit_conf",
            "bytesbc_service",
            "bytesbc_trunk_in_customer_ip",
            "bytesbc_trunk_out_customer_ip"
        );

        String schemaName = "swan-mybatis";
        for (String tableName : tables) {

            this.mybatisGeneratorService.generateEntity(schemaName, tableName, CommentTypeEnum.DOC);
            this.mybatisGeneratorService.generateMapperXml(schemaName, tableName);
            this.mybatisGeneratorService.generateMapper(schemaName, tableName);
        }



    }

}
