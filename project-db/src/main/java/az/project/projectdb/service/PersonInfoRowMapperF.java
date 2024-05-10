package az.project.projectdb.service;


import az.project.projectdb.entity.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonInfoRowMapperF implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person info = new Person();
        info.setName(rs.getString("NAME"));
        info.setSurname(rs.getString("SURNAME"));
        info.setMiddleName(rs.getString("MIDDLE_NAME"));
        return info;
    }
}

