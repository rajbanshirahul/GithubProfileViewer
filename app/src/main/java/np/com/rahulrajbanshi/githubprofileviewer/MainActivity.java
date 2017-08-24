package np.com.rahulrajbanshi.githubprofileviewer;

/**
 * Created by RAHUL on 8/22/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String userNameTextInput = "";
    public static final String FINALURLSTRINGTAG= "finalStringTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpButtonSubmit();
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
