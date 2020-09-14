package com.example.helloworld

import android.accounts.Account
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

// Google Login Result
// Firebase Auth
private var firebaseAuth:FirebaseAuth? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()

        // Firebase 인스턴스 생성
        firebaseAuth = FirebaseAuth.getInstance()

        /* 로그인 버튼 클릭 이벤트 */
        btnLogin.setOnClickListener {
            loginEmail()
        }

        /* 계정 생성 버튼 클릭 이벤트 */
        btnCreate.setOnClickListener {
            createEmail()
        }

    }

    // Create Email
    private fun createEmail(){
        firebaseAuth!!.createUserWithEmailAndPassword(txtID.text.toString(), txtPW.text.toString()).addOnCompleteListener(this){
            if(it.isSuccessful)
            {
                // Sign in success, update UI with the signed-in user's information
                val user = firebaseAuth?.currentUser
                Toast.makeText(this, "Authentication success.", Toast.LENGTH_LONG).show()
            }
            else{
                // If sign in fails, display a message to the user
                Toast.makeText(this, "Authentication failed." + it.exception.toString(), Toast.LENGTH_SHORT).show()

            }
        }
    }

    // SignIn with Email
    private fun loginEmail(){
        firebaseAuth!!.signInWithEmailAndPassword(txtID.text.toString(), txtPW.text.toString()).addOnCompleteListener(this){
            if(it.isSuccessful){
                // Sign in success, update UI with the signed-in user's information
                Toast.makeText(this, "signInWithEmail success.", Toast.LENGTH_LONG).show()

                // Firebase에 등록된 계정 정보들을 가진 변수.
                val user = firebaseAuth?.currentUser

                // intent로 뷰 간의 데이터 전송.
                val intent = Intent(this, Chat_Activity::class.java)
                intent.putExtra("user",user)

                // 채팅 화면 액티비티 실행
                startActivity(intent)
            }
            else
            {
                // If sign in fails, display a message to the user
                Toast.makeText(this, "로그인 실패. \n아이디 또는 패스워드를 확인해주세요.", Toast.LENGTH_LONG).show()
            }
        }
    }


}