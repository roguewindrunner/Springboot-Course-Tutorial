package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional=studentRepository
                .findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean b= studentRepository.existsById(studentId);
        if(!b){
            throw new IllegalStateException(
                    "Student with id " + studentId + " does not exist"
            );
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent (Long studentId, Student student){
        Optional<Student> studentOptional=studentRepository
                .findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        Student s= studentRepository.findById(studentId).get();
        s.setName(student.getName());
        s.setEmail(student.getEmail());
        studentRepository.save(s);
    }
}
