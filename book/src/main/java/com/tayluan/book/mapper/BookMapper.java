package com.tayluan.book.mapper;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {
    private static ModelMapper mapper = new ModelMapper();

    public static <O,D> D parseObject (O origin,Class <D> destination){
        return mapper.map(origin,destination);
    }
    public static <O,D> List<D> parseListObject(List<O> origin, Class <D> destination){
        List<D> objectList = origin
                .stream()
                .map(o -> mapper.map(o, destination))
                .collect(Collectors.toList());
        return objectList;
    }
}
