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
public class AutoDelCondition extends UpdateCondition {

    private String name;

    public AutoDelCondition() {
    }

    public AutoDelCondition(String name) {
        this.name = name;
    }
}
