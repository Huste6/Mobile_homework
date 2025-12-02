package com.example.studentmanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentmanagement.R

class AddStudentActivity : AppCompatActivity() {
    private lateinit var edtMssv: EditText
    private lateinit var edtHoten: EditText
    private lateinit var edtSoDienThoai: EditText
    private lateinit var edtDiaChi: EditText
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        // Set title cho ActionBar
        supportActionBar?.title = "Thêm sinh viên mới"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        edtMssv = findViewById(R.id.edtMssv)
        edtHoten = findViewById(R.id.edtHoten)
        edtSoDienThoai = findViewById(R.id.edtSoDienThoai)
        edtDiaChi = findViewById(R.id.edtDiaChi)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)
    }

    private fun setupListeners() {
        btnSave.setOnClickListener {
            if (validateInput()) {
                val student = Student(
                    mssv = edtMssv.text.toString().trim(),
                    hoten = edtHoten.text.toString().trim(),
                    soDienThoai = edtSoDienThoai.text.toString().trim(),
                    diaChi = edtDiaChi.text.toString().trim()
                )

                StudentManager.addStudent(student)
                Toast.makeText(this, "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun validateInput(): Boolean {
        if (edtMssv.text.toString().trim().isEmpty()) {
            edtMssv.error = "Vui lòng nhập MSSV"
            return false
        }
        if (edtHoten.text.toString().trim().isEmpty()) {
            edtHoten.error = "Vui lòng nhập họ tên"
            return false
        }
        if (edtSoDienThoai.text.toString().trim().isEmpty()) {
            edtSoDienThoai.error = "Vui lòng nhập số điện thoại"
            return false
        }
        if (edtDiaChi.text.toString().trim().isEmpty()) {
            edtDiaChi.error = "Vui lòng nhập địa chỉ"
            return false
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

