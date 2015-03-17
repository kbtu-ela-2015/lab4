package com.example.ela_lab3;

import java.util.List;

/**
 * Created by root on 3/17/15.
 */
public interface StudentDAO {
    List<Student> findAll();
    List<Student> findById(int id);
    List<Student> findByName(String name);
    boolean insertStudent(Student student);
    int deleteStudent(Student student);
    boolean updateStudent(Student student);
}
