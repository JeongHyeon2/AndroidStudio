package com.example.tutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String exp = "";
    static int prime=7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = findViewById (R.id.toolbar_main);
        setSupportActionBar (toolbar); //액티비티의 앱바(App Bar)로 지정

        setContentView(R.layout.activity_main);
        EditText expr = findViewById(R.id.expression);
        expr.setEnabled(false);

        Button button1, button2, button3, button4, button5,
                button6, button7, button8, button9, button0, addBtn, mulBtn, pointBtn,
                devBtn, subBtn, deleteBtn, clearBtn, calculateBtn, openBracket,closeBracket,modulo;
        ImageButton btn_setting;

        pointBtn = findViewById(R.id.button_point);
        openBracket = findViewById(R.id.openBracket);
        closeBracket = findViewById(R.id.closeBracket);
        modulo = findViewById(R.id.btnModulo);
        calculateBtn = findViewById(R.id.calculate);
        clearBtn = findViewById(R.id.clearBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        addBtn = findViewById(R.id.addBtn);
        subBtn = findViewById(R.id.subBtn);
        mulBtn = findViewById(R.id.mulBtn);
        devBtn = findViewById(R.id.devBtn);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        btn_setting = findViewById(R.id.btn_setting);




        try {
            calculateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Calculator calculator = null;
                    try {
                        calculator = new Calculator();
                    } catch (Exception e) {

                    }
                    if(exp.equals("")) return;

                    ArrayList<Character> arr = new ArrayList<>();
                    if (!(exp.charAt(0) >= '0' && exp.charAt(0) <= '9')&&exp.charAt(0)!='-') {
                        expr.setText("오류");
                    } else {
                        arr.add(exp.charAt(0));
                        for (int i = 1; i < exp.length(); i++) {
                            if (!isNumber(exp.charAt(i-1)) && isNumber(exp.charAt(i))) { // 문 수
                                arr.add(' ');
                            } else if (isNumber(exp.charAt(i-1)) && !isNumber(exp.charAt(i))) {
                                arr.add(' ');   // 수 문
                            } else if (!isNumber(exp.charAt(i-1)) && !isNumber(exp.charAt(i))) {
                                arr.add(' '); //문 문
                            }
                            arr.add(exp.charAt(i));
                        }
                    }
                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < arr.size(); i++) {
                        sb.append(arr.get(i));
                    }

                    try {
                        calculator.cal(sb.toString());
                        String answer = calculator.getAnswer();
                        String [] temp = answer.split("\\.");
                        if(temp.length>1){
                            if(isZero(temp[1])){
                                expr.setText(temp[0]);
                                exp = temp[0];
                            }else{
                                answer = String.format("%."+prime+"f", Double.parseDouble(answer));
                                expr.setText(answer);
                                exp = answer;
                            }
                        }else {
                            answer = String.format("%."+prime+"f", answer);
                            expr.setText(answer);
                            exp = answer;
                        }
                    } catch (Exception e) {
                        expr.setText("오류");
                        exp = "";
                        e.printStackTrace();
                        return;
                    }
                }
            });

        } catch (Exception e) {

        }
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PopUpActivity.class);
                startActivity(intent);
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp = "";
                expr.setText(exp);
            }
        });
        modulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp+="%";
                expr.setText(exp);
            }
        });
        closeBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp+=")";
                expr.setText(exp);
            }
        });
        openBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "(";
                expr.setText(exp);
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!exp.equals("")) {
                    exp = exp.substring(0, exp.length() - 1);
                }
                expr.setText(exp);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "+";
                expr.setText(exp);

            }
        });
        mulBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "*";
                expr.setText(exp);
            }
        });
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "-";
                expr.setText(exp);
            }
        });
        devBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "/";
                expr.setText(exp);
            }
        });
        pointBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp+=".";
                expr.setText(exp);
            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "0";
                expr.setText(exp);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "1";
                expr.setText(exp);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "2";
                expr.setText(exp);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "3";
                expr.setText(exp);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "4";
                expr.setText(exp);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "5";
                expr.setText(exp);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "6";
                expr.setText(exp);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "7";
                expr.setText(exp);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "8";
                expr.setText(exp);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp += "9";
                expr.setText(exp);
            }
        });


    }

    public boolean isZero(String s){

        for(int i=0;i<s.length();i++){
            if(!s.equals("0")) return false;

        }
        return true;
    }

    public boolean isNumber(char c){
        if(c>='0'&& c<='9' || c=='.') return true;
        return false;
    }

}