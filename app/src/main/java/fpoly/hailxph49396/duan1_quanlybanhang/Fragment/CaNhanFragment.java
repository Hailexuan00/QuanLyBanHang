package fpoly.hailxph49396.duan1_quanlybanhang.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fpoly.hailxph49396.duan1_quanlybanhang.LoginActivity;
import fpoly.hailxph49396.duan1_quanlybanhang.R;

public class CaNhanFragment extends Fragment {
    TextView tvUserName, tvEmail;
    Button btnEditInfo, btnLogout;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ca_nhan, container, false);

        tvUserName = view.findViewById(R.id.ed_user);
        tvEmail = view.findViewById(R.id.etEmail);
        btnEditInfo = view.findViewById(R.id.btnEditInfo);
        btnLogout = view.findViewById(R.id.btnLogout);

        // Gán dữ liệu mẫu
        tvUserName.setText("");
        tvEmail.setText("");

        // Sự kiện chỉnh sửa thông tin
        btnEditInfo.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CaNhan.class);
            startActivity(intent);
        });

        // Sự kiện đăng xuất
        btnLogout.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Đăng xuất thành công!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        return view;
    }
}
