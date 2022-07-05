package pl.sda.arppl4.springdemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.springdemo.model.Student;
import pl.sda.arppl4.springdemo.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            return student;
        }
        throw new EntityNotFoundException("Nie znaleziono studenta o id: " + studentId);
    }

    public void deleteById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public List<Student> findAllByNameContaining(String searchedName) {
        return studentRepository.findAllByNameLike("%" + searchedName + "%");
    }
}
