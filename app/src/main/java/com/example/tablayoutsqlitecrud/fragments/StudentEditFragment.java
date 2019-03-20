package com.example.tablayoutsqlitecrud.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tablayoutsqlitecrud.R;
import com.example.tablayoutsqlitecrud.Store;
import com.example.tablayoutsqlitecrud.models.Student;

public class StudentEditFragment extends Fragment {

    private static final String TAG = "StudentEditFragment";

    private EditText etId;
    private EditText etFullname;
    private EditText etBirthdate;
    private EditText etGender;
    private EditText etTelephone;
    private EditText etCountry;
    private EditText etCity;
    private Button btnSave;
    private Button btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_edit_fragment, container, false);

        etId = view.findViewById(R.id.ed_id);
        etFullname = view.findViewById(R.id.ed_fullname);
        etBirthdate = view.findViewById(R.id.ed_birthdate);
        etGender = view.findViewById(R.id.ed_gender);
        etTelephone = view.findViewById(R.id.ed_telephone);
        etCountry = view.findViewById(R.id.ed_country);
        etCity = view.findViewById(R.id.ed_city);

        btnSave = view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Store.updateStudentById(Long.parseLong(etId.getText().toString()), new Student(
                        etFullname.getText().toString(),
                        etBirthdate.getText().toString(),
                        etGender.getText().toString(),
                        etTelephone.getText().toString(),
                        etCountry.getText().toString(),
                        etCity.getText().toString()
                ));
                Store.setEditSelectedStudentIndex(null);
            }
        });

        btnCancel = view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Store.setEditSelectedStudentIndex(null);
            }
        });

        Store.addSubscription(TAG, new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, Store.editSelectedStudentIndex+"");
                if (Store.editSelectedStudentIndex != null) {
                    Student student = Store.studentList.get(Store.editSelectedStudentIndex);
                    etId.setText(String.valueOf(student.id));
                    etFullname.setText(student.fullname);
                    etBirthdate.setText(student.birthdate);
                    etGender.setText(student.gender);
                    etTelephone.setText(student.telephone);
                    etCountry.setText(student.country);
                    etCity.setText(student.city);
                } else {
                    etId.setText("");
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
