package com.example.housingsocietyapp.Model.Database;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vendors_table")
public class VendorsTable {

    @PrimaryKey
    @NonNull
    String phone;
    String name;
    String address;
    String type;

    public VendorsTable(@NonNull String phone, String name, String address, String type) {
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.type = type;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "VendorsTable{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

