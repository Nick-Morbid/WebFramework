package com.system.formwork.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.system.formwork.entity.constant.impl.Role;
import com.system.formwork.typehandler.AutoGenericEnumTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@TableName(value = "user")
public class User {
    @TableId
    private String id;
    private String name;
    private String password;
    @TableField(typeHandler = AutoGenericEnumTypeHandler.class)
    private Role role;
}
