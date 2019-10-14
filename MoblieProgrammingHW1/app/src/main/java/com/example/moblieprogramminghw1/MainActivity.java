package com.example.moblieprogramminghw1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser user;

    private ListView listView;
    private ListAdapter adapter;
    private EditText toDoTitle, toDoContents;
    private Button addButton;
    private ArrayList<ToDo> toDoList = new ArrayList<ToDo>();

    private int nextToDoNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = mAuth.getCurrentUser();

        listView = findViewById(R.id.listView);
        toDoTitle = findViewById(R.id.titleEditText);
        toDoContents = findViewById(R.id.contentsEditText);

        //리스트뷰에 어뎁터 설정
         adapter = new ListAdapter();
        listView.setAdapter(adapter);
        // 이전 리스트 가져옴.
        getData();

        addButton = findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> toDoMap = new HashMap<>();

                nextToDoNum = ToDo.num+1;
                String title = toDoTitle.getText().toString();
                String contents = toDoContents.getText().toString();

                toDoMap.put("num",nextToDoNum);
                toDoMap.put("title", title);
                toDoMap.put("contents", contents);

                db.collection(user.getEmail()).document()
                        .set(toDoMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        ToDo toDo = new ToDo(nextToDoNum,toDoTitle.getText().toString(), toDoContents.getText().toString());
                        toDoList.add(toDo);
                        adapter.notifyDataSetChanged();

                    }
                })    .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "데이터 전송 실패",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }


    private void getData(){
        db.collection(user.getEmail()).orderBy("num").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        Long temp = (Long)document.get("num");
                        ToDo toDo = new ToDo(temp.intValue(), (String)document.get("title"), (String)document.get("contents"));
                        toDoList.add(toDo);
                    }
                    adapter.notifyDataSetChanged();
                    ToDo.num = toDoList.size();
                } else {
                    Toast.makeText(MainActivity.this, "이전 데이터를 가져올 수 없습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return toDoList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // 재사용 가능한 뷰가 없다면 view를 생성하고
            if(convertView == null){
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.todo_list_item,null);
            }

            TextView title = convertView.findViewById(R.id.itemTitle);
            title.setText(toDoList.get(position).getTitle());

            TextView contents = convertView.findViewById(R.id.itemContents);
            contents.setText(toDoList.get(position).getContents());
            return convertView;
        }
    }
}
