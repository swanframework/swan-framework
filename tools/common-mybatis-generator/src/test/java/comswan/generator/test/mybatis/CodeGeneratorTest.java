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
        String tableName = "bytesbc_pstn";
        this.mybatisGeneratorService.generateEntity(schemaName, tableName, CommentTypeEnum.DOC);
        this.mybatisGeneratorService.generateMapperXml(schemaName, tableName);
        this.mybatisGeneratorService.generateMapper(schemaName, tableName);
    }

    @Test
    public void batchGenerate() {
        List<String> tables = Arrays.asList(
            "vc_video_participant"
        );

        String schemaName = "swan-mybatis";
        for (String tableName : tables) {
            this.mybatisGeneratorService.generateEntity(schemaName, tableName, CommentTypeEnum.DOC);
            this.mybatisGeneratorService.generateMapperXml(schemaName, tableName);
            this.mybatisGeneratorService.generateMapper(schemaName, tableName);
        }



    }

}
