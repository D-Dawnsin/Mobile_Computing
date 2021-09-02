package com.example.hw4tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends Activity {

    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();

    private static final NumberFormat percentFormat =
            NumberFormat.getPercentInstance();

    private double billAmount = 0.0;
    private double percent = 0.15;
    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            amountTextView = findViewById(R.id.amountTextView);
            percentTextView = findViewById(R.id.percentTextView);
            tipTextView = findViewById(R.id.tipTextView);
            totalTextView = findViewById(R.id.totalTextView);

            EditText amountEditText = findViewById(R.id.amountEditText);
            amountEditText.addTextChangedListener(amountEditTextWatcher);

            SeekBar percentSeekBar = findViewById(R.id.percentSeekBar);
            percentSeekBar.setOnSeekBarChangeListener(seekBarListener);


    }

    private void calculate()
    {
        percentTextView.setText(percentFormat.format(percent));

        double tip = billAmount * percent;
        double total = billAmount + tip;

        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));
    }

    private final OnSeekBarChangeListener seekBarListener = new OnSeekBarChangeListener()
    {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            percent = progress / 100.0;
            calculate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar)
        {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar)
        {

        }
    };

    private final TextWatcher amountEditTextWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            try
            {
                billAmount = Double.parseDouble(s.toString());
                amountTextView.setText(currencyFormat.format(billAmount));
            }

            catch (NumberFormatException e)
            {
                amountTextView.setText("");
                billAmount = 0.0;
            }

            calculate();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public void afterTextChanged(Editable s) {}

    public void beforeTextChanged( CharSequence s, int start,
                                   int count, int after) {}
}
