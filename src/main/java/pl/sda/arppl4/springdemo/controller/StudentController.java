package pl.sda.arppl4.springdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.springdemo.model.Student;
import pl.sda.arppl4.springdemo.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

// REST == HTTP == ZAPYTANIA == REQUESTS (mają 'metody')
// Wyróżniamy metody HTTP:
//  - GET     (pobierz)
//  - POST    (wstaw, edytuj)
//  - DELETE  (usuń)
//  - PUT     (wstaw, podmień)
//  - PATCH   (edytuj fragment [nie cały])
//  RESTFUL API
// http://localhost:8080
//  - protokół http
//  - host localhost
//  - port 8080
//  - CTX - pusty
// jdbc:mysql://localhost:3306/arppl4_spring_demo?serverTimezone=Europe/Warsaw&createDatabaseIfNotExist=true
//  - protokół jdbc:mysql
//  - host localhost
//  - port 3306
//  - CTX - arppl4_spring_demo
@Slf4j
@RequestMapping("/api/student")
@RestController()
public class StudentController {

    private StudentRepository studentRepository; // Autowired

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //<editor-fold desc="CRUD>
    @GetMapping
    public List<Student> studentList() {
        log.info("Wywołano metodę studentList");

        List<Student> studentList = studentRepository.findAll();

        return studentList;
    }

    // PathVariable - Zmienna podana w ścieżce
    // http://localhost:8080/api/student/5
    @GetMapping("/{identifier}")
    public Student findStudent(@PathVariable(name = "identifier") Long studentId) {
        log.info("Wywołano metodę findStudent: " + studentId);

        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            return student;
        }
        throw new EntityNotFoundException("Nie znaleziono studenta o id: " + studentId);
    }

    // REST -> Representation State Transfer
    // Resource
    // PathVariable - Zmienna podana w ścieżce
    // http://localhost:8080/api/student/5
    @DeleteMapping("/{identifier}")
    public void deleteStudent(@PathVariable(name = "identifier") Long studentId) {
        log.info("Wywołano metodę deleteStudent: " + studentId);

        studentRepository.deleteById(studentId);
    }

    // Request Param - parametr zapytania
    // http://localhost:8080/api/student/find?studentId=5
    @GetMapping("/find")
    public Student findStudentById(@RequestParam(name = "studentId") Long studentId) {
        log.info("Wywołano metodę findStudentById: " + studentId);

        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            return student;
        }
        throw new EntityNotFoundException("Nie znaleziono studenta o id: " + studentId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody Student student) {
        log.info("Wywołano metodę createStudent: " + student);

        studentRepository.save(student);
    }
    //</editor-fold>

    // Request Param - parametr zapytania
    // Select * from Student s where s.name LIKE %Gawel%
    // http://localhost:8080/api/student/findByName?name=Gawel
    @GetMapping("/findByName")
    public List<Student> findStudentByName(@RequestParam(name = "name") String searchedName) {
        log.info("Wywołano metodę findStudentByName: " + searchedName);

        return studentRepository.findAllByNameLike("%" + searchedName + "%");
//        return studentRepository.findAllByNameContaining(searchedName);
    }
}

// RestController -> Zwraca DANE!
// Controller     -> Zwraca HTML - nie na dzisiaj
//
// [Controller] -> [ -> ] -> [Repository]