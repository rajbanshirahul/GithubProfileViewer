package np.com.rahulrajbanshi.githubprofileviewer;

/**
 * Created by RAHUL on 8/22/2017.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import np.com.rahulrajbanshi.githubprofileviewer.model.UserModel;

public class ViewerActivity extends AppCompatActivity {

    private String finalUrl;
    private UserModel userModel;
    private Bitmap bitmap;
    private ImageView profileAvatar;
    private TextView userFullName, userName, userId, accountType, isUserSiteAdmin, company, blog, location, email, bio, noOfFollowers, noOfUsersFollowing, noOfPublicRepos, noOfPublicGists, userCreated, userUpdated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

        //getting final URL from the MainActivity
        Bundle b = this.getIntent().getExtras();
        if (b != null)
            finalUrl = b.getString(MainActivity.FINALURLSTRINGTAG);

        // Parse Data from the github
        new JSONParsingTask().execute(finalUrl);

    }

    private void initializeViews() {
        profileAvatar = (ImageView) findViewById(R.id.profile_avatar);
        userFullName = (TextView) findViewById(R.id.userFullName);
        userName = (TextView) findViewById(R.id.userName);
        userId = (TextView) findViewById(R.id.userId);
        accountType = (TextView) findViewById(R.id.accountType);
        isUserSiteAdmin = (TextView) findViewById(R.id.siteAdmin);
        company = (TextView) findViewById(R.id.company);
        blog = (TextView) findViewById(R.id.blog);
        location = (TextView) findViewById(R.id.location);
        email = (TextView) findViewById(R.id.email);
        bio = (TextView) findViewById(R.id.bio);
        noOfFollowers = (TextView) findViewById(R.id.noOfFollowers);
        noOfUsersFollowing = (TextView) findViewById(R.id.noOfUsersFollowing);
        noOfPublicRepos = (TextView) findViewById(R.id.noOfPublicRepos);
        noOfPublicGists = (TextView) findViewById(R.id.noOfPublicGists);
        userCreated = (TextView) findViewById(R.id.userCreated);
        userUpdated = (TextView) findViewById(R.id.userUpdated);
    }

    private void setResults() {
        //Setting results
        if (bitmap != null)
            profileAvatar.setImageBitmap(bitmap);

        if (userModel != null) {
            userFullName.setText(userModel.getUserFullName());
            userName.setText(userModel.getUserName());
            userId.setText(String.valueOf(userModel.getUserId()));
            accountType.setText(userModel.getAccountType());
            isUserSiteAdmin.setText(String.valueOf(userModel.isUserSiteAdmin()));

            if (!userModel.getCompany().contains("null"))
                company.setText(userModel.getCompany());

            if (!userModel.getBlog().contains("null"))
                blog.setText(userModel.getBlog());

            if (!userModel.getLocation().contains("null"))
                location.setText(userModel.getLocation());

            if (!userModel.getEmail().contains("null"))
                email.setText(userModel.getEmail());

            if (!userModel.getBio().contains("null"))
                bio.setText(userModel.getBio());

            noOfFollowers.setText(String.valueOf(userModel.getNoOfFollowers()));
            noOfUsersFollowing.setText(String.valueOf(userModel.getNoOfUsersFollowing()));
            noOfPublicRepos.setText(String.valueOf(userModel.getNoOfPublicRepos()));
            noOfPublicGists.setText(String.valueOf(userModel.getNoOfPublicGists()));
            userCreated.setText(userModel.getUserCreated());
            userUpdated.setText(userModel.getUserUpdated());
        }
    }

    public class JSONParsingTask extends AsyncTask<String, String, UserModel> {

        @Override
        protected UserModel doInBackground(String... params) {

            BufferedReader bufferedReader = null;
            HttpURLConnection connection = null;
            StringBuffer buffer = null;

            try {

                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                buffer = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line + "");
                }
                
                // close the BufferedReader
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

            String finalJsonString = "";

            // if buffer is null or empty then cancel AsyncTask and display user not found
            if (buffer != null)
                finalJsonString = buffer.toString();
            else cancel(true);

            JSONObject jsonObject;
            userModel = new UserModel();
            try {
                jsonObject = new JSONObject(finalJsonString);

                userModel.setUserFullName(jsonObject.getString("name"));
                userModel.setUserName(jsonObject.getString("login"));
                userModel.setUserId(jsonObject.getInt("id"));
                userModel.setAvatarUrl(jsonObject.getString("avatar_url"));
                userModel.setAccountType(jsonObject.getString("type"));
                userModel.setUserSiteAdmin(jsonObject.getBoolean("site_admin"));
                userModel.setCompany(jsonObject.getString("company"));
                userModel.setBlog(jsonObject.getString("blog"));
                userModel.setLocation(jsonObject.getString("location"));
                userModel.setEmail(jsonObject.getString("email"));
                userModel.setBio(jsonObject.getString("bio"));
                userModel.setNoOfFollowers(jsonObject.getInt("followers"));
                userModel.setNoOfUsersFollowing(jsonObject.getInt("following"));
                userModel.setNoOfPublicRepos(jsonObject.getInt("public_repos"));
                userModel.setNoOfPublicGists(jsonObject.getInt("public_gists"));
                userModel.setUserCreated(jsonObject.getString("created_at"));
                userModel.setUserUpdated(jsonObject.getString("updated_at"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            // to get the bitmap stream of profile avatar
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(userModel.getAvatarUrl()).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return userModel;
        }

        @Override
        protected void onPostExecute(UserModel userModel) {
            super.onPostExecute(userModel);
            if (userModel != null) {

                //set user full name as title on toolbar
                setTitle(userModel.getUserFullName());

                //initialize views
                initializeViews();

                //set results that are obtained after parsing the user's data from the github
                setResults();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

            LinearLayout viewsLayout = (LinearLayout) findViewById(R.id.viewsLinearLayout);
            viewsLayout.setVisibility(View.GONE);

            TextView tvUserNotFound = (TextView) findViewById(R.id.tv_userNotFound);
            tvUserNotFound.setVisibility(View.VISIBLE);
            tvUserNotFound.setText(R.string.tvUserNotFound);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
