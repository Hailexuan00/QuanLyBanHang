package fpoly.hailxph49396.duan1_quanlybanhang.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fpoly.hailxph49396.duan1_quanlybanhang.Adapter.NhanVienAdapter;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.NhanVienDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.Database.DbHelper;
import fpoly.hailxph49396.duan1_quanlybanhang.R;

public class NhanVienFragment extends Fragment {
    private RecyclerView recyclerView;
    private NhanVienAdapter nhanVienAdapter;
    private List<NhanVienDTO> NhanVienList;
    private DbHelper dbHelper;
    private Button btnAddEmployee;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nhan_vien, container, false);

        // Ánh xạ view
        recyclerView = view.findViewById(R.id.recyclerViewNhanVien);
        btnAddEmployee = view.findViewById(R.id.btnAddNhanVien);

        // Khởi tạo database và danh sách nhân viên
        dbHelper = new DbHelper(getContext());
        NhanVienList = NhanVienDTO.getAllEmployees(dbHelper.getReadableDatabase());
        if (NhanVienList == null) {
            NhanVienList = new ArrayList<>();
        }

        // Cài đặt adapter và layout cho RecyclerView
        nhanVienAdapter = new NhanVienAdapter(NhanVienList, getContext(), dbHelper);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(nhanVienAdapter);

        // Xử lý sự kiện nút thêm nhân viên
        btnAddEmployee.setOnClickListener(v -> showAddEmployeeDialog());

        return view;
    }

    private void showAddEmployeeDialog() {
        // Tạo dialog thêm nhân viên
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_nhanvien);
        dialog.setCancelable(true);

        // Ánh xạ view trong dialog
        EditText edtName = dialog.findViewById(R.id.edtTenNhanVien);
        EditText edtMiddleName = dialog.findViewById(R.id.edtHoVaTenDem);
        EditText edtGender = dialog.findViewById(R.id.edtGioiTinh);
        EditText edtPhone = dialog.findViewById(R.id.edtSoDienThoai);
        EditText edtEmail = dialog.findViewById(R.id.edtEmail);
        EditText edtAddress = dialog.findViewById(R.id.edtDiaChi);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(v -> {
            // Lấy dữ liệu từ người dùng
            String name = edtName.getText().toString().trim();
            String middleName = edtMiddleName.getText().toString().trim();
            String gender = edtGender.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String address = edtAddress.getText().toString().trim();

            // Kiểm tra dữ liệu hợp lệ
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
                Toast.makeText(getContext(), "Vui lòng nhập tên và số điện thoại", Toast.LENGTH_SHORT).show();
            } else {
                // Tạo đối tượng nhân viên mới
                if (NhanVienList == null) {
                    NhanVienList = new ArrayList<>();
                }

                NhanVienDTO newNhanVien = new NhanVienDTO(0, name, middleName, gender, phone, email, address);
                // Thêm nhân viên vào cơ sở dữ liệu
                NhanVienDTO.addEmployee(dbHelper.getWritableDatabase(), newNhanVien);
                // Cập nhật danh sách và thông báo cho adapter
                NhanVienList.add(newNhanVien);
                nhanVienAdapter.notifyItemInserted(NhanVienList.size() - 1);

                Toast.makeText(getContext(), "Thêm nhân viên thành công!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}
