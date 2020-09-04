package com.example.helloworld

import android.accounts.Account
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class Accounts{
    private var accounts = mutableMapOf<String, String>()

     fun putAccount(id:String, pw:String){
        accounts.put(id, pw)
    }

    fun checkId(id:String):Boolean{
        var bResult = accounts.containsKey((id))
        return bResult
    }

    fun checkPw(inputId: String, inputPw: String):Boolean{
        var bResult = if(accounts.get(inputId) == inputPw) true else false
        return bResult
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*계정 정보 선언 - 클래스 사용 */
        var accountsClass = Accounts()
        accountsClass.putAccount("20190085", "20190085")
        Log.d("BasicSyntax", "계정 정보 등록. id= ${accountsClass}")


        /* 계정 정보 선언 - 함수 사용 */
        var accounts = mutableMapOf<String, String>()
        accounts.put("20190085", "20190085")
        Log.d("BasicSyntax", "계정 정보 등록. id= ${accounts}")

        /* 로그인 버튼 클릭 이벤트 */
        btnLogin.setOnClickListener {
            /* 함수 */
            logIn(accounts)

            /* 클래스 */
            logIn_Class(accountsClass)
        }
    }

    /* 함수를 사용한 방법 */
    fun logIn(accounts: kotlin.collections.MutableMap<String, String>){
        var inputId = txtID.text.toString()
        Log.d("BasicSyntax", "입력한 id 읽어오기. id= ${inputId}")

        if (accounts.containsKey(inputId)) {
            Log.d("BasicSyntax", "id 정보 존재.")

            var inputPw = txtPW.text.toString()
            if (accounts.get(inputId) == inputPw) {
                Toast.makeText(this@MainActivity, "로그인 성공!", Toast.LENGTH_SHORT).show()
                Log.d("BasicSyntax", "계정정보 일치. 로그인 성공.")
                val intent = Intent(this, Chat_Activity::class.java)
                intent.putExtra("ID",inputId)
                startActivity(intent)

            } else {
                Toast.makeText(this@MainActivity, "패스워드가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                Log.d("BasicSyntax", "패스워드 불일치.")
            }
        } else {
            Toast.makeText(this@MainActivity, "등록되지 않은 아이디입니다.", Toast.LENGTH_SHORT).show()
            Log.d("BasicSyntax", "아이디 정보 없음.")
        }

    }

    /* 클래스 사용한 방법*/
    fun logIn_Class(accounts:Accounts) {

        var inputId = txtID.text.toString()
        Log.d("BasicSyntax", "입력한 id 읽어오기. id= ${inputId}")

        if (accounts.checkId(inputId)) {
            Log.d("BasicSyntax", "id 정보 존재.")

            var inputPw = txtPW.text.toString()
            if (accounts.checkPw(inputId, inputPw)) {
                Toast.makeText(this@MainActivity, "로그인 성공!", Toast.LENGTH_SHORT).show()
                Log.d("BasicSyntax", "계정정보 일치. 로그인 성공 class.")
            }
            else {
                Toast.makeText(this@MainActivity, "패스워드가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                Log.d("BasicSyntax", "패스워드 불일치.")
            }
        }
        else {
            Toast.makeText(this@MainActivity, "등록되지 않은 아이디입니다.", Toast.LENGTH_SHORT).show()
            Log.d("BasicSyntax", "아이디 정보 없음.")
        }
    }
}