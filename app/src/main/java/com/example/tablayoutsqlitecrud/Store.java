package com.example.tablayoutsqlitecrud;

import android.content.Context;
import android.support.design.widget.TabLayout;

import com.example.tablayoutsqlitecrud.models.Student;
import com.example.tablayoutsqlitecrud.models.StudentTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    public static List<Student> studentList = new ArrayList<>();
    public static TabLayout tabLayout;
    public static Integer detailSelectedStudentIndex = null;
    public static volatile Integer editSelectedStudentIndex = null;
    private static StudentTable studentTable;
    private static Map<String, Runnable> runnables = new HashMap<>();

    public static void init(Context context){
        studentTable = new StudentTable(context);
        studentTable.open();
        studentList = studentTable.getStudents();
        studentTable.close();
    }

    public static void addSubscription(String name, Runnable runnable) {
        runnables.put(name, runnable);
    }
    public static void notifySubscribers() {
        for (Runnable runnable : runnables.values())
            runnable.run();
    }

    public static void setDetailSelectedStudentIndex(Integer index) {
        detailSelectedStudentIndex = index;
        notifySubscribers();
    }
    public static void setEditSelectedStudentIndex(Integer index) {
        editSelectedStudentIndex = index;
        notifySubscribers();
    }

    public static Student createStudent(Student student) {
        studentTable.open();
        long id = studentTable.createStudent(student);
        studentTable.close();
        student.setId(id);
        studentList.add(student);
        notifySubscribers();
        return student;
    }
    public static List<Student> getStudents() {
        return studentList;
    }
    public static Student getStudentById(Long id) {
        int foundIndex = studentList.indexOf(new Student().setId(id));
        if (foundIndex != -1) return studentList.get(foundIndex);
        return null;
    }
    public static Student updateStudentById(Long id, Student student) {
        student.setId(id);
        studentTable.open();
        Boolean success = studentTable.updateContactById(id, student);
        studentTable.close();
        if (success) {
            int foundIndex = studentList.indexOf(student);
            if (foundIndex != -1) {
                studentList.set(foundIndex, student);
                notifySubscribers();
                return student;
            }
            return null;
        } else {
            return null;
        }
    }
    public static Long deleteStudentById(Long id) {
        studentTable.open();
        Boolean success = studentTable.deleteContactById(id);
        studentTable.close();
        if (success) {
            int foundIndex = studentList.indexOf(new Student().setId(id));
            if (foundIndex != -1) {
                studentList.remove(foundIndex);
                notifySubscribers();
                return id;
            }
            return null;
        } else {
            return null;
        }
    }
}
