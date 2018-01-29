package com.projeto.ensinodigital.ensinodigital.database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Sender on 19/01/2018.
 */

public class ConfiguracaoDataBase {

    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth autenticacao;

    public static DatabaseReference getReferenciaFirebase(){
        if (referenciaFirebase == null){
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return  referenciaFirebase;
    }

    public static FirebaseAuth getFirebaseAutenticacao(){
        if (autenticacao == null){
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }
}
