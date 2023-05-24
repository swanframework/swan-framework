package com.swan.test.core.utils;

import com.swan.core.domain.TreeNode;
import com.swan.core.utils.TreeUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zongf
 * @since 2023-05-24
 **/
public class TreeUtilTest {

    @Data
    @NoArgsConstructor
    private static class Dept{
        private Integer id;

        private Integer parentId;

        private String name;

        public Dept(Integer id, Integer parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name + "_" + id;
        }


    }


    public static void main(String[] args) {
        List<Dept> depts = new ArrayList<>();
        depts.add(new Dept(100, null, "一级部门"));
        depts.add(new Dept(110, 100, "二级部门"));
        depts.add(new Dept(111, 110, "三级部门"));
        depts.add(new Dept(112, 110, "三级部门"));
        depts.add(new Dept(113, 110, "三级部门"));
        depts.add(new Dept(120, 100, "二级部门"));
        depts.add(new Dept(121, 120, "三级部门"));
        depts.add(new Dept(122, 120, "三级部门"));
        depts.add(new Dept(123, 120, "三级部门"));
        depts.add(new Dept(200, null, "一级部门"));
        depts.add(new Dept(210, 200, "二级部门"));
        depts.add(new Dept(211, 210, "三级部门"));
        depts.add(new Dept(212, 210, "三级部门"));
        depts.add(new Dept(213, 210, "三级部门"));

        List<TreeNode<Dept>> treeNodes = TreeUtil.buildTree(depts, Dept::getId, Dept::getParentId, null);

        System.out.println(treeNodes);


    }
}
