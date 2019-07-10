package dvelopd.firebaseauthtutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private EditText usernameET, passwordET;
    private Button registerButton;
    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        registerButton = findViewById(R.id.registerButton);
        mDialog = new ProgressDialog(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameET.getText().toString().isEmpty() || passwordET.getText().toString().isEmpty()){
                    Toast.makeText(v.getContext(), "Please fill in username AND password", Toast.LENGTH_SHORT);
                }else {
                    String email = usernameET.getText().toString().trim();
                    String pass = passwordET.getText().toString().trim();
                    register(email, pass);
                }
            }
        });


    }

    private void register(String email, String pass){
        mDialog.setMessage("Creating User please wait...");
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
//                    sendEmailVerification();
                    mDialog.dismiss();
//                    OnAuth(task.getResult().getUser());
                    mAuth.signOut();
                }else{
                    Toast.makeText(RegisterActivity.this,"Error on creating user",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
