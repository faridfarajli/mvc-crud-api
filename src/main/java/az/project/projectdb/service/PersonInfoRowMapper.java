package az.project.projectdb.service;


import az.project.projectdb.entity.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonInfoRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person info = new Person();
        info.setId(rs.getLong("ID"));
        info.setName(rs.getString("NAME"));
        info.setSurname(rs.getString("SURNAME"));
        info.setMiddleName(rs.getString("MIDDLE_NAME"));
        info.setSex(rs.getLong("SEX"));
        info.setBirthDate(rs.getDate("BIRTH_DATE"));
        info.setComPersonUniqId(rs.getLong("COM_PERSON_UNIQ_ID"));
        info.setChangeDate(rs.getDate("CHANGE_DATE"));
        info.setActive(rs.getLong("ACTIVE"));
        info.setNotificationStatus(rs.getLong("NOTIFICATION_STATUS"));
        info.setNew_C(rs.getLong("NEW_C"));
        info.setOldSurname(rs.getString("OLD_SURNAME"));
        return info;
    }
}

