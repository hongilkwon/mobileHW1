package com.example.moblieprogramminghw1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity  {


     static final int SIGNUP_INFO = 1;
     static final int SIGNUP_CONDITION = 2;


    private FragmentManager fragmentManager;
    private Fragment infoFragment = new InfoFragment();
    private Fragment conditionsFragment = new ConditionsFragment();

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        fragmentManager = getSupportFragmentManager();
        settingFragment(SIGNUP_CONDITION);
    }

    public void settingFragment(int fragmentCode){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (fragmentCode){
            case SIGNUP_INFO:
                transaction.replace(R.id.signUpActivity_frame_layout,infoFragment);
                break;
            case SIGNUP_CONDITION:
                transaction.replace(R.id.signUpActivity_frame_layout,conditionsFragment);
                transaction.addToBackStack(null);
                break;
        }
        transaction.commit();
    }

    public void createAccount(String id , String pw){

        mAuth.createUserWithEmailAndPassword(id, pw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this,"회원가입 완료",Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(SignUpActivity.this,"회원가입 실패",Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });



    }


}
