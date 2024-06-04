    package az.project.projectdb.service;

    import az.project.projectdb.entity.Person;
    import lombok.RequiredArgsConstructor;
    import org.springframework.jdbc.core.JdbcTemplate;
    import org.springframework.stereotype.Service;

    import javax.sql.DataSource;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Map;

    @Service
    @RequiredArgsConstructor
    public class SearchService {

        private final DataSource dataSource;
        private final JdbcTemplate jdbcTemplate;


        public List<Person> searchByName(String name) throws SQLException {
            List<Person> results = new ArrayList<>();
            name = name.toLowerCase();
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * \n" +
                            "FROM (\n" +
                            "    SELECT ROWNUM AS rn, p.*\n" +
                            "    FROM (\n" +
                            "        SELECT * FROM EDUMAN_COMMON.COM_PERSONS WHERE LOWER(NAME) LIKE ?\n" +
                            "    ) p\n" +
                            "    WHERE ROWNUM <= 300\n" +
                            ")\n");
            ) {
                preparedStatement.setString(1, "%" + name + "%");
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Person person = new Person();
                        person.setId(resultSet.getLong("id"));
                        person.setName(resultSet.getString("name"));
                        results.add(person);
                    }
                }
            }
            return results;


        }


        public List<Person> searchBySurname(String surname) throws SQLException {
            List<Person> results = new ArrayList<>();
            surname = surname.toLowerCase();
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * \n" +
                            "FROM (\n" +
                            "    SELECT ROWNUM AS rn, p.*\n" +
                            "    FROM (\n" +
                            "        SELECT * FROM EDUMAN_COMMON.COM_PERSONS WHERE LOWER(SURNAME) LIKE ?\n" +
                            "    ) p\n" +
                            "    WHERE ROWNUM <= 300\n" +
                            ")\n");
            ) {
                preparedStatement.setString(1, "%" + surname + "%");
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Person person = new Person();
                        person.setId(resultSet.getLong("id"));
                        person.setName(resultSet.getString("surname"));
                        results.add(person);
                    }
                }
            }
            return results;


        }


        public List<Person> searchById(Long id) throws SQLException {
            List<Person> results = new ArrayList<>();
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * \n" +
                            "FROM (\n" +
                            "    SELECT ROWNUM AS rn, p.*\n" +
                            "    FROM (\n" +
                            "        SELECT * FROM EDUMAN_COMMON.COM_PERSONS WHERE ID LIKE ?\n" +
                            "    ) p\n" +
                            "    WHERE ROWNUM <= 300\n" +
                            ")\n");
            ) {
                preparedStatement.setString(1, "%" + id + "%");
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Person person = new Person();
                        person.setId(resultSet.getLong("id"));
                        person.setName(resultSet.getString("id"));
                        results.add(person);
                    }
                }
            }
            return results;

        }



        public List<Map<String, Object>> getPersonsById(Long id, String query, String type, String active, String sex) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM EDUMAN_COMMON.COM_PERSONS p ");

            if ("student".equalsIgnoreCase(type)) {
                sql.append("JOIN EDUMAN_MANAGEMENT.MAN_STUDENTS s ON s.COM_PERSON_ID = p.ID ");
            }

            sql.append("WHERE 1=1 ");

            if (id != null) {
                sql.append("AND p.ID = ").append(id).append(" ");
            }

            if (query != null && !query.isEmpty()) {
                sql.append("AND (p.NAME LIKE '%").append(query).append("%' OR p.SURNAME LIKE '%").append(query).append("%') ");
            }

            if ("0".equals(active)) {
                sql.append("AND p.ACTIVE = '0' ");
            } else if ("1".equals(active)) {
                sql.append("AND p.ACTIVE = '1' ");
            }

            if ("20000015".equals(sex)) {
                sql.append("AND p.SEX = '20000015' ");
            } else if ("20000016".equals(sex)) {
                sql.append("AND p.SEX = '20000016' ");
            }

            sql.append("AND ROWNUM < 200");

            return jdbcTemplate.queryForList(sql.toString());
        }


        public List<Map<String, Object>> getPersonsByName(String query, String type, String active, String sex) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM EDUMAN_COMMON.COM_PERSONS p ");

            if ("student".equalsIgnoreCase(type)) {
                sql.append("JOIN EDUMAN_MANAGEMENT.MAN_STUDENTS s ON s.COM_PERSON_ID = p.ID ");
            }

            sql.append("WHERE 1=1 ");

//            if (id != null) {
//                sql.append("AND p.ID = ").append(id).append(" ");
//            }

            if (query != null && !query.isEmpty()) {
                sql.append("AND (p.NAME LIKE '%").append(query).append(" ");
            }

            if ("0".equals(active)) {
                sql.append("AND p.ACTIVE = '0' ");
            } else if ("1".equals(active)) {
                sql.append("AND p.ACTIVE = '1' ");
            }

            if ("20000015".equals(sex)) {
                sql.append("AND p.SEX = '20000015' ");
            } else if ("20000016".equals(sex)) {
                sql.append("AND p.SEX = '20000016' ");
            }

            sql.append("AND ROWNUM < 200");

            return jdbcTemplate.queryForList(sql.toString());
        }


    }
