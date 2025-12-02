package com.example.studentmanagement

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanager.AddStudentActivity
import com.example.studentmanager.Student
import com.example.studentmanager.StudentAdapter
import com.example.studentmanager.StudentDetailActivity
import com.example.studentmanager.StudentManager

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set title cho ActionBar
        supportActionBar?.title = "Danh sách sinh viên"

        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        // Refresh danh sách khi quay lại activity
        refreshList()
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)

        // Convert List to MutableList
        adapter = StudentAdapter(
            StudentManager.getAllStudents().toMutableList(),
            onItemClick = { position ->
                // Mở DetailActivity khi click vào sinh viên
                val intent = Intent(this, StudentDetailActivity::class.java)
                intent.putExtra("POSITION", position)
                startActivity(intent)
            },
            onDeleteClick = { position ->
                StudentManager.deleteStudent(position)
                refreshList()
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun refreshList() {
        adapter.updateData(StudentManager.getAllStudents())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                // Mở AddStudentActivity
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}