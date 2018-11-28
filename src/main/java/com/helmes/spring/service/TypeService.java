package com.helmes.spring.service;

import com.helmes.spring.model.Type;

import java.util.List;
import java.util.Map;

public interface TypeService {

    public List<Type> getAllTypes();
    public Type getTypeById(String id);
    public Map<String, String> getMapTypes();
}