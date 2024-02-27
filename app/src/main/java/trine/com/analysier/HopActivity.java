package trine.com.analysier;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.pingidentity.android.net.dns.TracerouteContainer;
import com.pingidentity.android.net.dns.TracerouteUtils;
import java.net.UnknownHostException;
import java.util.List;

public class HopActivity extends AppCompatActivity {

    private EditText hopCountTextBox;
    private Button countButton;
    private TextView hopCountDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hop);

        // Initialize views
        hopCountTextBox = findViewById(R.id.hop_count_text_box);
        countButton = findViewById(R.id.hop_count_button);
        hopCountDetails = findViewById(R.id.hop_count_details);

        // Set click listener for the count button
        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countHop();
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
        if (id == R.id.menu_button) {
            // Handle menu button click
            Toast.makeText(this, "Menu button clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void countHop() {
        // Get the entered domain name
        final String domainName = hopCountTextBox.getText().toString().trim();

        // Execute traceroute in background
        new AsyncTask<Void, Void, List<TracerouteContainer>>() {
            @Override
            protected List<TracerouteContainer> doInBackground(Void... voids) {
                try {
                    // Perform traceroute
                    return TracerouteUtils.executeTraceroute(domainName);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<TracerouteContainer> result) {
                if (result != null && !result.isEmpty()) {
                    // Extract hop count from traceroute result
                    int hopCount = result.size();
                    // Display hop count details
                    hopCountDetails.setText("Hop Count for " + domainName + ": " + hopCount);
                } else {
                    // Unable to perform traceroute
                    hopCountDetails.setText("Unable to perform traceroute for " + domainName);
                }
            }
        }.execute();
    }
}
