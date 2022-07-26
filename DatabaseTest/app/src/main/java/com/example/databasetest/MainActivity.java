package com.example.databasetest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;

    EditText editTextName, editTextPhone, editTextAddress, editTextID;
    Button buttonInsert, buttonView, buttonUpdate, buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextID = findViewById(R.id.editTextID);

        buttonInsert = findViewById(R.id.buttonInsert);
        buttonView = findViewById(R.id.buttonView);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete =findViewById(R.id.buttonDelete);


        AddData();
        viewAll();
        UpdateData();
        DeleteData();



    }

    //데이터베이스 추가하기
    public void AddData(){
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertData(editTextName.getText().toString(),
                        editTextPhone.getText().toString(),
                        editTextAddress.getText().toString());

                if(isInserted == true)
                    Toast.makeText(MainActivity.this,"데이터추가 성공",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"데이터추가 실패",Toast.LENGTH_LONG).show();
            }
        });
    }

    // 데이터베이스 읽어오기
    public void viewAll()
    {
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.getAllData();
                if(res.getCount() == 0){
                    ShowMessage("실패","데이터를 찾을 수 없습니다.");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID: "+res.getString(0)+"\n");
                    buffer.append("이름: "+res.getString(1)+"\n");
                    buffer.append("전화번호: "+res.getString(2)+"\n");
                    buffer.append("주소: "+res.getString(3)+"\n\n");
                }
                ShowMessage("데이터",buffer.toString());


            }
        });

    }

    //데이터베이스 수정하기
    public void UpdateData(){
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = myDB.updateData(editTextID.getText().toString(),
                        editTextName.getText().toString(),
                        editTextPhone.getText().toString(),
                        editTextAddress.getText().toString());

                if(isUpdated == true)
                    Toast.makeText(MainActivity.this,"데이터 수정 성공",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"데이터 수정 실패",Toast.LENGTH_LONG).show();



            }
        });
    }

    // 데이터베이스 삭제하기
    public void DeleteData(){
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows = myDB.deleteData(editTextID.getText().toString());
                if(deleteRows>0)
                    Toast.makeText(MainActivity.this,"데이터 삭제 성공",Toast.LENGTH_LONG ).show();
                else
                    Toast.makeText(MainActivity.this,"데이터 삭제 실패",Toast.LENGTH_LONG ).show();
            }
        });
    }




    public void ShowMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}