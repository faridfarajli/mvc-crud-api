package az.project.projectdb.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
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
    private Long newC;
    private String oldUsername;
}
