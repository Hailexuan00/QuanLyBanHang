package fpoly.hailxph49396.duan1_quanlybanhang.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fpoly.hailxph49396.duan1_quanlybanhang.Adapter.NhanVienAdapter;
import fpoly.hailxph49396.duan1_quanlybanhang.DAO.nhanvienDao;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.NhanVienDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.Database.DbHelper;
import fpoly.hailxph49396.duan1_quanlybanhang.R;

public class NhanVienFragment extends Fragment {
    private RecyclerView recyclerView;
    private NhanVienAdapter nhanVienAdapter;
    private List<NhanVienDTO> nhanVienList;
    private nhanvienDao nhanVienDAO;
    private ImageView btnAddEmployee;

    private EditText edtName, edtMiddleName, edtGender, edtPhone, edtEmail, edtAddress, edtUsername, edtPassword;
    private Button btnAdd, btnCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nhan_vien, container, false);

        // Khởi tạo view
        recyclerView = view.findViewById(R.id.recyclerViewNhanVien);
        btnAddEmployee = view.findViewById(R.id.fabAddEmployee);

        // Khởi tạo DAO và danh sách nhân viên
        DbHelper dbHelper = new DbHelper(getContext()); // Correctly initialize DbHelper with context
        nhanVienDAO = new nhanvienDao(getContext()); // Pass context to DAO
        nhanVienList = nhanVienDAO.getAllEmployees(); // Fetch employees from DAO

        if (nhanVienList == null) {
            nhanVienList = new ArrayList<>();
        }


        // Cài đặt Adapter
        nhanVienAdapter = new NhanVienAdapter(nhanVienList, getContext(), nhanVienDAO, this::showEditEmployeeDialog);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(nhanVienAdapter);

        // Thêm nhân viên
        btnAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddEmployeeDialog();
            }
        });

        return view;
    }


    private void showAddEmployeeDialog() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_nhanvien);
        dialog.setCancelable(true);

         edtUsername = dialog.findViewById(R.id.edtUserName);
         edtPassword = dialog.findViewById(R.id.edtPassword);
         edtName = dialog.findViewById(R.id.edtName);
         edtMiddleName = dialog.findViewById(R.id.edtMiddleName);
         edtGender = dialog.findViewById(R.id.edtGender);
         edtPhone = dialog.findViewById(R.id.edtPhone);
         edtEmail = dialog.findViewById(R.id.edtEmail);
         edtAddress = dialog.findViewById(R.id.edtAddress);
         btnAdd = dialog.findViewById(R.id.btnAdd);
         btnCancel = dialog.findViewById(R.id.btnCancel);


    }

    private void showEditEmployeeDialog(@Nullable NhanVienDTO nhanVien) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_nhanvien);
        dialog.setCancelable(true);

        edtUsername = dialog.findViewById(R.id.edtUserName);
        edtPassword = dialog.findViewById(R.id.edtPassword);
         edtName = dialog.findViewById(R.id.edtName);
         edtMiddleName = dialog.findViewById(R.id.edtMiddleName);
         edtGender = dialog.findViewById(R.id.edtGender);
         edtPhone = dialog.findViewById(R.id.edtPhone);
         edtEmail = dialog.findViewById(R.id.edtEmail);
         edtAddress = dialog.findViewById(R.id.edtAddress);
         btnAdd = dialog.findViewById(R.id.btnAdd);
         btnCancel = dialog.findViewById(R.id.btnCancel);

        // If editing an existing employee, pre-fill the fields
        if (nhanVien != null) {
            edtUsername.setText(nhanVien.getUsername());
            edtPassword.setText(nhanVien.getPassword());
            edtName.setText(nhanVien.getName());
            edtMiddleName.setText(nhanVien.getMiddleName());
            edtGender.setText(nhanVien.getGender());
            edtPhone.setText(nhanVien.getPhone());
            edtEmail.setText(nhanVien.getEmail());
            edtAddress.setText(nhanVien.getAddress());
            btnAdd.setText("Sửa");
        }

        // Handle the 'Add' or 'Edit' button
        btnAdd.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();

            // Validate input
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone)) {
                NhanVienDTO newNhanVien = new NhanVienDTO(
                        nhanVien == null ? 0 : nhanVien.getId(),
                        name,
                        edtMiddleName.getText().toString().trim(),
                        edtGender.getText().toString().trim(),
                        phone,
                        edtEmail.getText().toString().trim(),
                        edtAddress.getText().toString().trim()
                );

                // Add or update the employee
                if (nhanVien == null) {
                    nhanVienDAO.addEmployee(newNhanVien);
                    nhanVienList.add(newNhanVien);
                    nhanVienAdapter.notifyItemInserted(nhanVienList.size() - 1);
                    Toast.makeText(getContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            } else {
                Toast.makeText(getContext(), "Vui lòng nhập tên và số điện thoại!", Toast.LENGTH_SHORT).show();
            }
        });

        // Cancel button closes the dialog
        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
