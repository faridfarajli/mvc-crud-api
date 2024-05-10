package az.project.projectdb;

import az.project.projectdb.service.PersonInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ProjectDbApplication {
private final PersonInfoService personInfoService;
    public static void main(String[] args) {
        SpringApplication.run(ProjectDbApplication.class, args);

    }



}
