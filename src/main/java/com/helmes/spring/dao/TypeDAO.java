package com.helmes.spring.dao;

import com.helmes.spring.model.Type;

import java.util.List;
import java.util.Map;

public interface TypeDAO {

    public Type getTypeById(String id);
    public List<Type> getAllTypes();
    public Map<String, String> getMapTypes();
}
