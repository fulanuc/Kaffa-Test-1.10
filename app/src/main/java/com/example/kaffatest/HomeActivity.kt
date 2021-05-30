package com.example.kaffatest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kaffatest.TodoList.TodoListActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun goToValidator(view: View) {
        val intent = Intent(this, ValidateCNPJActivity::class.java)
        startActivity(intent)
    }
    fun goToRectangles(view: View) {
        val intent = Intent(this, RectanglesIntersectActivity::class.java)
        startActivity(intent)
    }

    fun goToAreaIntersect(view: View) {
        val intent = Intent(this, AreaIntersectActivity::class.java)
        startActivity(intent)
    }
    fun goToList(view: View) {
        val intent = Intent(this, TodoListActivity::class.java)
        startActivity(intent)
    }


}