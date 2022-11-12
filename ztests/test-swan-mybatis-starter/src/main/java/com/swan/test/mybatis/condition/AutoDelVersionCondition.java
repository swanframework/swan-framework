package com.swan.test.mybatis.condition;

import com.swan.mybatis.condition.UpdateCondition;
import lombok.Getter;
import lombok.Setter;
import com.swan.mybatis.core.ICondition;

/**
 * @author zongf
 * @date 2021-01-09
 */
@Setter @Getter
public class AutoDelVersionCondition extends UpdateCondition {

    private String name;

    public AutoDelVersionCondition() {
    }

    public AutoDelVersionCondition(String name) {
        this.name = name;
    }

}
