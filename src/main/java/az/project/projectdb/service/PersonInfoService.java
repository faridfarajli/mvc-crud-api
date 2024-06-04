package az.project.projectdb.service;

import az.project.projectdb.entity.Person;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class PersonInfoService {

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public List<Person> callPersonFirstPage() {
        NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        String sql = "SELECT * FROM EDUMAN_COMMON.TEST_FARID  WHERE ROWNUM <= 20";
        Map<String, Object> paramMap = new HashMap<>();
        List<Person> resultList = namedJdbcTemplate.query(sql, paramMap, new PersonInfoRowMapper());
        return resultList;
    }


    public List<Person> callPersonSecondPage() {
        NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        String sql = "SELECT * FROM (SELECT f.*, ROWNUM AS rn FROM EDUMAN_COMMON.TEST_FARID f WHERE ROWNUM <= 40) WHERE rn > 0";

        Map<String, Object> paramMap = new HashMap<>();
        List<Person> resultList = namedJdbcTemplate.query(sql, paramMap, new PersonInfoRowMapper());
        return resultList;
    }

    public List<Person> callPersonThirdPage() {
        NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        String sql = "SELECT * FROM (SELECT f.*, ROWNUM AS rn FROM EDUMAN_COMMON.TEST_FARID f WHERE ROWNUM <= 60) WHERE rn > 0";
        Map<String, Object> paramMap = new HashMap<>();
        List<Person> resultList = namedJdbcTemplate.query(sql, paramMap, new PersonInfoRowMapper());
        return resultList;
    }
    public List<Person> callPersonFourthPage() {
        NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        String sql = "SELECT * FROM (SELECT f.*, ROWNUM AS rn FROM EDUMAN_COMMON.TEST_FARID f WHERE ROWNUM <= 80) WHERE rn > 0";
        Map<String, Object> paramMap = new HashMap<>();
        List<Person> resultList = namedJdbcTemplate.query(sql, paramMap, new PersonInfoRowMapper());
        return resultList;
    }

    public List<Person> callPersonFivethPage() {
        NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        String sql = "SELECT * FROM (SELECT f.*, ROWNUM AS rn FROM EDUMAN_COMMON.TEST_FARID f WHERE ROWNUM <= 100) WHERE rn > 0";
        Map<String, Object> paramMap = new HashMap<>();
        List<Person> resultList = namedJdbcTemplate.query(sql, paramMap, new PersonInfoRowMapper());
        return resultList;
    }


    public List<Person> callPersonAllData() {
        NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        String sql = "SELECT * FROM EDUMAN_COMMON.TEST_FARID  WHERE ROWNUM <= 220";
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

    public int update(Person person) {
        int rowsAffected = jdbcTemplate.update("BEGIN EDUMAN_COMMON.update_method_farid(?, ?, ?, ?); END;"
                , person.getId(), person.getName(), person.getSurname(), person.getMiddleName());
        System.out.println(">> updated: " + rowsAffected);
        return rowsAffected;
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
        System.out.println("deleted: " + update);
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



    public void  findByDetailsId(Long id) {
        String sql = "SELECT p.name, p.surname, p.middle_name FROM EDUMAN_COMMON.TEST_FARID p where p.id = ?";
         jdbcTemplate.query(sql, new Object[]{id}, rs -> {});
    }

    public Map<String, Object> getItems(Long page, Long rows) {
        Long offset = (page +1) * rows;

        String countSql = "SELECT COUNT(*) FROM EDUMAN_COMMON.TEST_FARID";
        Long total = jdbcTemplate.queryForObject(countSql, Long.class);

        String sql = "SELECT * FROM (SELECT f.*, ROWNUM AS rn FROM EDUMAN_COMMON.TEST_FARID f WHERE ROWNUM <= ?) WHERE rn > ?";
        List<Person> items = jdbcTemplate.query(sql, new Object[]{offset, rows}, new PersonInfoRowMapper());

        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("rows", items);

        return result;
    }


        public void exportToExcel(HttpServletResponse response) throws IOException {
            List<Person> persons = callPersonAllData();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Persons");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Name");
            header.createCell(2).setCellValue("Surname");
            header.createCell(3).setCellValue("Middle Name");
            header.createCell(4).setCellValue("Sex");
            header.createCell(6).setCellValue("Com Person Uniq ID");
            header.createCell(8).setCellValue("Active");
            header.createCell(9).setCellValue("Notification Status");
            header.createCell(10).setCellValue("New C");

            int rowCount = 1;
            for (Person person : persons) {
                Row row = sheet.createRow(rowCount++);
                row.createCell(0).setCellValue(person.getId());
                row.createCell(1).setCellValue(person.getName());
                row.createCell(2).setCellValue(person.getSurname());
                row.createCell(3).setCellValue(person.getMiddleName());
                row.createCell(4).setCellValue(person.getSex());
                row.createCell(6).setCellValue(person.getComPersonUniqId());
                row.createCell(8).setCellValue(person.getActive());
                row.createCell(9).setCellValue(person.getNotificationStatus());
                row.createCell(10).setCellValue(person.getNew_C());
            }

            workbook.write(response.getOutputStream());
            workbook.close();
        }




    public void exportToExcel(Long page, Long pageSize, HttpServletResponse response) throws IOException {
        Long offset = (page + 1) * pageSize;
        List<Person> persons = callPersonDataWithPagination(offset,pageSize);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Persons");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Surname");
        header.createCell(3).setCellValue("Middle Name");
        header.createCell(4).setCellValue("Sex");
        header.createCell(6).setCellValue("Com Person Uniq ID");
        header.createCell(8).setCellValue("Active");
        header.createCell(9).setCellValue("Notification Status");
        header.createCell(10).setCellValue("New C");

        int rowCount = 1;
        for (Person person : persons) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(person.getId());
            row.createCell(1).setCellValue(person.getName());
            row.createCell(2).setCellValue(person.getSurname());
            row.createCell(3).setCellValue(person.getMiddleName());
            row.createCell(4).setCellValue(person.getSex());
            row.createCell(6).setCellValue(person.getComPersonUniqId());
            row.createCell(8).setCellValue(person.getActive());
            row.createCell(9).setCellValue(person.getNotificationStatus());
            row.createCell(10).setCellValue(person.getNew_C());
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    private List<Person> callPersonDataWithPagination(Long offset, Long pageSize) {
        String sql = "SELECT * FROM (SELECT f.*, ROWNUM AS rn FROM EDUMAN_COMMON.TEST_FARID f WHERE ROWNUM <= ?) WHERE rn > ?";
        return jdbcTemplate.query(sql, new Object[]{offset + pageSize, offset}, new PersonInfoRowMapper());
    }


}



