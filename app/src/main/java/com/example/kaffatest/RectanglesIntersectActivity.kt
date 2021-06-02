package com.example.kaffatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.lang.Long

class RectanglesIntersectActivity : AppCompatActivity() {

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

/*      checks the numeric difference between X1's X2's and Y1's and Y2's
        they are allowed to cancel themselves presenting a resultant of zero but any negative
        value formulated from their difference would imply in a negative width (in case of X's)
        or negative height (in case of Y's)                                                    */
        if (twoAndThree.calculaArea() > 0) {
            isValid()
            return
        } else {
            isNotValid()
            return
        }
    }

    private fun isValid() {
        val myToast = Toast.makeText(this,"True",Toast.LENGTH_SHORT)
        myToast.setGravity(Gravity.CENTER,200,200)
        myToast.show()
    }

    private fun isNotValid() {
        Toast.makeText(this, "False", Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.TOP, 0, 0)
            show()
        }
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


/*
    |..+-------+....
 10 |..|.......|....
    |..|...A...|....
    |..|.......|....
    |..|...#####-+..
    |..|...#####.|..
 5  |..+---#####.|..
    |......|..B..|..
    |......|.....|..
    |......+-----+..
 1  |...............
 0  +----|----|----|
    0    5    10   15

A = (3, 5; 11, 11)      ->  A: x is composed of a length 3 to 11, y is 5 to 11
B = (7, 2; 13, 7)       ->  B: x is composed of a length 7 to 13, y to 2 to 7
C = (?, ?; ?, ?)        ->  C: x is composed of a length 7 to 11, y to 5 to 7


Introducing a hypothetical failure which will result in an illegal result:
A = (3, 5; 11, 11)      ->  A: x is 3 to 11,  y is 5  to 11
B = (6, 4; 13, 4)       ->  B: x is 6 to 13,  y is 4 to  4
C = (?, ?; ?, ?)        ->  C: x is 6 to 11,  y is 5 to  4
                                    4 -  5 = -1 -> negative  -> proof by absurd!! this rectangle is not possible.
intersects(A, B) => true
intersects(A, C) => true
intersects(B, C) => false



If a rectangle A intersects a rectangle B,
there is a rectangle C that is an intersection between A and B

to do:
1 - sort x's and y's in a hierarchy
2 - calculate a intersect rectangle EVEN THO it might not be one, but assume that one exists.
3 - if one of its values goes negative, flags as false -> else -> true


 */