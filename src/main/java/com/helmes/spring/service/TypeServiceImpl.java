package com.helmes.spring.service;

import com.helmes.spring.dao.TypeDAO;
import com.helmes.spring.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDAO typeDAO;
    public void setTypeDAO(TypeDAO typeDAO) {
        this.typeDAO = typeDAO;
    }

    @Override
    @Transactional
    public List<Type> getAllTypes() {
        return this.typeDAO.getAllTypes();
    }

    @Override
    @Transactional
    public Type getTypeById(String id) {
        return this.typeDAO.getTypeById(id);
    }
    @Override
    @Transactional
    public Map<String, String> getMapTypes() {
        return this.typeDAO.getMapTypes();
    }
}