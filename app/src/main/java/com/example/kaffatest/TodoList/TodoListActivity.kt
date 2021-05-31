package com.example.kaffatest.TodoList

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.kaffatest.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

const val filename = "todoList.txt"

class TodoListActivity : AppCompatActivity() {

    private val tag = "TodoListActivityDEBUG"

    private lateinit var todoListTextView:TextView
    private lateinit var taskText:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        todoListTextView = findViewById(R.id.todo_list_text_view)

        taskText = findViewById(R.id.task_text_edit_text)
        val addTaskFab = findViewById<FloatingActionButton>(R.id.add_task_fab)
        addTaskFab.setOnClickListener {
            val currentTasks = todoListTextView.text
            todoListTextView.text = "${currentTasks} • ${taskText.text}\n"
            taskText.text.clear()
        }
    }

    fun removeTask(view: View) {
        val firstTask = todoListTextView.text.lineSequence().first()
//        val endIndex = firstTask.length - 1
        todoListTextView.text = todoListTextView.text.removePrefix(firstTask+'\n')
//        todoListTextView.text.replaceRange(0, endIndex, "")
        Log.d("TodoListDBUG", "removing:\n$firstTask")
    }

    override fun onPause() {
        super.onPause()

        this.openFileOutput(filename, Context.MODE_PRIVATE).use {

            val fileContents = todoListTextView.text.toString()
            it.write(fileContents.toByteArray())
            todoListTextView.text = ""
            Log.d(tag, "sent: ${todoListTextView.text}")
        }
    }

    override fun onResume() {
        super.onResume()
        this.openFileInput(filename).bufferedReader().useLines { lines ->
//            lines.fold("") { some, text ->
//                "$some\n$text"
//            }
            lines.forEach {
                todoListTextView.text = "${todoListTextView.text}$it\n"
            }

            Log.d(tag, "received: ${todoListTextView.text}")
        }
    }


}