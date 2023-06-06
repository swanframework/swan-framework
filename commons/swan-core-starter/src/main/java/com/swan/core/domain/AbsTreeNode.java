package com.swan.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zongf
 * @since 2023-06-06
 **/
@Setter @Getter
public abstract class AbsTreeNode<T> {

    // 唯一id
    private T id;

    // 父id
    private T parentId;

    // 子节点列表
    private List<AbsTreeNode> children = new ArrayList<>();

}
