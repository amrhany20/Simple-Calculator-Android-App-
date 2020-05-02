package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String STATE_Currentoperation = " Currentoperation";
    private final String STATE_Operand1 = " operand1";
    String Currentoperation = "=";
    private EditText Result;
    private EditText newNUm;
    private TextView oper;
    //*********************
    private Double operand1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Result = findViewById(R.id.Result);
        newNUm = findViewById(R.id.NewNum);
        oper = findViewById(R.id.Operation);
        Button button0 = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonMul = findViewById(R.id.buttonMul);
        Button buttonDiv = findViewById(R.id.buttonDiv);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonSub = findViewById(R.id.buttonSub);
        Button buttonEqual = findViewById(R.id.buttonequal);
        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonDelete = findViewById(R.id.buttonDelete);
        Button buttonDot = findViewById(R.id.buttonDot);
        Button buttonNeg = findViewById(R.id.buttonNeg);

        //******************************************
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                newNUm.append(b.getText().toString());
            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

        // *******************************************
        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = newNUm.getText().toString();
                try {
                    Double Dvalue = Double.valueOf(value);
                    preformOperation(Dvalue, op);


                } catch (NumberFormatException e) {
                    newNUm.setText("");
                }
                Currentoperation = op;
                oper.setText(Currentoperation);

            }
        };

        buttonAdd.setOnClickListener(listener1);
        buttonSub.setOnClickListener(listener1);
        buttonMul.setOnClickListener(listener1);
        buttonDiv.setOnClickListener(listener1);
        buttonEqual.setOnClickListener(listener1);

//*********************************************************
        View.OnClickListener clearlistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Result.setText("");
                newNUm.setText("");
                oper.setText("");
                operand1 = null;
                Currentoperation = "=";
            }
        };
        buttonClear.setOnClickListener(clearlistener);

        View.OnClickListener negatelistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = newNUm.getText().toString();
                if (value.length() == 0)
                    newNUm.setText("-");
                else {
                    try {
                        Double Dvalue = Double.valueOf(value);
                        Dvalue *= -1;
                        newNUm.setText(Dvalue.toString());

                    } catch (NumberFormatException e) {
                        newNUm.setText("");
                    }

                }
            }
        };
        buttonNeg.setOnClickListener(negatelistener);

        View.OnClickListener dellistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = newNUm.getText().toString();

                if (value.length() == 0)
                    newNUm.setText("");
                else {
                    try {
                        value = value.substring(0, value.length() - 1);
                        newNUm.setText(value);


                    } catch (NumberFormatException e) {
                        newNUm.setText("");
                    }


                }
            }
        };
        buttonDelete.setOnClickListener(dellistener);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        Currentoperation = savedInstanceState.getString(STATE_Currentoperation);
        operand1 = savedInstanceState.getDouble(STATE_Operand1);
        oper.setText(Currentoperation);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_Currentoperation, Currentoperation);
        if (operand1 != null)
            outState.putDouble(STATE_Operand1, operand1);
        super.onSaveInstanceState(outState);
    }

    private void preformOperation(Double val, String operation) {

        if (null == operand1) {
            operand1 = val;

        } else {


            if (Currentoperation.equals("=")) {
                Currentoperation = operation;
            }
            switch (Currentoperation) {
                case "=":
                    operand1 = val;
                    break;
                case "/":
                    if (val == 0)
                        operand1 = 0.0;
                    else
                        operand1 /= val;
                    break;
                case "*":
                    operand1 *= val;
                    break;
                case "-":
                    operand1 -= val;
                    break;
                case "+":
                    operand1 += val;
                    break;

            }
        }
        Result.setText(operand1.toString());
        newNUm.setText("");
    }
}
