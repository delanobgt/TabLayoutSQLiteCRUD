package com.example.tablayoutsqlitecrud.models;

public class Student {
    public Long id;
    public String fullname;
    public String birthdate;
    public String gender;
    public String telephone;
    public String country;
    public String city;

    public Student() {}

    public Student(Long id, String fullname, String birthdate, String gender, String telephone, String country, String city) {
        this.id = id;
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.telephone = telephone;
        this.country = country;
        this.city = city;
    }

    public Student(String fullname, String birthdate, String gender, String telephone, String country, String city) {
        this.id = null;
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.telephone = telephone;
        this.country = country;
        this.city = city;
    }

    public Student setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (!other.id.equals(id))
            return false;
        return true;
    }
}
