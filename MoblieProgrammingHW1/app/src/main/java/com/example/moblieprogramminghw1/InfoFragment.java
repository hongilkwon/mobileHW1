package com.example.moblieprogramminghw1;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InfoFragment extends Fragment {

    private SignUpActivity signUpActivity;

    private EditText email, pw, pwconfirm, phoneNum, address;
    private Button completeButton;


    String id;
    String password;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        signUpActivity = (SignUpActivity) getActivity();
        email = view.findViewById(R.id.infoFragmentEmail);
        pw = view.findViewById(R.id.infoFragmentPW);
        pwconfirm = view.findViewById(R.id.infoFragmentPWConfirm);
        phoneNum = view.findViewById(R.id.infoFragmentPhoneNum);
        address = view.findViewById(R.id.infoFragmentAddress);

        completeButton = view.findViewById(R.id.infoFragmentCompleteButton);
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = email.getText().toString().trim().replace(" ", "");
                String password = pw.getText().toString().trim().replace(" ", "");
                if(validationSignUp()){
                    signUpActivity.createAccount(id, password);
                }else {
                    //검사를 통과하지 못한경우.
                }
            }
        });
        return view;
    }

    private boolean validationSignUp(){
        if( !validationEditText(email) || !validationEditText(pw) || !validationEditText(pwconfirm) ||
                !validationEditText(phoneNum) || !validationEditText(address)){

            Toast.makeText(getContext(),"입력하지 않은 정보가 있습니다.",Toast.LENGTH_SHORT).show();
            return false;
        }
        if( !validationEmail(email.getText().toString().trim().replace(" ", "")) ){
            Toast.makeText(getContext(),"아이디는 Email 형식입니다.",Toast.LENGTH_SHORT).show();
            return false;
        }
        if( !validationPasswd(pw.getText().toString().trim().replace(" ", ""))){

            Toast.makeText(getContext(),"비밀번호는 최소 8자리 문자 특수문자 숫자로 1개 이상 포함됩니다.",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }




    private boolean validationEditText(EditText editText){
        String str = editText.getText().toString().trim().replace(" ", "");
        if(str.isEmpty()){
            return false;
        }
        return true;
    }

    private boolean validationPasswd(String pw){
        Pattern p = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$");
        Matcher m = p.matcher(pw);

        if(m .matches()){
            return true;
        }
        return false;
    }

    private boolean validationEmail(String email){
        Pattern p = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");
        Matcher m = p.matcher(email);

        if(m .matches()){
            return true;
        }
        return false;
    }
}
