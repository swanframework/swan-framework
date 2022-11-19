package com.swan.test.mybatis.mapper;

import com.swan.test.mybatis.po.AutoDelEntity;
import com.swan.test.mybatis.po.AutoDelVersionEntity;
import com.swan.test.mybatis.po.AutoEntity;
import com.swan.test.mybatis.po.AutoVersionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zongf
 * @since 2021-01-10
 */
public class AutoEntityFactory {

    public static List<AutoEntity> createAutoEntity(int size) {
        return createAutoEntity(size, "auto_");
    }

    public static List<AutoEntity> createAutoEntity(int size, String... namePrefixs) {
        List<AutoEntity> list = new ArrayList<>(size);

        for (int i = 1; i <= size; i++) {
            for (String namePrefix : namePrefixs) {
                list.add(new AutoEntity(namePrefix + i, 10+i));
            }
        }
        return list;
    }

    public static List<AutoDelEntity> createAutoDelEntity(int size) {
        return createAutoDelEntity(size, "autoDel_");
    }

    public static List<AutoDelEntity> createAutoDelEntity(int size, String... namePrefixs) {
        List<AutoDelEntity> list = new ArrayList<>(size);

        for (int i = 1; i <= size; i++) {
            for (String namePrefix : namePrefixs) {
                list.add(new AutoDelEntity(namePrefix + i, 10+i));
            }
        }
        return list;
    }

    public static List<AutoVersionEntity> createAutoVersionEntity(int size) {
        return createAutoVersionEntity(size, "autoVersion_");

    }
    public static List<AutoVersionEntity> createAutoVersionEntity(int size, String... namePrefixs) {
        List<AutoVersionEntity> list = new ArrayList<>(size);

        for (int i = 1; i <= size; i++) {
            for (String namePrefix : namePrefixs) {
                list.add(new AutoVersionEntity(namePrefix + i, 10+i));
            }
        }
        return list;
    }

    public static List<AutoDelVersionEntity> createAutoDelVersionEntity(int size) {
        return createAutoDelVersionEntity(size, "autoDelVersion_");
    }

    public static List<AutoDelVersionEntity> createAutoDelVersionEntity(int size, String... namePrefixs) {
        List<AutoDelVersionEntity> list = new ArrayList<>(size);

        for (int i = 1; i <= size; i++) {
            for (String namePrefix : namePrefixs) {
                list.add(new AutoDelVersionEntity(namePrefix + i, 10+i));
            }
        }
        return list;
    }

}
