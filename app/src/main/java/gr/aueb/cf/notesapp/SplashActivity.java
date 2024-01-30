package gr.aueb.cf.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
/**
 * Splash screen activity to check the user's authentication status and navigate accordingly.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Using a handler to delay the activity transition for a short period.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Check if a user is authenticated.
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                // If no user is authenticated, navigate to the LoginActivity.
                if(currentUser==null){
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                }else{
                    // If a user is authenticated, navigate to the MainActivity.
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }
                // Finish the SplashActivity to prevent navigating back to it
                finish();
            }
        },1000); // Delay for 1000 milliseconds (1 second)

    }
    }
