package com.system.formwork.mapstruct;

import com.system.formwork.entity.pojo.User;
import com.system.formwork.entity.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserConvertor {

    UserConvertor INSTANCE = Mappers.getMapper( UserConvertor.class );

    @Mappings({
            @Mapping(source = "id",target = "userId"),
            @Mapping(source = "name",target = "userName"),
            @Mapping(source = "role.msg",target = "roleName")
    })
    UserVo UserToUserVo(User user);
}
