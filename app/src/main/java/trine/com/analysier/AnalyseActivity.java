package trine.com.analysier;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import trine.com.analyzer.R;

public class AnalyseActivity extends AppCompatActivity {

    private EditText searchEditText;
    private ImageButton searchButton;
    private TextView networkListTextView;
    private TextView pingTextView;
    private TextView hopCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        searchEditText = findViewById(R.id.search_edit_text);
        searchButton = findViewById(R.id.search_button);
        networkListTextView = findViewById(R.id.network_list_text_view);
        pingTextView = findViewById(R.id.ping);
        hopCountTextView = findViewById(R.id.hop_count);

        // Set click listener for the search button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchNetwork();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.hop_menu) {
            // Start the HopActivity
            Intent hopIntent = new Intent(this, HopActivity.class);
            startActivity(hopIntent);
            return true;
        }
        if (id == R.id.ping_menu) {
            // Start the PingActivity
            Intent pingIntent = new Intent(this, PingActivity.class);
            startActivity(pingIntent);
            return true;
        }
        if (id == R.id.analyse_menu) {
            // Start the AnalyseActivity
            Intent analyseIntent = new Intent(this, AnalyseActivity.class);
            startActivity(analyseIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void searchNetwork() {
        // Get the entered network from the EditText
        String enteredNetwork = searchEditText.getText().toString().trim();

        // Dummy nearby network devices
        String[] nearbyDevices = {"Device 1", "Device 2", "Device 3"};

        // Dummy ping and hop count
        int ping = 50;
        int hopCount = 5;

        // Check if the entered network is presented in the nearby devices
        boolean networkFound = false;
        for (String device : nearbyDevices) {
            if (device.equalsIgnoreCase(enteredNetwork)) {
                networkFound = true;
                break;
            }
        }

        // Prepare the text to display
        String displayText;
        if (networkFound) {
            // Network found, concatenate ping and hop count with existing text
            displayText = networkListTextView.getText().toString() +
                    "\nPing: " + ping + "\nHop Count: " + hopCount;
            // Display ping and hop count
            pingTextView.setText("Ping: " + ping);
            hopCountTextView.setText("Hop Count: " + hopCount);
        } else {
            // Network not found
            displayText = networkListTextView.getText().toString() + "\nNetwork not found";
            pingTextView.setText("Ping: N/A");
            hopCountTextView.setText("Hop Count: N/A");
        }

        // Set the updated text to the TextView
        networkListTextView.setText(displayText);
    }
}
