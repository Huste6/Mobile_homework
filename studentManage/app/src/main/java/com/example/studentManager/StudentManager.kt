package com.example.studentmanager

object StudentManager {
    private val students = mutableListOf<Student>()

    init {
        // Dữ liệu mẫu
        students.addAll(listOf(
            Student("20200001", "Nguyễn Văn A", "0123456789", "Hà Nội"),
            Student("20200002", "Trần Thị B", "0987654321", "Hồ Chí Minh"),
            Student("20200003", "Lê Văn C", "0912345678", "Đà Nẵng"),
            Student("20200004", "Phạm Thị D", "0898765432", "Hải Phòng"),
            Student("20200005", "Hoàng Văn E", "0876543210", "Cần Thơ")
        ))
    }

    fun getAllStudents(): List<Student> = students

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun updateStudent(position: Int, student: Student) {
        if (position in students.indices) {
            students[position] = student
        }
    }

    fun deleteStudent(position: Int) {
        if (position in students.indices) {
            students.removeAt(position)
        }
    }

    fun getStudent(position: Int): Student? {
        return if (position in students.indices) students[position] else null
    }
}
