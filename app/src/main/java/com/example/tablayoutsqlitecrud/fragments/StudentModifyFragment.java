package com.example.tablayoutsqlitecrud.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tablayoutsqlitecrud.R;
import com.example.tablayoutsqlitecrud.Store;
import com.example.tablayoutsqlitecrud.models.Student;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class StudentModifyFragment extends Fragment {

    private static final String TAG = "StudentModifyFragment";

    private EditText etId;
    private EditText etFullname;
    private EditText etBirthdate;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Spinner spGender;
    private EditText etTelephone;
    private EditText etCountry;
    private EditText etCity;
    private Button btnCreate;
    private Button btnSave;
    private Button btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_modify_fragment, container, false);

        etId = view.findViewById(R.id.ed_id);
        etFullname = view.findViewById(R.id.ed_fullname);
        etBirthdate = view.findViewById(R.id.ed_birthdate);
        spGender = view.findViewById(R.id.sp_gender);
        etTelephone = view.findViewById(R.id.ed_telephone);
        etCountry = view.findViewById(R.id.ed_country);
        etCity = view.findViewById(R.id.ed_city);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        etBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        btnCreate = view.findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Store.getStore().createStudent(new Student(
                        etFullname.getText().toString(),
                        etBirthdate.getText().toString(),
                        spGender.getSelectedItem().toString().toLowerCase(),
                        etTelephone.getText().toString(),
                        etCountry.getText().toString(),
                        etCity.getText().toString()
                ));
                setCreateForm();
                Toast.makeText(getContext(), "Student created.", Toast.LENGTH_SHORT).show();

            }
        });

        btnSave = view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Store.getStore().updateStudentById(Long.parseLong(etId.getText().toString()), new Student(
                        etFullname.getText().toString(),
                        etBirthdate.getText().toString(),
                        spGender.getSelectedItem().toString().toLowerCase(),
                        etTelephone.getText().toString(),
                        etCountry.getText().toString(),
                        etCity.getText().toString()
                ));
                setCreateForm();
                Toast.makeText(getContext(), "Student updated.", Toast.LENGTH_SHORT).show();
                Store.getStore().tabPagerAdapter.tab3Title = "Create";
                Store.getStore().tabPagerAdapter.notifyDataSetChanged();
            }
        });

        btnCancel = view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCreateForm();
                Store.getStore().tabPagerAdapter.tab3Title = "Create";
                Store.getStore().tabPagerAdapter.notifyDataSetChanged();
            }
        });

        setCreateForm();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void showDatePickerDialog() {
        Calendar todayDate = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(
            getContext(),
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(year, monthOfYear, dayOfMonth);
                    etBirthdate.setText(dateFormatter.format(selectedDate.getTime()));
                }
            },
            todayDate.get(Calendar.YEAR),
            todayDate.get(Calendar.MONTH),
            todayDate.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }

    public void setCreateForm() {
        etId.setVisibility(View.GONE);
        etFullname.setVisibility(View.VISIBLE);
        etBirthdate.setVisibility(View.VISIBLE);
        spGender.setVisibility(View.VISIBLE);
        etTelephone.setVisibility(View.VISIBLE);
        etCountry.setVisibility(View.VISIBLE);
        etCity.setVisibility(View.VISIBLE);
        btnCreate.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);

        etId.setText("");
        etFullname.setText("");
        etBirthdate.setText("");
        spGender.setSelection(0);
        etTelephone.setText("");
        etCountry.setText("");
        etCity.setText("");
    }

    public void setEditForm(Student student) {
        etId.setVisibility(View.VISIBLE);
        etFullname.setVisibility(View.VISIBLE);
        etBirthdate.setVisibility(View.VISIBLE);
        spGender.setVisibility(View.VISIBLE);
        etTelephone.setVisibility(View.VISIBLE);
        etCountry.setVisibility(View.VISIBLE);
        etCity.setVisibility(View.VISIBLE);
        btnCreate.setVisibility(View.GONE);
        btnSave.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);

        etId.setText(String.valueOf(student.id));
        etFullname.setText(student.fullname);
        etBirthdate.setText(student.birthdate);
        spGender.setSelection(student.gender.equalsIgnoreCase("male") ? 0 : 1);
        etTelephone.setText(student.telephone);
        etCountry.setText(student.country);
        etCity.setText(student.city);
    }
}
