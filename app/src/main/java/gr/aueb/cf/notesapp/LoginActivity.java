package gr.aueb.cf.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
/**
 * Activity for user login using Firebase Authentication.
 */
public class LoginActivity extends AppCompatActivity {

    // UI elements
    EditText emailEditText,passwordEditText;
    Button loginBtn;
    ProgressBar progressBar;
    TextView createAccountBtnTextView;

    /**
     * Initializes the UI elements and sets up click listeners.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI elements
        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        loginBtn = findViewById(R.id.login_btn);
        progressBar = findViewById(R.id.progress_bar);
        createAccountBtnTextView = findViewById(R.id.create_account_text_view_btn);

        // Set click listeners
        loginBtn.setOnClickListener((v)-> loginUser() );
        createAccountBtnTextView.setOnClickListener((v)->startActivity(new Intent(LoginActivity.this,CreateAccountActivity.class)) );

    }

    /**
     * Validates user input and triggers the login process.
     */
    void loginUser(){
        String email  = emailEditText.getText().toString();
        String password  = passwordEditText.getText().toString();

        // Validate user input
        boolean isValidated = validateData(email,password);
        if(!isValidated){
            return;
        }

        // Initiate Firebase login
        loginAccountInFirebase(email,password);

    }

    /**
     * Logs in the user using Firebase Authentication.
     *
     * @param email    User's email address.
     * @param password User's password.
     */
    void loginAccountInFirebase(String email,String password){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        changeInProgress(true);
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                changeInProgress(false);
                if(task.isSuccessful()){
                    // Login successful
                    if(firebaseAuth.getCurrentUser().isEmailVerified()){
                        // Go to MainActivity
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
                    }else{

                        Utility.showToast(LoginActivity.this,"Email not verified, Please verify your email.");
                    }

                }else{
                    // Login failed
                    Utility.showToast(LoginActivity.this,task.getException().getLocalizedMessage());
                }
            }
        });
    }

    /**
     * Toggles the visibility of the progress bar and login button.
     *
     * @param inProgress True if in progress, false otherwise.
     */
    void changeInProgress(boolean inProgress){
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Validates user input data.
     *
     * @param email    User's email address.
     * @param password User's password.
     * @return True if the input data is valid, false otherwise.
     */
    boolean validateData(String email,String password){
        // Validate the data input by the user.
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Email is invalid");
            return false;
        }
        if(password.length()<6){
            passwordEditText.setError("Password length is invalid");
            return false;
        }
        return true;
    }
}