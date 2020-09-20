package com.example.android_app.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Data
@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
