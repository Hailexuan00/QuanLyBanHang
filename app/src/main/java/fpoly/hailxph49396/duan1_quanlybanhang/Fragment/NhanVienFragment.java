package fpoly.hailxph49396.duan1_quanlybanhang.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fpoly.hailxph49396.duan1_quanlybanhang.R;

public class NhanVienFragment extends Fragment {

    private TextView tvSelectedEmployee;
    private EditText etEmployeeName;
    private Button btnAdd, btnEdit, btnDelete;
    private ListView lvEmployees;

    private ArrayList<String> employeeList;
    private ArrayAdapter<String> adapter;
    private int selectedPosition = -1;

    public NhanVienFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nhan_vien, container, false);
        initializeViews(view);
        setupEmployeeList();
        setupEventListeners();
        return view;
    }

    private void initializeViews(View view) {
        tvSelectedEmployee = view.findViewById(R.id.tvSelectedEmployee);
        etEmployeeName = view.findViewById(R.id.etEmployeeName);
        btnAdd = view.findViewById(R.id.btnAddEmployee);
        btnEdit = view.findViewById(R.id.btnEditEmployee);
        btnDelete = view.findViewById(R.id.btnDeleteEmployee);
        lvEmployees = view.findViewById(R.id.lvEmployees);
    }

    private void setupEmployeeList() {
        employeeList = new ArrayList<>();
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, employeeList);
        lvEmployees.setAdapter(adapter);
    }

    private void setupEventListeners() {
        btnAdd.setOnClickListener(v -> addEmployee());
        btnEdit.setOnClickListener(v -> editEmployee());
        btnDelete.setOnClickListener(v -> deleteEmployee());
        lvEmployees.setOnItemClickListener((parent, view, position, id) -> selectEmployee(position));
    }

    private void addEmployee() {
        String name = etEmployeeName.getText().toString().trim();
        if (name.isEmpty()) {
            showToast("Vui lòng nhập tên nhân viên");
        } else {
            employeeList.add(name);
            adapter.notifyDataSetChanged();
            clearInput();
            showToast("Đã thêm nhân viên");
        }
    }

    private void editEmployee() {
        String name = etEmployeeName.getText().toString().trim();
        if (selectedPosition == -1) {
            showToast("Vui lòng chọn nhân viên để sửa");
        } else if (name.isEmpty()) {
            showToast("Vui lòng nhập tên mới");
        } else {
            employeeList.set(selectedPosition, name);
            adapter.notifyDataSetChanged();
            clearSelection();
            showToast("Đã sửa nhân viên");
        }
    }

    private void deleteEmployee() {
        if (selectedPosition == -1) {
            showToast("Vui lòng chọn nhân viên để xóa");
        } else {
            employeeList.remove(selectedPosition);
            adapter.notifyDataSetChanged();
            clearSelection();
            showToast("Đã xóa nhân viên");
        }
    }

    private void selectEmployee(int position) {
        selectedPosition = position;
        String selectedName = employeeList.get(position);
        tvSelectedEmployee.setText(selectedName);
        etEmployeeName.setText(selectedName);
    }

    private void clearInput() {
        etEmployeeName.setText("");
    }

    private void clearSelection() {
        selectedPosition = -1;
        tvSelectedEmployee.setText("Nhân Viên");
        clearInput();
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
