package com.example.studentmanager

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    var mssv: String,
    var hoten: String,
    var soDienThoai: String = "",
    var diaChi: String = ""
) : Parcelable
