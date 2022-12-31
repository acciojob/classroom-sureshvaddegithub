package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {

    HashMap<String,Student> studentList = new HashMap<>();
    HashMap<String,Teacher> teachersList = new HashMap<>();

    HashMap<String, List<String>> studentTeacherPair = new HashMap<>();

   public void addStudentToDb(Student student)
    {
        studentList.put(student.getName(),student);
    }

    public void addTeachertoDb(Teacher teacher)
    {
        teachersList.put(teacher.getName(),teacher);
    }

   public void addStudentTeacherPairToDb(String studentName,String teacherName) {
        if (studentList.containsKey(studentName) && teachersList.containsKey(teacherName)) {
            if (studentTeacherPair.containsKey(teacherName) ) {
                studentTeacherPair.get(teacherName).add(studentName);
            } else {
                List<String> studentNames = new ArrayList<>();
                studentNames.add(studentName);
                studentTeacherPair.put(teacherName, studentNames);
            }
        }
    }
   public Student getStudentByNameFromDb(String name)
        {
           return studentList.get(name);
        }

    public Teacher getTeacherByNameFromDb(String name)
    {
        return teachersList.get(name);
    }

   public List<String> getStudentsByTeacherNameFromDb(String name){
       return studentTeacherPair.get(name);
    }
    public List<String> getAllStudentsFromDb(){
        List<String> students = new ArrayList<>();
        for(String s:studentList.keySet()){
            students.add(s);
        }
        return students;
    }
    public void deleteTeacherByNameFromDb(String name){
       teachersList.remove(name);
       if(studentTeacherPair.containsKey(name)){
           for(String s:studentTeacherPair.get(name)){
               studentList.remove(s);
           }
           studentTeacherPair.remove(name);
       }
    }

    public void deleteAllTeachers(){
        teachersList.clear();
       for(String names:studentTeacherPair.keySet()){
           for(String students:studentTeacherPair.get(names)){
               studentList.remove(students);
           }
       }
       studentTeacherPair.clear();
    }
}
