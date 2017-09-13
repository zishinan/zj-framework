package com.zj.framework.mybatis.handler;

import com.zj.framework.baseto.eo.IdEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用该转换器，接口封装层依赖
 * <dependency>
 *     <groupId>com.zj.framework.baseto</groupId>
 *     <artifactId>zj-baseto</artifactId>
 *     <version>1.0.0</version>
 * </dependency>
 * enum定义时实现 {@link IdEnum}
 * @author Mr. xi.yang<br/>
 * @version V1.0 <br/>
 * @description: 带id属性的通用转换器,将id映射到数据库中 <br/>
 * @date 2017-05-18 下午 2:51 <br/>
 */
public class IdEnumHandler<E extends Enum<E>> extends BaseTypeHandler<E> {
    private Class<E> type;
    private Map<Integer, E> map = new HashMap<Integer,E>();
    public IdEnumHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        E[] enums = type.getEnumConstants();
        if (enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
        for (E e : enums) {
            IdEnum idEnum = (IdEnum) e;
            map.put(idEnum.getId(), e);
        }
    }
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        IdEnum idEnum = (IdEnum) parameter;
        ps.setInt(i, idEnum.getId());
    }
    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int i = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return getIdEnum(i);
        }
    }
    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return getIdEnum(i);
        }
    }
    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return getIdEnum(i);
        }
    }
    private E getIdEnum(int id) {
        try {
            return map.get(id);
        } catch (Exception ex) {
            throw new IllegalArgumentException(
                    "Cannot convert " + id + " to " + type.getSimpleName() + " by value.", ex);
        }
    }
}
