package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    void addStudent(Student student)
    {
        studentRepository.addStudentToDb(student);
    }
    void addTeacher(Teacher teacher)
    {
        studentRepository.addTeachertoDb(teacher);
    }

    void addStudentTeacherPair(String studentName,String teacherName)
    {
        studentRepository.addStudentTeacherPairToDb(studentName,teacherName);
    }
    Student getStudentByName(String name)
    {
        return studentRepository.getStudentByNameFromDb(name);
    }

    Teacher getTeacherByName(String name)
    {
        return studentRepository.getTeacherByNameFromDb(name);
    }

    List<String> getStudentsByTeacherName(String name){
        return studentRepository.getStudentsByTeacherNameFromDb(name);
    }
    List<String> getAllStudents(){
        return studentRepository.getAllStudentsFromDb();
    }

    void deleteTeacherByName(String name){
        studentRepository.deleteTeacherByNameFromDb(name);
    }

    void deleteAllTeachers(){
        studentRepository.deleteAllTeachers();
    }
}
