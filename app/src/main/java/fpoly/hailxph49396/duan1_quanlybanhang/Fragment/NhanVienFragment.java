package fpoly.hailxph49396.duan1_quanlybanhang.Fragment;

import static java.security.AccessController.getContext;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//import fpoly.hailxph49396.duan1_quanlybanhang.Adapter.NhanVienAdapter;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.NhanVienDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.Database.DbHelper;
import fpoly.hailxph49396.duan1_quanlybanhang.R;

public class NhanVienFragment<db, View> extends Fragment {

//    private RecyclerView recyclerView;
//    private NhanVienAdapter NhanVienAdapter;
//    private List<NhanVienDTO> NhanVienList;
//
//    public NhanVienFragment() {
//        // Required empty public constructor
//    }
//
//    public static NhanVienFragment newInstance(String param1, String param2) {
//        NhanVienFragment fragment = new NhanVienFragment();
//        Bundle args = new Bundle();
//        args.putString("param1", param1);
//        args.putString("param2", param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout
//        View view = inflater.inflate(R.layout.fragment_nhan_vien, container, false);
//
//        // Find views by ID
//        recyclerView = view.findViewById(R.id.recyclerView);
//        Button addButton = view.findViewById(R.id.);
//
//        // Setup RecyclerView
//        setupRecyclerView();
//
//        // Add Button Click Listener
//        addButton.setOnClickListener(v -> showAddEmployeeDialog());
//
//        return view;
//    }
//
//    private void setupRecyclerView() {
//        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()))
//
//        // Fetch employee list from database
//        DbHelper dbHelper = new DbHelper(getContext());
//        SQLiteDatabase db = dbHelper.GetWritableDatabase();
//        NhanVienList = dbHelper.getAllEmployees(db);
//
//        // Setup adapter
//        NhanVienAdapter = new NhanVienAdapter(NhanVienList);
//        recyclerView.setAdapter(NhanVienAdapter);
//    }
//
//    private void showAddEmployeeDialog() {
//        // Create and show the dialog to add a new employee
//        Dialog dialog = new Dialog(recyclerView.getContext());
//        dialog.setContentView(R.layout.dialog_nhan_vien);
//
//        EditText nameEditText = dialog.findViewById(R.id.nameEditText);
//        EditText middleNameEditText = dialog.findViewById(R.id.middleNameEditText);
//        EditText genderEditText = dialog.findViewById(R.id.genderEditText);
//        EditText phoneEditText = dialog.findViewById(R.id.phoneEditText);
//        EditText emailEditText = dialog.findViewById(R.id.emailEditText);
//        EditText addressEditText = dialog.findViewById(R.id.addressEditText);
//        Button saveButton = dialog.findViewById(R.id.saveButton);
//
//        saveButton.setOnClickListener(v -> {
//            // Get input values
//            String name = nameEditText.getText().toString();
//            String middleName = middleNameEditText.getText().toString();
//            String gender = genderEditText.getText().toString();
//            String phone = phoneEditText.getText().toString();
//            String email = emailEditText.getText().toString();
//            String address = addressEditText.getText().toString();
//
//            // Insert employee into database
//            DbHelper dbHelper = new DbHelper(getContext());
//            SQLiteDatabase db = dbHelper.getWritableDatabase();
//            NhanVienDTO newEmployee = new NhanVienDTO(0, name, middleName, gender, phone, email, address);
//            dbHelper.addNhanVien(db, newEmployee);
//
//            // Update RecyclerView
//            NhanVienList.add(newEmployee);
//            NhanVienAdapter.notifyDataSetChanged();
//
//            dialog.dismiss();
//        });
//
//        dialog.show();
//    }
}