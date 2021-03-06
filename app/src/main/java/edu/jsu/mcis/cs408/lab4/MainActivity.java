package edu.jsu.mcis.cs408.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText et_bill;
    TextView tv_tip, tv_ppl, c_tip_out, c_total_out;
    Button b_calc, p_tip, m_tip, p_ppl, m_ppl;

    int tipPercent = 0;
    int peopleCount = 1;
    double billInit = 100.00;
    double tipOutput = 0;
    double totalOutput = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_bill = findViewById(R.id.et_bill);
        tv_tip = findViewById(R.id.tv_tip);
        tv_ppl = findViewById(R.id.tv_ppl);
        c_tip_out = findViewById(R.id.c_tip_out);
        c_total_out = findViewById(R.id.c_total_out);
        b_calc = findViewById(R.id.b_calc);
        p_tip = findViewById(R.id.p_tip);
        m_tip = findViewById(R.id.m_tip);
        p_ppl = findViewById(R.id.p_ppl);
        m_ppl = findViewById(R.id.m_ppl);

        b_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String billString = et_bill.getText().toString();
                if (!billString.equals("")){
                    //initial bill
                    billInit = Double.valueOf(billString);

                    billInit = billInit*100;
                    billInit = Math.round(billInit);
                    billInit = billInit / 100;

                    et_bill.setText(String.format(Locale.getDefault(), "%.2f", billInit));

                    tipOutput = (billInit * tipPercent) / 100;

                    tipOutput = tipOutput*100;
                    tipOutput = Math.round(tipOutput);
                    tipOutput = tipOutput / 100;

                    if (peopleCount == 1) {

                        c_tip_out.setText(String.format(Locale.getDefault(), "%.2f", tipOutput));

                        totalOutput = billInit + tipOutput;
                        c_total_out.setText(String.format(Locale.getDefault(), "%.2f", totalOutput));
                    }
                    else {
                        totalOutput = billInit + tipOutput;
                        totalOutput = totalOutput / peopleCount;
                        c_total_out.setText(String.format(Locale.getDefault(), "%.2f", totalOutput));

                        tipOutput = tipOutput / peopleCount;
                        c_tip_out.setText(String.format(Locale.getDefault(), "%.2f", tipOutput));
                    }
                }
            }
        });

        p_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercent++;
                tv_tip.setText(tipPercent + "%");
            }
        });

        m_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipPercent > 0) {
                    tipPercent--;
                    tv_tip.setText(tipPercent + "%");
                }
            }
        });

        p_ppl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peopleCount++;
                tv_ppl.setText(peopleCount + "");
            }
        });

        m_ppl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (peopleCount > 1) {
                    peopleCount--;
                    tv_ppl.setText(peopleCount + "");
                }
            }
        });

    }
}