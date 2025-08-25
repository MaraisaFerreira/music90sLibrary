package com.study.mf.mappers;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectsMapper {
    private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <I, O> O parseObject(I in, Class<O> out) {
        return mapper.map(in, out);
    }

    public static <I, O> List<O> parseListObject(List<I> listIn, Class<O> out) {
        List<O> list = new ArrayList<>();
        for (I in : listIn) {
            list.add(mapper.map(in, out));
        }

        return list;
    }
}
