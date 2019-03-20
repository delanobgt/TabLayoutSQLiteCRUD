package com.example.tablayoutsqlitecrud;

import android.content.Context;
import android.support.design.widget.TabLayout;

import com.example.tablayoutsqlitecrud.adapters.RecyclerViewAdapter;
import com.example.tablayoutsqlitecrud.adapters.TabPagerAdapter;
import com.example.tablayoutsqlitecrud.fragments.StudentDetailFragment;
import com.example.tablayoutsqlitecrud.fragments.StudentModifyFragment;
import com.example.tablayoutsqlitecrud.models.Student;
import com.example.tablayoutsqlitecrud.models.StudentTable;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private static Store store= null;
    private StudentTable studentTable;
    public List<Student> studentList = new ArrayList<>();
    public TabLayout tabLayout;
    public RecyclerViewAdapter recyclerViewAdapter;
    public TabPagerAdapter tabPagerAdapter;
    public StudentDetailFragment detailFragment;
    public StudentModifyFragment modifyFragment;

    protected Store() { }

    public static Store getStore() {
        if (store == null)
            store = new Store();
        return store;
    }

    public void init(Context context){
        studentTable = new StudentTable(context);
        studentTable.open();
        studentList = studentTable.getStudents();
        studentTable.close();
    }

    public Student createStudent(Student student) {
        studentTable.open();
        long id = studentTable.createStudent(student);
        studentTable.close();
        student.setId(id);
        studentList.add(student);
        return student;
    }
    public List<Student> getStudents() {
        return studentList;
    }
    public Student getStudentById(Long id) {
        int foundIndex = studentList.indexOf(new Student().setId(id));
        if (foundIndex != -1) return studentList.get(foundIndex);
        return null;
    }
    public Student updateStudentById(Long id, Student student) {
        student.setId(id);
        studentTable.open();
        Boolean success = studentTable.updateContactById(id, student);
        studentTable.close();
        if (success) {
            int foundIndex = studentList.indexOf(student);
            if (foundIndex != -1) {
                studentList.set(foundIndex, student);
                return student;
            }
            return null;
        } else {
            return null;
        }
    }
    public Long deleteStudentById(Long id) {
        studentTable.open();
        Boolean success = studentTable.deleteContactById(id);
        studentTable.close();
        if (success) {
            int foundIndex = studentList.indexOf(new Student().setId(id));
            if (foundIndex != -1) {
                studentList.remove(foundIndex);
                return id;
            }
            return null;
        } else {
            return null;
        }
    }
}
