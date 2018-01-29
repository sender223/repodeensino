package com.projeto.ensinodigital.ensinodigital.Entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.projeto.ensinodigital.ensinodigital.database.ConfiguracaoDataBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Sender on 19/01/2018.
 */

public class Usuarios {

    private String id;
    private String email;
    private String senha;
    private String nome;
    private String aniversario;
    private String sexo;
    private String ra;

    public Usuarios() {
    }
    public void salvar(){
        DatabaseReference referenciaFirebase = ConfiguracaoDataBase.getReferenciaFirebase();
        referenciaFirebase.child("usuario").child(String.valueOf(getId())).setValue(this);

    }
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> hashMapUsuario = new HashMap<>();

        hashMapUsuario.put("id", getId());
        hashMapUsuario.put("email",getEmail());
        hashMapUsuario.put("senha",getSenha());
        hashMapUsuario.put("nome",getNome());
        hashMapUsuario.put("dataNascimento",getAniversario());
        hashMapUsuario.put("sexo", getSexo());
        hashMapUsuario.put("ra",getra());

        return  hashMapUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getra() {
        return ra;
    }

    public void setra(String ra) {
        this.ra = ra;
    }

    public String getAniversario() {
        return aniversario;
    }

    public void setAniversario(String aniversario) {
        this.aniversario = aniversario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
