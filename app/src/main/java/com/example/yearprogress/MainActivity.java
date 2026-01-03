package com.example.yearprogress;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        boolean isLeapYear = (currentYear % 4 == 0 && currentYear % 100 != 0)||(currentYear % 400 == 0);
        int totalDays = isLeapYear ? 366:365;
        int daysLeft = totalDays - dayOfYear;
        int percentageCompleted = (int)((dayOfYear*100.0f)/totalDays);

        TextView tvYear = findViewById(R.id.tvYear);
        TextView tvPercent = findViewById(R.id.tvPercent);
        TextView tvDaysLeft = findViewById(R.id.tvDaysLeft);

        tvYear.setText(String.valueOf(currentYear));
        tvPercent.setText(percentageCompleted + "% completed");
        tvDaysLeft.setText(daysLeft + " days left");


        RecyclerView recyclerView = findViewById(R.id.dotsRecycler);

        int columns = 14; // you can tweak this later
        recyclerView.setLayoutManager(
                new GridLayoutManager(this, columns)
        );

        DotAdapter adapter = new DotAdapter(this, totalDays, dayOfYear);
        recyclerView.setAdapter(adapter);

        Log.d("YEAR_PROGRESS","Year:"+currentYear+",Days:"+dayOfYear+",Left:"+daysLeft+",Done:"+percentageCompleted+"%");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}