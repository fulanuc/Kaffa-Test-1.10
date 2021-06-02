package com.example.kaffatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.lang.Long.parseLong
import java.lang.NumberFormatException

class ValidateCNPJActivity : AppCompatActivity() {
    lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validate_cnpj)
        editText = findViewById(R.id.edit_field)
    }
    //01.345.789/1234-67
    //12.345.678/9012-34
    //11.444.777/0001-00 -> last digits are invalid
    //11.444.777/0001-61 -> corrected last digits

    fun checkInput(view: View) {                        //identifies if the given cnpj is formated in a specific manner.
        val cnpj = editText.text.toString()
        val cnpjValue: String
        if (cnpj.length == 18 &&
            cnpj[2] == '.' &&
            cnpj[6] == '.' &&
            cnpj[10] == '/' &&
            cnpj[15] == '-' ) {
            cnpjValue = cnpj.substring(0, 2)   +
                        cnpj.substring(3, 6)   +
                        cnpj.substring(7, 10)  +
                        cnpj.substring(11, 15) +
                        cnpj.substring(16, 18)
        }
        else
            cnpjValue = cnpj
        try {
            parseLong(cnpjValue)                     //searches the given cnpj looking after non-numerals
        } catch (e: NumberFormatException) {
            showNotValid()
            return
        }
        if (cnpjValue.length == 14 &&
            cnpjValue.substring(0,13) != "00000000000000") {
            val authDigitOne = cnpjValue.substring(0, 12)
            val weightDigitOne = "543298765432"
            var sumDigitOne = 0
            weightDigitOne.indices.forEach { i ->
                sumDigitOne += authDigitOne[i].digitToInt() * weightDigitOne[i].digitToInt()
            }

            Log.d("digitcheck1", "valor de sumDigitOne: ${sumDigitOne}")

            sumDigitOne %= 11
            if (sumDigitOne < 2 && cnpjValue[12].digitToInt() == 0) {
//              given conditions are met: proceeds to authenticate the second rule digit
                Log.d("ValidateCNPJActivity", " cnpjValue.lengh = ${cnpjValue.length}")
                val authDigitTwo = cnpjValue.substring(0, 13)
                val weightDigitTwo = "6543298765432"
                var sumDigitTwo = 0
                weightDigitTwo.indices.forEach { i ->
                    sumDigitTwo += authDigitTwo[i].digitToInt() * weightDigitTwo[i].digitToInt()
                }

                Log.d("digitcheck2", "valor de sumDigitTwo: ${sumDigitTwo}")

                sumDigitTwo %= 11
                if (sumDigitTwo < 2 && cnpjValue[13].digitToInt() == 0)
//                  is a CNPJ
                    showIsValid()
                else if (cnpjValue[13].digitToInt() == 11 - sumDigitTwo)
//                  is a CNPJ
                    showIsValid()
                else{
                    showNotValid()
                    return
                }
            }
            else if (cnpjValue[12].digitToInt() == 11 - sumDigitOne){
//              proceeds to authenticate the second rule digit
                val authDigitTwo = cnpjValue.substring(0, 13)
                val weightDigitTwo = "6543298765432"
                var sumDigitTwo = 0
                weightDigitTwo.indices.forEach { i ->
                    sumDigitTwo += authDigitTwo[i].digitToInt() * weightDigitTwo[i].digitToInt()
                }

                Log.d("digitcheck2", "valor de sumDigitTwo: ${sumDigitTwo}")

                sumDigitTwo %= 11
                if (sumDigitTwo < 2 && cnpjValue[13].digitToInt() == 0) {
//                  is a CNPJ
                    showIsValid()
                }
                else if (cnpjValue[13].digitToInt() == 11 - sumDigitTwo)
//                  is a CNPJ
                    showIsValid()
                else {
                    showNotValid()
                    return
                }
            }
            else {
                showNotValid()
                return
            }
        }
        else {
            showNotValid()
            return
        }

    }

    fun showNotValid() {
        editText.error = "Invalid CNPJ"
    }

    fun showIsValid() {
        Toast.makeText(this, "This CNPJ is Valid.", Toast.LENGTH_SHORT).show()
    }
    fun clearFields(view: View){
        findViewById<EditText>(R.id.edit_field).setText("")
    }
}