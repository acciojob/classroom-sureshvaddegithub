package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {

    Set<Student> studentList = new HashSet<>();
    Set<Teacher> teachersList = new HashSet<>();

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
        boolean teacherpresent = false;
        boolean studentpresent = false;

        for(Student s:studentList){
            if(s.getName().equals(studentName)) {
                studentpresent = true;
                break;
            }
        }
        for(Teacher t:teachersList){
            if(t.getName().equals(teacherName)) {
                teacherpresent = true;
                break;
            }
        }

        if (studentpresent && teacherpresent) {
            int studentscount =0;
            for(Teacher teacher:teachersList){
                if(teacher.getName().equals(teacherName))
                    studentscount = teacher.getNumberOfStudents();
            }

            if (studentTeacherPair.containsKey(teacherName) && studentTeacherPair.get(teacherName).size()<studentscount) {
                studentTeacherPair.get(teacherName).add(studentName);
            } else if(!studentTeacherPair.containsKey(teacherName)){
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
            for(String names:studentTeacherPair.get(name)) {
                students.add(names);
            }
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

      for(Teacher t:teachersList){
          if(t.getName().equals(name)) {
              teachersList.remove(t);
              break;
          }
      }

      studentTeacherPair.remove(name);
    }

    void deleteAllTeachers(){
        teachersList.clear();
        studentTeacherPair.clear();
    }
}
