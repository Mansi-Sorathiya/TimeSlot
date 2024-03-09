package com.example.timeslot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText editText1, editText2;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = findViewById(R.id.edit1);
        editText2 = findViewById(R.id.edit2);
        button = findViewById(R.id.btn);
        textView = findViewById(R.id.text);

        button = findViewById(R.id.btn);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                generateTimeSlots();

            }
        });
    }

    private void generateTimeSlots() {
        String startTimeText = editText1.getText().toString();
        String endTimeText = editText2.getText().toString();

        if (!startTimeText.isEmpty() && !endTimeText.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.US);

            try {
                Date startDate = sdf.parse(startTimeText);
                Date endDate = sdf.parse(endTimeText);
                long slotDurationInMillis = 10 * 60 * 1000L; // 10 minutes in milliseconds

                Date currentSlotTime = startDate;

                StringBuilder outputText = new StringBuilder();

                while (currentSlotTime.before(endDate)) {
                    String slotEndTime = sdf.format(new Date(currentSlotTime.getTime() + slotDurationInMillis));
                    outputText.append("Slot: ").append(sdf.format(currentSlotTime))
                            .append(" to ").append(slotEndTime).append("\n");

                    currentSlotTime = new Date(currentSlotTime.getTime() + slotDurationInMillis);
                }

                textView.setText(outputText.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

}

