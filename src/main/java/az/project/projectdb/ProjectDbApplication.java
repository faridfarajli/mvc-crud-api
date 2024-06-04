package az.project.projectdb;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ProjectDbApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectDbApplication.class, args);
    }
 }
