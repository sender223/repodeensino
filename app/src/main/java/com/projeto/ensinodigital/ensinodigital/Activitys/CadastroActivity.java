package com.projeto.ensinodigital.ensinodigital.Activitys;

import android.content.Intent;
import android.provider.ContactsContract;
import android.renderscript.Element;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.projeto.ensinodigital.ensinodigital.Entidades.Usuarios;
import com.projeto.ensinodigital.ensinodigital.Helper.Base64Custom;
import com.projeto.ensinodigital.ensinodigital.Helper.Preferencias;
import com.projeto.ensinodigital.ensinodigital.R;
import com.projeto.ensinodigital.ensinodigital.database.ConfiguracaoDataBase;

import java.text.DateFormat;
import java.util.Date;

public class CadastroActivity extends AppCompatActivity {
    private EditText nomeCompleto;
    private EditText emailCadastro;
    private EditText senhaCadastro;
    private EditText confirmarSenhaCadastro;
    private EditText dataNascimento;
    private Button botaoGravar;
    private EditText ra;
    private RadioGroup sexo;
    private RadioButton rbMasculino;
    private RadioButton rbFeminino;
    private Usuarios usuarios;
    private FirebaseAuth autenticacao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nomeCompleto = (EditText) findViewById(R.id.nomeCadastroId);
        emailCadastro = (EditText)findViewById(R.id.emailCadastroId);
        senhaCadastro = (EditText)findViewById(R.id.senhaCadastroId);
        confirmarSenhaCadastro = (EditText) findViewById(R.id.confirmaSenhaCadastroId);
        dataNascimento = (EditText) findViewById(R.id.nascimentoCadastroId);
        botaoGravar = (Button) findViewById(R.id.btnFinalizarCadastro);
        ra = (EditText) findViewById(R.id.raId);
        sexo = (RadioGroup) findViewById(R.id.radioGroupId);
        rbMasculino = (RadioButton) findViewById(R.id.chkMasculino);
        rbFeminino = (RadioButton) findViewById(R.id.chkFeminino);

        botaoGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(senhaCadastro.getText().toString().equals(confirmarSenhaCadastro.getText().toString())){
                    usuarios = new Usuarios();
                    usuarios.setNome(nomeCompleto.getText().toString());
                    usuarios.setEmail(emailCadastro.getText().toString());
                    usuarios.setSenha(senhaCadastro.getText().toString());
                    usuarios.setra(ra.getText().toString());
                    usuarios.setAniversario(dataNascimento.getText().toString());

                    if (rbFeminino.isChecked()){
                        usuarios.setSexo("Feminino");
                    }else{
                        usuarios.setSexo("Masculino");
                    }

                    cadastrarUsuario();

                }else{
                    Toast.makeText(CadastroActivity.this,"As Senhas não são Correspondentes",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void cadastrarUsuario(){
        autenticacao = ConfiguracaoDataBase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuarios.getEmail(),
                usuarios.getSenha()
        ).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "Usuário Cadastrado com sucesso",Toast.LENGTH_LONG).show();

                    String identificadorUsuario = Base64Custom.codificarBase64(usuarios.getEmail());
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuarios.setId(identificadorUsuario);
                    usuarios.salvar();

                    Preferencias preferencias = new Preferencias(CadastroActivity.this);
                    preferencias.salvarUsuarioPreferencias(identificadorUsuario,usuarios.getNome());

                    abrirLoginUsuario();


                }else{
                    String erroExcecao = "";

                    try{
                        throw task.getException();
                        }catch (FirebaseAuthWeakPasswordException e){
                        erroExcecao = "Digite uma senha mais forte, contendo no mínimo 8 caracteres de letras e números";
                        }catch (FirebaseAuthInvalidUserException e){
                        erroExcecao = "O e-mail digitado é invalido, digite um novo e-mail";
                        }catch (FirebaseAuthUserCollisionException e){
                        erroExcecao = "Esse e-mail já está cadastrado no sistema";
                        }catch (Exception e){
                        erroExcecao = "Erro ao efetuar o cadastro";
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void abrirLoginUsuario(){
        Intent intent = new Intent(CadastroActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
