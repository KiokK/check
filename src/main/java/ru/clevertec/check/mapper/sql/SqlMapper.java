package ru.clevertec.check.mapper.sql;

import java.sql.ResultSet;

public interface SqlMapper<E> {

    E toEntity(ResultSet rs);

}
