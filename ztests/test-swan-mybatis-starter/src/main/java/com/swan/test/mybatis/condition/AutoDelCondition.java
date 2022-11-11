package com.swan.test.mybatis.condition;

import lombok.Getter;
import lombok.Setter;
import com.swan.mybatis.core.ICondition;

/**
 * @author zongf
 * @date 2021-01-09
 */
@Setter @Getter
public class AutoDelCondition implements ICondition {

    private String name;

    public AutoDelCondition() {
    }

    public AutoDelCondition(String name) {
        this.name = name;
    }
}
