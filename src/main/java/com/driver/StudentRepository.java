package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    ArrayList<Student> studentList = new ArrayList<>();
    ArrayList<Teacher> teachersList = new ArrayList<>();

    HashMap<String, List<String>> studentTeacherPair = new HashMap<>();

    void addStudentToDb(Student student)
    {
        studentList.add(student);
    }

    void addTeachertoDb(Teacher teacher)
    {
        teachersList.add(teacher);
    }

    void addStudentTeacherPairToDb(String studentName,String teacherName) {
        if (teachersList.contains(teacherName) && studentList.contains(studentName)) {
            if (studentTeacherPair.containsKey(teacherName)) {
                studentTeacherPair.get(teacherName).add(studentName);
            } else {
                List<String> studentNames = new ArrayList<>();
                studentNames.add(studentName);
                studentTeacherPair.put(teacherName, studentNames);
            }

        }
    }
        Student getStudentByNameFromDb(String name)
        {
            for(Student s:studentList){
                if(s.getName().equals(name))
                    return s;
            }
            return null;
        }

    Teacher getTeacherByNameFromDb(String name)
    {
        for(Teacher s:teachersList){
            if(s.getName().equals(name))
                return s;
        }
        return null;
    }

    List<String> getStudentsByTeacherNameFromDb(String name){
        List<String> students = new ArrayList<>();
        if(studentTeacherPair.containsKey(name)){
            for(String names:studentTeacherPair.get(name))
                students.add(names);
        }
        return students;
    }
    List<String> getAllStudentsFromDb(){
        List<String> students = new ArrayList<>();
        for(Student s:studentList){
            students.add(s.getName());
        }
        return students;
    }
    void deleteTeacherByNameFromDb(String name){
      for(int i = 0 ;i< teachersList.size();i++){
          if(teachersList.get(i).getName().equals(name)){
              teachersList.remove(i);
          }
      }
      studentTeacherPair.remove(name);
    }

    void deleteAllTeachers(){
        teachersList.clear();
        studentTeacherPair.clear();
    }
}
