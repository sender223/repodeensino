package com.projeto.ensinodigital.ensinodigital.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.projeto.ensinodigital.ensinodigital.Entidades.Usuarios;
import com.projeto.ensinodigital.ensinodigital.R;
import com.projeto.ensinodigital.ensinodigital.database.ConfiguracaoDataBase;

/**
 * Created by Sender on 19/01/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editSenha;
    private Button botaoLogar;
    private FirebaseAuth autenticacao;
    private Usuarios usuarios;
    private TextView tvCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        //aqui vamos ocultar a barra actionbar na tela de abertura
        getSupportActionBar().hide();

        editEmail = (EditText) findViewById(R.id.emailCadastroId);
        editSenha = (EditText) findViewById(R.id.senhaCadastroId);
        botaoLogar = (Button) findViewById(R.id.botaoLogar);
        tvCadastro = (TextView) findViewById(R.id.tvcadastroId);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editEmail.getText().toString().equals("") &&
                        !editSenha.getText().toString().equals("")) {

                    usuarios = new Usuarios();
                    usuarios.setEmail(editEmail.getText().toString());
                    usuarios.setSenha(editSenha.getText().toString());
                    validarLogin();

                }else {
                    Toast.makeText(LoginActivity.this,"Preencha os campos de E-mail e Senha", Toast.LENGTH_LONG).show();

                }
            }
        });

        tvCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreCadastroUsuario();
            }
        });

      //  botaoCadastrar.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View v) {
      //          Intent intentAbrirTelaCadastro = new Intent(LoginActivity.this,CadastroActivity.class);
      //          startActivity(intentAbrirTelaCadastro);
      //      }
      //  });

    }

    private void validarLogin(){

        autenticacao = ConfiguracaoDataBase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuarios.getEmail(), usuarios.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    abrirTelaPrincipal();
                    Toast.makeText(LoginActivity.this,"Login Efetuado com Sucesso",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this,"E-mail ou Senha Invalidos!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void abrirTelaPrincipal(){
        Intent intentAbrirTelaPrincipal = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intentAbrirTelaPrincipal);
    }

    public void abreCadastroUsuario(){
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
        finish();
    }

    //public void abrirTelaCadastro(){

    //}

}








/*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //agora dentro do metodo oncreate
        //vamos recuperar o objeto do firebase vamos utilizar
        //o firebaseauth.getinstance,
        // pegando a classe e utilizando o metodo firebase
        //vc consegue fazer a autenticação do usuário

        //voce faz com que o usuário desloge do sistema.
        firebaseAuth.signOut();

        firebaseAuth = FirebaseAuth.getInstance();
        //aqui usamos o getCurrentUser para pegar o usuário corrente no momento.
        if (firebaseAuth.getCurrentUser() != null){
            Log.i("VerificaUsuario", "Usuario está logado!!");
        }else{
            Log.i("VerificaUsuario", "Usuario não esta logado!!");

        }


        //Login de usuário
        //aqui utilizaremos o metodo signInWithEmailAndPassword para validar o login e senha,
        //utilizamos o metodo add.OnCOmpleteListener para após a validação ele executara outros techos de codigos
        firebaseAuth.signInWithEmailAndPassword("sender.curso@gmail.com", "sender123")
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if( task.isSuccessful() ){//retornar true, sucesso ao login do usuário
                            Log.i("signIn","Sucesso ao fazer o Login usuário!!!");
                        }else{//erro ao cadastrar usuário
                            Log.i("signIn","Erro ao fazer o Login Usuário!!!" + task.getException());

                        }


                    }
                });
        /*
        //cadastro do usuário
        //pegando o objeto firebaseAuth e o metodo createUserWithEmailandPassaword
        //vamos criar o email e senha do usuário, paramentro 1 email, parametro 2 senha.
        firebaseAuth.createUserWithEmailAndPassword("sender.curso@gmail.com","sender123")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                //outro metodo é o .addOnCompleteListener - conseguimos adicionar um trecho de codigo
                //que voce quer que seja executado quando o processo de criação da conta do usuário for finalizado
                //primeiro parametro, a classe que vc está. e vamos instanciar uma classe oculta, o OnCompleteListener<AuthResult>()
                //nessa interface existe o metodo OnCompleteListener.
                    @Override
                    //dentro do onComplete iremos colocar as regras que precisamos. se task.isSuccessful ele retorna true e o log
                    aparecerá sucesso. se não o log ira aparecer erro.
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if( task.isSuccessful() ){//retornar true, sucesso ao cadastrar usuário
                            Log.i("createUser","Sucesso ao cadastrar usuário!!!");
                        }else{//erro ao cadastrar usuário
                            Log.i("createUser","Erro ao cadastrar Usuário!!!");

                        }

                    }
                });
        */




