package com.example.tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String exp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        EditText expr = findViewById(R.id.expression);
        Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, addBtn, mulBtn, devBtn, subBtn, deleteBtn, clearBtn, calculateBtn;
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

                    ArrayList<Character> arr = new ArrayList<Character>();
                    if (!(exp.charAt(0) >= '0' && exp.charAt(0) <= '9')) {
                        expr.setText("오류");
                    } else {
                        arr.add(exp.charAt(0));
                        for (int i = 1; i < exp.length(); i++) {
                            if ((!(exp.charAt(i - 1) >= '0' && exp.charAt(i - 1) <= '9') && (exp.charAt(i) >= '0' && exp.charAt(i) <= '9'))) { // 문 수
                                arr.add(' ');
                            } else if (((exp.charAt(i - 1) >= '0' && exp.charAt(i - 1) <= '9') && !(exp.charAt(i) >= '0' && exp.charAt(i) <= '9'))) {
                                arr.add(' ');   // 수 문
                            } else if ((!(exp.charAt(i - 1) >= '0' && exp.charAt(i - 1) <= '9') && !((exp.charAt(i) >= '0' && exp.charAt(i) <= '9')))) {
                                arr.add(' ');
                            }

                            arr.add(exp.charAt(i));
                        }
                    }
                    StringBuffer sb = new StringBuffer();

                    for (int i = 0; i < arr.size(); i++) {
                        sb.append(arr.get(i));
                    }
                    try {
                        calculator.cal(sb.toString());
                        expr.setText(calculator.getAnswer());
                        exp = "";
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

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exp = "";
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

    public boolean isOperator(String s) {
        try {
            Integer.parseInt(s); // s �� ���ڷ� ��ȯ �Ǹ� �����ڰ� �ƴϹǷ� false
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public void addClick(View v) {

    }

    public void subClick(View v) {

    }

    public void mulClick(View v) {

    }

    public void devClick(View v) {


    }
}