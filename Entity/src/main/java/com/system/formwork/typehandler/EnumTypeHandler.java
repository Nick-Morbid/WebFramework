package com.system.formwork.typehandler;

import com.system.formwork.constant.CommonEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
* 实现枚举类与数据库中tinyint的映射
* */
@MappedJdbcTypes(JdbcType.INTEGER)
public class EnumTypeHandler<T extends CommonEnum> extends BaseTypeHandler<T> {

    private Class<T> type = null;


    public EnumTypeHandler(){}

    public EnumTypeHandler(Class<T> type) {
        if (type==null) System.out.println("Type argument cannot be null");
        this.type = type;
    }

    //用来设置java对象->数据库的参数映射
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T t, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,t.getCode());
    }

    @Override
    public T getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return convert(resultSet.getInt(s));
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return convert(resultSet.getInt(i));
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return convert(callableStatement.getInt(i));
    }

    public T convert(Integer code){
        for (T enumConstant : type.getEnumConstants()) {
            if (enumConstant.getCode().equals(code)) return enumConstant;
        }
        return null;
    }
}
