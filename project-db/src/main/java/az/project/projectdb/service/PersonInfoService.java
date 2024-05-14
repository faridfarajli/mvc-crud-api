package az.project.projectdb.service;

import az.project.projectdb.entity.Person;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class PersonInfoService {

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public List<Person> callPersonInfoFunction() {

        NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = "SELECT * FROM EDUMAN_COMMON.TEST_FARID";
        Map<String, Object> paramMap = new HashMap<>();

        List<Person> resultList = namedJdbcTemplate.query(sql, paramMap, new PersonInfoRowMapper());

        return resultList;
    }

    public List<Person> callPersonInfoStudent() {

        NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = "SELECT *FROM EDUMAN_COMMON.COM_PERSONS p JOIN EDUMAN_MANAGEMENT.MAN_STUDENTS s ON s.COM_PERSON_ID  = p.id WHERE ROWNUM<200";
        Map<String, Object> paramMap = new HashMap<>();

        List<Person> resultList = namedJdbcTemplate.query(sql, paramMap, new PersonInfoRowMapper());

        return resultList;
    }

    public void update(Person person) {
        int rowsAffected = jdbcTemplate.update("BEGIN EDUMAN_COMMON.update_method_farid(?, ?, ?, ?); END;"
                , person.getId() , person.getName(), person.getSurname(), person.getMiddleName());
        System.out.println(">> updated: " + rowsAffected);
    }

    public Optional<Person> findById(Long id) {
        String sql = "SELECT p.name, p.surname, p.middle_name FROM EDUMAN_COMMON.TEST_FARID p where p.id = ?";

        return jdbcTemplate.query(sql, new Object[]{id}, rs -> {
            if (rs.next()) {
                Person person = new Person();
                person.setId(id);
                person.setName(rs.getString("NAME"));
                person.setSurname(rs.getString("SURNAME"));
                person.setMiddleName(rs.getString("MIDDLE_NAME"));
                return Optional.of(person);
            } else {
                return Optional.empty();
            }
        });


    }

    public String generateRandomId() {
        int randomNum = ThreadLocalRandom.current().nextInt(1000000, 10000000);
        return Integer.toString(randomNum);
    }


    public void addPerson(Person person) {
        String sql = "BEGIN EDUMAN_COMMON.add_method_farid(?, ?, ?, ?, ?); END;";
        jdbcTemplate.update(sql, generateRandomId(), person.getName(), person.getSurname(), person.getMiddleName(), person.getSex());
    }

    public void deleteById(Long id) {
        String sql = "BEGIN EDUMAN_COMMON.delete_method_farid(?); END; ";
        int update = jdbcTemplate.update(sql, new Object[]{id});
        System.out.println("deleted: " + update );
    }

    public Optional<Person> deleteFindById(Long id) {
        String sql = "SELECT p.id FROM EDUMAN_COMMON.TEST_FARID p where p.id = ?";

        return jdbcTemplate.query(sql, new Object[]{id}, rs -> {
            if (rs.next()) {
                Person person = new Person();
                person.setId(id);
                return Optional.of(person);
            } else {
                return Optional.empty();
            }
        });

    }
}


