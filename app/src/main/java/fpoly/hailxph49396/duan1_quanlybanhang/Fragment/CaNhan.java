package fpoly.hailxph49396.duan1_quanlybanhang.Fragment;



import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fpoly.hailxph49396.duan1_quanlybanhang.R;

public class CaNhan extends AppCompatActivity {

    private EditText etUserName, etEmail;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_nhan);

        // Initialize views
        etUserName = findViewById(R.id.etUserName);
        etEmail = findViewById(R.id.etEmail);
        btnSave = findViewById(R.id.btnSave);

        // Set default values
        etUserName.setText("Tên Người Dùng");
        etEmail.setText("example@gmail.com");

        // Handle save button click
        btnSave.setOnClickListener(v -> {
            String newUserName = etUserName.getText().toString().trim();
            String newEmail = etEmail.getText().toString().trim();

            // Simulate saving data
            Toast.makeText(this, "Thông tin đã được lưu!", Toast.LENGTH_SHORT).show();

            // Close activity
            finish();
        });
    }
}


