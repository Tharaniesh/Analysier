package trine.com.analysier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import trine.com.analyzer.R;

public class AnalyseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyse);

        // Back button
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the main activity
                Intent intent = new Intent(AnalyseActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optionally, finish the current activity
            }
        });

        // Packet Capture Button
        Button buttonCapture = findViewById(R.id.buttonCapture);
        buttonCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to Packet Capture activity
                Intent intent = new Intent(AnalyseActivity.this, PacketCaptureActivity.class);
                startActivity(intent);
            }
        });

        // Inspect Packets Button
        Button buttonInspect = findViewById(R.id.buttonInspect);
        buttonInspect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to Inspect Packets activity
                Intent intent = new Intent(AnalyseActivity.this, InspectPacketsActivity.class);
                startActivity(intent);
            }
        });

        // Protocol Analysis Button
        Button buttonProtocolAnalysis = findViewById(R.id.buttonProtocolAnalysis);
        buttonProtocolAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to Protocol Analysis activity
                Intent intent = new Intent(AnalyseActivity.this, ProtocolAnalysisActivity.class);
                startActivity(intent);
            }
        });

        // Traffic Monitoring Button
        Button buttonTrafficMonitoring = findViewById(R.id.buttonTrafficMonitoring);
        buttonTrafficMonitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to Traffic Monitoring activity
                Intent intent = new Intent(AnalyseActivity.this, TrafficMonitoringActivity.class);
                startActivity(intent);
            }
        });

        // Filter and Search Button
        Button buttonFilterSearch = findViewById(R.id.buttonFilterSearch);
        buttonFilterSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to Filter and Search activity
                Intent intent = new Intent(AnalyseActivity.this, FilterSearchActivity.class);
                startActivity(intent);
            }
        });

        // Export and Save Button
        Button buttonExportSave = findViewById(R.id.buttonExportSave);
        buttonExportSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to Export and Save activity
                Intent intent = new Intent(AnalyseActivity.this, ExportSaveActivity.class);
                startActivity(intent);
            }
        });

        // Custom Analysis Tools Button
        Button buttonCustomAnalysis = findViewById(R.id.buttonCustomAnalysis);
        buttonCustomAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to Custom Analysis Tools activity
                Intent intent = new Intent(AnalyseActivity.this, CustomAnalysisActivity.class);
                startActivity(intent);
            }
        });

    }
}
