package np.com.rahulrajbanshi.githubprofileviewer;

/**
 * Created by RAHUL on 8/22/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.security.ProviderInstaller;

public class MainActivity extends AppCompatActivity {

    private String userNameTextInput = "";
    public static final String FINALURLSTRINGTAG= "finalStringTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // update Android's security Provider if system is older than Lollipop
        if (Build.VERSION.SDK_INT < 21)
            updateAndroidSecurityProvider(this);

        setUpButtonSubmit();
    }

    private void updateAndroidSecurityProvider(Activity callingActivity) {
        try {
            ProviderInstaller.installIfNeeded(this);
        } catch (GooglePlayServicesRepairableException e) {
            // Thrown when Google Play Services is not installed, up-to-date, or enabled
            // Show dialog to allow users to install, update, or otherwise enable Google Play services.
            GooglePlayServicesUtil.getErrorDialog(e.getConnectionStatusCode(), callingActivity, 0);
        } catch (GooglePlayServicesNotAvailableException e) {
            Log.e("SecurityException", "Google Play Services not available.");
        }
    }

    private void setUpButtonSubmit() {
        Button btnSubmit = (Button) findViewById(R.id.buttonSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String partialUrl = "https://api.github.com/users/";
                EditText etUserName = (EditText) findViewById(R.id.etUserName);
                userNameTextInput = etUserName.getText().toString();

                if (!userNameTextInput.isEmpty()) {
                    String finalUrl = partialUrl + userNameTextInput.trim();

                    Intent intent = new Intent(MainActivity.this, ViewerActivity.class);
                    Bundle b = new Bundle();
                    b.putString(FINALURLSTRINGTAG, finalUrl);
                    intent.putExtras(b);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter username", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
