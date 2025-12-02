package com.example.studentmanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentmanagement.R

class StudentDetailActivity : AppCompatActivity() {
    private lateinit var edtMssv: EditText
    private lateinit var edtHoten: EditText
    private lateinit var edtSoDienThoai: EditText
    private lateinit var edtDiaChi: EditText
    private lateinit var btnUpdate: Button
    private lateinit var btnBack: Button

    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)

        // Set title cho ActionBar
        supportActionBar?.title = "Thông tin sinh viên"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        position = intent.getIntExtra("POSITION", -1)

        initViews()
        loadStudentData()
        setupListeners()
    }

    private fun initViews() {
        edtMssv = findViewById(R.id.edtMssv)
        edtHoten = findViewById(R.id.edtHoten)
        edtSoDienThoai = findViewById(R.id.edtSoDienThoai)
        edtDiaChi = findViewById(R.id.edtDiaChi)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnBack = findViewById(R.id.btnBack)
    }

    private fun loadStudentData() {
        if (position != -1) {
            StudentManager.getStudent(position)?.let { student ->
                edtMssv.setText(student.mssv)
                edtHoten.setText(student.hoten)
                edtSoDienThoai.setText(student.soDienThoai)
                edtDiaChi.setText(student.diaChi)
            }
        }
    }

    private fun setupListeners() {
        btnUpdate.setOnClickListener {
            if (validateInput()) {
                val updatedStudent = Student(
                    mssv = edtMssv.text.toString().trim(),
                    hoten = edtHoten.text.toString().trim(),
                    soDienThoai = edtSoDienThoai.text.toString().trim(),
                    diaChi = edtDiaChi.text.toString().trim()
                )

                StudentManager.updateStudent(position, updatedStudent)
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        btnBack.setOnClickListener {
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