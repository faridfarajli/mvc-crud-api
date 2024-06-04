package az.project.projectdb.repo;

import az.project.projectdb.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = PersonRepository.class)
public interface PersonRepository extends JpaRepository<Person, Long> {
}
