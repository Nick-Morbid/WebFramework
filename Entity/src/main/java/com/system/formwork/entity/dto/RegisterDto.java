package com.system.formwork.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 注册表单
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RegisterDto {
    private String id;
    private String name;
    private String password;
}
