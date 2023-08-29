package com.example.tfgfrontcamarero.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.tfgfrontcamarero.R
import com.example.tfgfrontcamarero.data.model.Usuario
import com.example.tfgfrontcamarero.ui.viewmodel.UsuarioViewModel

class LoginActivity : AppCompatActivity() {

    private val usuarioViewModel: UsuarioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val inputUsername: EditText = findViewById(R.id.txtUsername);
        val inputPassword: EditText =  findViewById(R.id.txtContrasena);
        val btnLogin: Button =  findViewById(R.id.btnLogin);
        val loginErrorMsg: TextView =  findViewById(R.id.loginError);
        val linkToRegistro: TextView = findViewById(R.id.linkRegistrar)


        fun validarDatos(): Boolean{
            if (inputUsername.text.toString().trim().isEmpty()){
                val toast = Toast.makeText(this, "El campo Usuario esta vacío", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                inputUsername.requestFocus()
                return false
            }
            if (inputPassword.text.toString().trim().isEmpty()){
                val toast = Toast.makeText(this, "El campo Contraseña esta vacío", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                inputPassword.requestFocus()
                return false
            }

            return true
        }

        btnLogin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                if (validarDatos()==true){
                    val usuario = Usuario()
                    usuario.userName=inputUsername.text.toString()
                    usuario.password=inputPassword.text.toString()
                    usuario.rol="CAMARERO"


                    usuarioViewModel.loginMethod(usuario);
                    usuarioViewModel.usuarioModel.observe(this@LoginActivity, Observer{
                        if (it!=null){
                            inputUsername.setText("")
                            inputPassword.setText("")
                            val intent = Intent(this@LoginActivity, MesasPedidosActivity::class.java )
                            intent.putExtra("usuario", usuarioViewModel.usuarioModel.value)
                            startActivity(intent)
                        }else{
                            val toast = Toast.makeText(this@LoginActivity, "Login erroneo", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER, 0, 0)
                            toast.show()
                            inputUsername.setText("")
                            inputPassword.setText("")
                        }
                    })

                }

            }
        })

        linkToRegistro.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View?) {
                val intent = Intent(this@LoginActivity, RegistrarActivity::class.java )
                inputUsername.setText("")
                inputPassword.setText("")
                startActivity(intent)
            }
        })
    }
}