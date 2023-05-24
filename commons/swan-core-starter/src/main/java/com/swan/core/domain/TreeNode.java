package com.swan.core.domain;

import lombok.Data;

import java.util.List;

/**
 * @author zongf
 * @since 2023-05-24
 **/
@Data
public class TreeNode <T>{

    private T value;

    private List<TreeNode<T>> children;

    public TreeNode() {
    }

    public TreeNode(T value) {
        this(value, null);
    }

    public TreeNode(T value, List<TreeNode<T>> children) {
        this.value = value;
        this.children = children;
    }

}
