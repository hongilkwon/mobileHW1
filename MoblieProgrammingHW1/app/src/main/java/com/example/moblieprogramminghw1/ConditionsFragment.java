package com.example.moblieprogramminghw1;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConditionsFragment extends Fragment {


    private SignUpActivity signUpActivity;
    private Button nextButton;
    private RadioGroup radioGroup;

    private boolean conditionAgree;

    public ConditionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_conditions, container, false);

        signUpActivity = (SignUpActivity) getActivity();
        nextButton = view.findViewById(R.id.conditionsFragmentNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioGroup.getCheckedRadioButtonId() == R.id.conditionsFragmentAgreeRadioButton){
                    signUpActivity.settingFragment(SignUpActivity.SIGNUP_INFO);
                }else{
                    Toast.makeText(getContext(),"약관에 동의 하지 않으셨습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        radioGroup = view.findViewById(R.id.conditionsFragmentRadioGroup);
        return view;
    }

}
