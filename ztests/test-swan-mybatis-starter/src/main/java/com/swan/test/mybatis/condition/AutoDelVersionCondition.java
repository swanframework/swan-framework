package com.swan.test.mybatis.condition;

import lombok.Getter;
import lombok.Setter;
import com.swan.mybatis.core.ICondition;

/**
 * @author zongf
 * @date 2021-01-09
 */
@Setter @Getter
public class AutoDelVersionCondition implements ICondition {

    private String name;

    public AutoDelVersionCondition() {
    }

    public AutoDelVersionCondition(String name) {
        this.name = name;
    }

}
