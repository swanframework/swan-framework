package com.swan.mybatis.field;

import com.swan.core.utils.NameUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zongf
 * @since 2022-11-12
 **/
public class SelectFields extends ArrayList<String>{

    @Override
    public boolean add(String s) {
        return super.add(NameUtil.toHungaryName(s));
    }

    @Override
    public void add(int index, String element) {
        super.add(index, NameUtil.toHungaryName(element));
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        List<String> list = c.stream().map(NameUtil::toHungaryName).collect(Collectors.toList());
        return super.addAll(list);
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        List<String> list = c.stream().map(NameUtil::toHungaryName).collect(Collectors.toList());
        return super.addAll(index, list);
    }

    @Override
    public String set(int index, String element) {
        return super.set(index, NameUtil.toHungaryName(element));
    }



}
