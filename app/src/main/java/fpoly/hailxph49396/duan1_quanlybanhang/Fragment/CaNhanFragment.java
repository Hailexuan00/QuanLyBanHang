package fpoly.hailxph49396.duan1_quanlybanhang.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fpoly.hailxph49396.duan1_quanlybanhang.R;

public class CaNhanFragment extends Fragment {

    private TextView tvUserName, tvEmail;
    private ImageView imgAvatar;
    private Button btnEditInfo, btnLogout;

    public CaNhanFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ca_nhan, container, false);

        // Initialize views
        tvUserName = view.findViewById(R.id.tvUserName);
        tvEmail = view.findViewById(R.id.tvEmail);
        imgAvatar = view.findViewById(R.id.imgAvatar);
        btnEditInfo = view.findViewById(R.id.btnEditInfo);
        btnLogout = view.findViewById(R.id.btnLogout);

        // Set default user info
        tvUserName.setText("Đặng Lê Hải Nam");
        tvEmail.setText("danglehainam@gmail.com");

        // Button "Chỉnh sửa thông tin"
        btnEditInfo.setOnClickListener(v -> showEditInfoDialog());

        // Button "Đăng xuất"
        btnLogout.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Đăng xuất thành công!", Toast.LENGTH_SHORT).show();

            // Navigate to LoginActivity
            // You can handle navigation here if needed
        });

        return view;
    }

    private void showEditInfoDialog() {
        // Create a custom dialog layout
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_ca_nhan, null);

        // Initialize dialog views
        EditText etDialogUserName = dialogView.findViewById(R.id.etDialogUserName);
        EditText etDialogEmail = dialogView.findViewById(R.id.etDialogEmail);
        Button btnDialogSave = dialogView.findViewById(R.id.btnDialogSave);
        Button btnDialogCancel = dialogView.findViewById(R.id.btnDialogCancel);

        // Pre-fill the dialog fields with current values
        etDialogUserName.setText(tvUserName.getText().toString());
        etDialogEmail.setText(tvEmail.getText().toString());

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        // Handle save button click
        btnDialogSave.setOnClickListener(v -> {
            String newUserName = etDialogUserName.getText().toString().trim();
            String newEmail = etDialogEmail.getText().toString().trim();

            if (!newUserName.isEmpty() && !newEmail.isEmpty()) {
                // Update the TextViews with new data
                tvUserName.setText(newUserName);
                tvEmail.setText(newEmail);

                Toast.makeText(getContext(), "Thông tin đã được cập nhật!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle cancel button click
        btnDialogCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
