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

public class StudentCreateFragment extends Fragment {

    private static final String TAG = "StudentCreateFragment";

    private EditText etFullname;
    private EditText etBirthdate;
    private EditText etGender;
    private EditText etTelephone;
    private EditText etCountry;
    private EditText etCity;
    private Button btnCreate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_create_fragment, container, false);

        etFullname = view.findViewById(R.id.et_fullname);
        etBirthdate = view.findViewById(R.id.et_birthdate);
        etGender = view.findViewById(R.id.et_gender);
        etTelephone = view.findViewById(R.id.et_telephone);
        etCountry = view.findViewById(R.id.et_country);
        etCity = view.findViewById(R.id.et_city);

        btnCreate = view.findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Store.createStudent(new Student(
                    etFullname.getText().toString(),
                    etBirthdate.getText().toString(),
                    etGender.getText().toString(),
                    etTelephone.getText().toString(),
                    etCountry.getText().toString(),
                    etCity.getText().toString()
                ));
                resetForm();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void resetForm() {
        etFullname.setText("");
        etBirthdate.setText("");
        etGender.setText("");
        etTelephone.setText("");
        etCountry.setText("");
        etCity.setText("");
    }
}
