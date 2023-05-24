package com.swan.core.utils;

import com.swan.core.domain.TreeNode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zongf
 * @since 2023-05-24
 **/
public class TreeUtil {

    /**
     * @param list 数据列表
     * @param idGetter id getter 方法
     * @param parentIdGetter 父id getter 方法
     * @param topValue 一级节点value 值
     * @param <E> 数据
     * @param <ID> id 类型
     * @return List<TreeNode<E>> 一级节点列表
     */
    public static <E, ID> List<TreeNode<E>> buildTree(List<E> list, Function<E, ID> idGetter, Function<E, ID> parentIdGetter, ID topValue) {
        if (Objects.isNull(list)) {
            return null;
        }
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        List<TreeNode<E>> nodes = list.stream().map(TreeNode<E>::new).collect(Collectors.toList());

        for (TreeNode<E> parentNode : nodes) {
            List<TreeNode<E>> children = nodes.stream()
                    .filter(node -> !node.equals(parentNode))
                    .filter(node -> isEquals(parentIdGetter.apply(node.getValue()), idGetter.apply(parentNode.getValue())))
                    .collect(Collectors.toList());

            parentNode.setChildren(children);
        }

        // 根节点
        List<TreeNode<E>> rootList = nodes.stream()
                .filter(node -> isEquals(parentIdGetter.apply(node.getValue()), topValue))
                .collect(Collectors.toList());

        return rootList;
    }


    private static <ID> boolean isEquals(ID a, ID b) {
        return a == null ? a == b : a.equals(b);
    }


}
