package trine.com.analysier;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PingActivity extends AppCompatActivity {

    private EditText domainEditText;
    private TextView pingDetailsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);

        // Initialize views
        domainEditText = findViewById(R.id.ping_domain);
        pingDetailsTextView = findViewById(R.id.ping_details);
    }

    // Options Menu Item Click Listener
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.hop_menu:
                startActivity(new Intent(this, HopActivity.class));
                return true;
            case R.id.ping_menu:
                startActivity(new Intent(this, PingActivity.class));
                return true;
            case R.id.analyse_menu:
                startActivity(new Intent(this, AnalyseActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Ping Domain Method
    private void pingDomain() {
        // Get the entered domain name or IP address
        final String input = domainEditText.getText().toString().trim();

        // Check if the input is an IP address
        if (isValidIpAddress(input)) {
            // If it's an IP address, directly ping it
            pingIpAddress(input);
        } else {
            // If it's not an IP address, assume it's a domain name and resolve it
            resolveAndPingDomain(input);
        }
    }

    // Validate IP Address Method
    private boolean isValidIpAddress(String input) {
        // Regular expression to check if the input is a valid IP address
        String ipPattern = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        return input.matches(ipPattern);
    }

    // Ping IP Address Method
    private void pingIpAddress(final String ipAddress) {
        // Execute ping command in background for the provided IP address
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    // Perform ping command
                    Process pingProcess = Runtime.getRuntime().exec("ping -c 4 " + ipAddress);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(pingProcess.getInputStream()));
                    StringBuilder output = new StringBuilder();
                    String line;
                    // Read output of ping command
                    while ((line = reader.readLine()) != null) {
                        output.append(line).append("\n");
                    }
                    return output.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                if (result != null) {
                    // Display ping details
                    pingDetailsTextView.setText(result);
                } else {
                    // Unable to perform ping
                    pingDetailsTextView.setText("Unable to perform ping for " + ipAddress);
                }
            }
        }.execute();
    }

    // Resolve and Ping Domain Method
    private void resolveAndPingDomain(final String domainName) {
        // Resolve the domain name to an IP address
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    // Perform DNS resolution
                    return java.net.InetAddress.getByName(domainName).getHostAddress();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String ipAddress) {
                if (ipAddress != null) {
                    // If resolved successfully, ping the IP address
                    pingIpAddress(ipAddress);
                } else {
                    // Unable to resolve domain name
                    pingDetailsTextView.setText("Unable to resolve domain name: " + domainName);
                }
            }
        }.execute();
    }
}
