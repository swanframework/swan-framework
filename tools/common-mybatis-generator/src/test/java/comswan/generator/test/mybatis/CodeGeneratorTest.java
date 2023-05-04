package comswan.generator.test.mybatis;

import com.swan.generator.enums.CommentTypeEnum;
import com.swan.generator.service.api.IMybatisGeneratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
        this.mybatisGeneratorService.generateMapperXml(schemaName, tableName);
    }

}
