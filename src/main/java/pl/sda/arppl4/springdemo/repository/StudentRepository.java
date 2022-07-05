package pl.sda.arppl4.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.arppl4.springdemo.model.Student;

import java.util.List;

// Bean -> nasionko, ziarno
// Instancja Componentu (Bean) tworzy się przy uruchomieniu aplikacji
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Select * from student s where name like ...
    List<Student> findAllByNameLike(String name);
    List<Student> findAllByName(String name);
}

// USER -> || -> [Controller] -> [...] -> [Repository]