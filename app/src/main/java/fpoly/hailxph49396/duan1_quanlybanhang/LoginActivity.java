package fpoly.hailxph49396.duan1_quanlybanhang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import fpoly.hailxph49396.duan1_quanlybanhang.DAO.TaiKhoanDao;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.TaiKhoanDTO;

public class LoginActivity extends AppCompatActivity {
    private Button btnDangNhap, btnHuy;
    private TextInputLayout layoutUser, layoutPass;
    private TextInputEditText edUser, edPass;
    private CheckBox cbGhiNho;
    private TaiKhoanDao taiKhoanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        loadSavedCredentials();

        btnHuy.setOnClickListener(v -> clearInputs());

        btnDangNhap.setOnClickListener(v -> checkLogin());
    }

    private void initViews() {
        taiKhoanDao = new TaiKhoanDao(this);

        layoutUser = findViewById(R.id.txt_user);
        edUser = findViewById(R.id.ed_user);
        layoutPass = findViewById(R.id.txt_pass);
        edPass = findViewById(R.id.ed_pass);
        btnDangNhap = findViewById(R.id.btn_dangnhap);
        btnHuy = findViewById(R.id.btn_huy);
        cbGhiNho = findViewById(R.id.cb_ghinho);
    }

    private void loadSavedCredentials() {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");
        boolean rem = pref.getBoolean("REMEMBER", false);

        edUser.setText(user);
        edPass.setText(pass);
        cbGhiNho.setChecked(rem);
    }

    private void clearInputs() {
        edUser.setText("");
        edPass.setText("");
        layoutUser.setError(null);
        layoutPass.setError(null);
    }

    private void rememberUser(String user, String pass, boolean isRemember) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        if (!isRemember) {
            editor.clear();
        } else {
            editor.putString("USERNAME", user);
            editor.putString("PASSWORD", pass);
            editor.putBoolean("REMEMBER", true);
        }
        editor.apply();
    }

    private void checkLogin() {
        String strUser = edUser.getText().toString().trim();
        String strPass = edPass.getText().toString().trim();

        boolean isValid = true;

        if (strUser.isEmpty()) {
            layoutUser.setError("Tên đăng nhập không được để trống");
            isValid = false;
        } else {
            layoutUser.setError(null);
        }

        if (strPass.isEmpty()) {
            layoutPass.setError("Mật khẩu không được để trống");
            isValid = false;
        } else {
            layoutPass.setError(null);
        }

        if (isValid) {
            if (TaiKhoanDao.checkLogin(strUser, strPass)) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser, strPass, cbGhiNho.isChecked());

                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                intent.putExtra("user", strUser);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
