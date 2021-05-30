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
    //11.444.777/0001-00

    fun verify(view: View) {
        val cnpj = editText.text.toString()
        var cnpjValue: String
        if (cnpj.length == 18 &&
            cnpj[2] == '.' &&
            cnpj[6] == '.' &&
            cnpj[10] == '/' &&
            cnpj[15] == '-' ) {
            cnpjValue = cnpj.substring(0, 2) +
                    cnpj.substring(3, 6) +
                    cnpj.substring(7, 10) +
                    cnpj.substring(11, 15) +
                    cnpj.substring(16, 18)
            try {
                parseLong(cnpjValue)
            } catch (e: NumberFormatException) {
                errorDisplay()
                return
            }
        }
        else
            cnpjValue = cnpj

        if (cnpjValue.length == 14) {
            val authDigitOne = cnpjValue.substring(0, 12)
            val weightDigitOne = "543298765432"
            var sumDigitOne = 0
            weightDigitOne.indices.forEach { i ->
                sumDigitOne += authDigitOne[i].digitToInt() * weightDigitOne[i].digitToInt()
            }
            Log.d("digitcheck1", "valor de sumDigitOne: ${sumDigitOne}")
            sumDigitOne %= 11
            if (sumDigitOne < 2){
                if (cnpjValue[13].digitToInt() == 0) {
//                  proceeds to authDigitTwo
//                  digitTwoVerify(cnpjValue)
                    Log.d("ValidateCNPJActivity", " cnpjValue.lengh = ${cnpjValue.length}")
                    val authDigitTwo = cnpjValue.substring(0, 13)
                    val weightDigitTwo = "6543298765432"
                    var sumDigitTwo = 0
                    var counterre = 0
                    weightDigitTwo.indices.forEach { i ->
                        sumDigitTwo += authDigitTwo[i].digitToInt() * weightDigitTwo[i].digitToInt()
                    }
                    Log.d("digitcheck2", "valor de sumDigitTwo: ${sumDigitTwo}")
                    sumDigitTwo %= 11
                    if (sumDigitTwo < 2) {
                        if (cnpjValue.substring(13, 14).toInt() == 0)
//                                  is a CNPJ
                            isValidDisplay()
                        else {
                            errorDisplay()
                            return
                        }
                    }
                    else if (cnpjValue.substring(13, 14).toInt() == 11 - sumDigitTwo)
//                                  is a CNPJ
                            isValidDisplay()
                    else{
                        errorDisplay()
                        return
                    }
                }
            }
            else if (cnpjValue[12].digitToInt() == 11 - sumDigitOne){
//                  proceeds to authDigitTwo
//                  digitTwoVerify(cnpjValue)
                    val authDigitTwo = cnpjValue.substring(0, 14)
                    val weightDigitTwo = "6543298765432"
                    var sumDigitTwo = 0
                    weightDigitTwo.indices.forEach { i ->
                        sumDigitTwo += authDigitTwo[i].digitToInt() * weightDigitTwo[i].digitToInt()
                    }
                    Log.d("digitcheck2", "valor de sumDigitTwo: ${sumDigitTwo}")
                    sumDigitTwo %= 11
                    if (sumDigitTwo < 2) {
                        if (cnpjValue[13].digitToInt() == 0)
//                               is a CNPJ
                            isValidDisplay()
                        else{
                            errorDisplay()
                            return
                        }
                    }
                    else if (cnpjValue[13].digitToInt() == 11 - sumDigitTwo)
//                              is a CNPJ
                        isValidDisplay()
                    else {
                        errorDisplay()
                        return
                    }
            }
        else {
            errorDisplay()
            return
            }
        }
    }

    fun errorDisplay() {
        editText.error = "Invalid CNPJ"
    }

    fun isValidDisplay() {
        Toast.makeText(this, "This CNPJ is Valid.", Toast.LENGTH_SHORT).show()
    }
}