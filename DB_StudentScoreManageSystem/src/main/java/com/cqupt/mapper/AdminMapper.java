package com.cqupt.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
    public String getPasswordById(String userName);
}
