package com.example.kaffatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.lang.Long

class AreaIntersectActivity : AppCompatActivity() {

    private lateinit var twoAndThree: TwoAndThree
    private lateinit var rectangle1x1: EditText
    private lateinit var rectangle1y1: EditText
    private lateinit var rectangle1x2: EditText
    private lateinit var rectangle1y2: EditText
    private lateinit var rectangle2x1: EditText
    private lateinit var rectangle2y1: EditText
    private lateinit var rectangle2x2: EditText
    private lateinit var rectangle2y2: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rectangles_intersect)

        rectangle1x1 = findViewById(R.id.rectangle1x1)
        rectangle1y1 = findViewById(R.id.rectangle1y1)
        rectangle1x2 = findViewById(R.id.rectangle1x2)
        rectangle1y2 = findViewById(R.id.rectangle1y2)
        rectangle2x1 = findViewById(R.id.rectangle2x1)
        rectangle2y1 = findViewById(R.id.rectangle2y1)
        rectangle2x2 = findViewById(R.id.rectangle2x2)
        rectangle2y2 = findViewById(R.id.rectangle2y2)
    }

    fun verify(view: View) {

        try {
            twoAndThree = TwoAndThree(
                Long.parseLong(rectangle1x1.text.toString()),
                Long.parseLong(rectangle1y1.text.toString()),
                Long.parseLong(rectangle1x2.text.toString()),
                Long.parseLong(rectangle1y2.text.toString()),
                Long.parseLong(rectangle2x1.text.toString()),
                Long.parseLong(rectangle2y1.text.toString()),
                Long.parseLong(rectangle2x2.text.toString()),
                Long.parseLong(rectangle2y2.text.toString())
            )
        } catch (e: Exception) {
            Log.d("RectIntersectActivity", e.message?: "fail to call call TwoAndThree or parse to Long")
        }

        val area = twoAndThree.calculaArea()
        if (area >= 0) {
            Toast.makeText(this,
                String.format("the area of given intersection is: %d", area), Toast.LENGTH_SHORT).show()
            return
        }
        else {
            isNotValid()
            return
        }
    }
    fun isNotValid() {
        Toast.makeText(this, "There is no possible Area to be calculated.", Toast.LENGTH_SHORT).show()
    }
    fun clearFields(view: View) {
        findViewById<EditText>(R.id.rectangle1x1).setText("")
        findViewById<EditText>(R.id.rectangle1y1).setText("")
        findViewById<EditText>(R.id.rectangle1x2).setText("")
        findViewById<EditText>(R.id.rectangle1y2).setText("")
        findViewById<EditText>(R.id.rectangle2x1).setText("")
        findViewById<EditText>(R.id.rectangle2y1).setText("")
        findViewById<EditText>(R.id.rectangle2x2).setText("")
        findViewById<EditText>(R.id.rectangle2y2).setText("")
    }
}