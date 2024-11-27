package fpoly.hailxph49396.duan1_quanlybanhang.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fpoly.hailxph49396.duan1_quanlybanhang.R;

public class CaNhan extends AppCompatActivity {

    private EditText etUserName, etEmail;
    private ImageView imgAvatar;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_nhan);

        // Initialize views
        etUserName = findViewById(R.id.etUserName);
        etEmail = findViewById(R.id.etEmail);
        imgAvatar = findViewById(R.id.imgAvatar);
        btnSave = findViewById(R.id.btnSave);

        // Set initial data (can come from a database or shared preferences)
        etUserName.setText("Tên Người Dùng");
        etEmail.setText("example@gmail.com");

        // Handle save button click
        btnSave.setOnClickListener(v -> {
            // Get updated values
            String updatedUserName = etUserName.getText().toString();
            String updatedEmail = etEmail.getText().toString();

            // Create an intent to send updated data back
            Intent resultIntent = new Intent();
            resultIntent.putExtra("updatedUserName", updatedUserName);
            resultIntent.putExtra("updatedEmail", updatedEmail);
            setResult(RESULT_OK, resultIntent);

            // Simulate saving updated info
            Toast.makeText(CaNhan.this, "Thông tin đã được lưu!", Toast.LENGTH_SHORT).show();

            // Close the activity and return to the previous screen
            finish();
        });
    }
}
