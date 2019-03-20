package com.example.tablayoutsqlitecrud.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tablayoutsqlitecrud.R;
import com.example.tablayoutsqlitecrud.Store;
import com.example.tablayoutsqlitecrud.models.Student;

public class StudentDetailFragment extends Fragment {

    private static final String TAG = "StudentDetailFragment";

    private EditText etFullname;
    private EditText etBirthdate;
    private EditText etGender;
    private EditText etTelephone;
    private EditText etCountry;
    private EditText etCity;
    private Button btnCreate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_detail_fragment, container, false);
        etFullname = view.findViewById(R.id.ro_fullname);
        etBirthdate = view.findViewById(R.id.ro_birthdate);
        etGender = view.findViewById(R.id.ro_gender);
        etTelephone = view.findViewById(R.id.ro_telephone);
        etCountry = view.findViewById(R.id.ro_country);
        etCity = view.findViewById(R.id.ro_city);

        Store.addSubscription(TAG, new Runnable() {
            @Override
            public void run() {
                if (Store.detailSelectedStudentIndex != null) {
                    Student student = Store.studentList.get(Store.detailSelectedStudentIndex);
                    etFullname.setText(student.fullname);
                    etBirthdate.setText(student.birthdate);
                    etGender.setText(student.gender);
                    etTelephone.setText(student.telephone);
                    etCountry.setText(student.country);
                    etCity.setText(student.city);
                } else {
                    etFullname.setText("");
                    etBirthdate.setText("");
                    etGender.setText("");
                    etTelephone.setText("");
                    etCountry.setText("");
                    etCity.setText("");
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
