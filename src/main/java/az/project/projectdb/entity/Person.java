package az.project.projectdb.entity;

import lombok.*;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "TEST_FARID",schema = "EDUMAN_COMMON")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
//    @SequenceGenerator(name = "person_seq", sequenceName = "EDUMAN_COMMON.PERSON_SEQ", allocationSize = 1)
    private Long id;
    private String name;
    private String surname;
    private String middleName;
    private Long sex;
    private Date birthDate;
    private Long comPersonUniqId;
    private Date changeDate;
    private Long active;
    private Long notificationStatus;
    private Long new_C;
    private String oldSurname;

    public Person(Long l, String name1, String surname1) {
    }
}
