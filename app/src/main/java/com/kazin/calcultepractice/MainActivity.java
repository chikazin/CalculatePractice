package com.kazin.calcultepractice;

import android.app.AlertDialog;
import android.app.Instrumentation;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int answer;
    int userInput;
    int score = 0;
    TextView expression;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText userInputView = (EditText) findViewById(R.id.editText1);
        final TextView scoreView = (TextView) findViewById(R.id.score);
        expression = (TextView) findViewById(R.id.expression1);


        final Button button_1 = (Button) findViewById(R.id.one);
        final Button button_2 = (Button) findViewById(R.id.two);
        final Button button_3 = (Button) findViewById(R.id.three);
        final Button button_4 = (Button) findViewById(R.id.four);
        final Button button_5 = (Button) findViewById(R.id.five);
        final Button button_6 = (Button) findViewById(R.id.six);
        final Button button_7 = (Button) findViewById(R.id.senven);
        final Button button_8 = (Button) findViewById(R.id.eight);
        final Button button_9 = (Button) findViewById(R.id.nine);
        final Button button_0 = (Button) findViewById(R.id.zero);
        final Button button_delete = (Button) findViewById(R.id.delete);
        final Button button_minus = (Button) findViewById(R.id.minus);
        final Button button_point = (Button) findViewById(R.id.point);
        final Button button_commit = (Button) findViewById(R.id.commit);

        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_0.setOnClickListener(this);
        button_delete.setOnClickListener(this);
        button_delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                keyDown(KeyEvent.KEYCODE_CLEAR);
                userInputView.setText("");
                return false;
            }
        });
        button_minus.setOnClickListener(this);
        button_point.setOnClickListener(this);
        button_commit.setOnClickListener(this);


        generateExpression();


        userInputView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInputView.setText("");
                //禁用软键盘
/*                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }*/
            }
        });

        userInputView.callOnClick();

        userInputView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (null != keyEvent && KeyEvent.KEYCODE_ENTER == keyEvent.getKeyCode()) {
                    if (keyEvent.getAction() == KeyEvent.ACTION_UP) {
                        try {
                            userInput = Integer.parseInt(userInputView.getText().toString());
                            Log.e("line58", "" + userInput);
                            if (answer == userInput) {
                                score += 7;
                                scoreView.setText("Your current score is: " + score);
                                generateExpression();
                                userInputView.setText("");

                            } else {
                                score = 0;
                                scoreView.setText("Your current score is: " + score);
                                AlertDialog.Builder dialog1 = new AlertDialog.Builder(MainActivity.this);
                                dialog1.setTitle("Inaccuracy").setMessage("your answer is:" + userInput + ", but the exact answer is:" + answer);
                                dialog1.setPositiveButton("ok", null).show();
                                userInputView.setText("");
                            }
                        } catch (Exception e) {
                            Log.e("line156", "捕捉到异常" + e.toString());
                            e.printStackTrace();
                            AlertDialog.Builder dialog1 = new AlertDialog.Builder(MainActivity.this);
                            dialog1.setTitle("Warning!").setMessage("You didn't input a valid answer");
                            dialog1.setPositiveButton("ok", null).show();
                            userInputView.setText("");
                        }
                        return true;
                    }
                }
                return true;
            }
        });
    }

    public void keyDown(final int keyCode) {
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(keyCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one:
                keyDown(KeyEvent.KEYCODE_1);
                break;
            case R.id.two:
                keyDown(KeyEvent.KEYCODE_2);
                break;
            case R.id.three:
                keyDown(KeyEvent.KEYCODE_3);
                break;
            case R.id.four:
                keyDown(KeyEvent.KEYCODE_4);
                break;
            case R.id.five:
                keyDown(KeyEvent.KEYCODE_5);
                break;
            case R.id.six:
                keyDown(KeyEvent.KEYCODE_6);
                break;
            case R.id.senven:
                keyDown(KeyEvent.KEYCODE_7);
                break;
            case R.id.eight:
                keyDown(KeyEvent.KEYCODE_8);
                break;
            case R.id.nine:
                keyDown(KeyEvent.KEYCODE_9);
                break;
            case R.id.zero:
                keyDown(KeyEvent.KEYCODE_0);
                break;
            case R.id.delete:
                keyDown(KeyEvent.KEYCODE_DEL);
                break;
            case R.id.minus:
                keyDown(KeyEvent.KEYCODE_MINUS);
                break;
            case R.id.point:
                keyDown(KeyEvent.KEYCODE_NUMPAD_DOT);
                break;
            case R.id.commit:
                keyDown(KeyEvent.KEYCODE_ENTER);
                break;
        }
    }

    public void generateExpression() {
        int type = (int) (Math.random() * 4);
        int a = (int) (Math.random() * 100) + 1;
        int b = (int) (Math.random() * 100) + 1;
        switch (type) {
            case 0:
                answer = a + b;
                expression.setText(a + "+" + b + "=?");
                break;
            case 1:
                answer = a - b;
                expression.setText(a + "-" + b + "=?");
                break;
            case 2:
                answer = a * b;
                expression.setText(a + "*" + b + "=?");
                break;
            case 3:
                answer = (a % b);
                expression.setText(a + "%" + b + "=?");
                break;
            default:
                break;
        }
        Log.e("line46", answer + "");
    }
}