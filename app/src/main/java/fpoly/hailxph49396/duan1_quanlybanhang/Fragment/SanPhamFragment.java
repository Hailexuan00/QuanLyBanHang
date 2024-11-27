package fpoly.hailxph49396.duan1_quanlybanhang.Fragment;

import android.os.Bundle;
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

import fpoly.hailxph49396.duan1_quanlybanhang.Adapter.SanPhamAdapter;
import fpoly.hailxph49396.duan1_quanlybanhang.R;

public class SanPhamFragment extends Fragment {

    private EditText searchEditText;
    private Button scanButton;
    private RecyclerView recyclerView;

    public SanPhamFragment() {
        // Required empty public constructor
    }

    public static SanPhamFragment newInstance(String param1, String param2) {
        SanPhamFragment fragment = new SanPhamFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout
        View view = inflater.inflate(R.layout.fragment_san_pham, container, false);

        // Find views by ID
        searchEditText = view.findViewById(R.id.searchEditText);
        scanButton = view.findViewById(R.id.scanButton);
        recyclerView = view.findViewById(R.id.recyclerView);

        // Setup RecyclerView
        setupRecyclerView();

        // Set button click listener
        scanButton.setOnClickListener(v -> onScanButtonClicked());

        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Assuming a custom adapter named "ProductAdapter"
        List<String> dummyData = new ArrayList<>();
        dummyData.add("Product 1");
        dummyData.add("Product 2");
        dummyData.add("Product 3");
        recyclerView.setAdapter(new SanPhamAdapter(dummyData));
    }

    private void onScanButtonClicked() {
        // Handle barcode scan logic here
        // Example: Show a toast (replace this with your actual logic)
        Toast.makeText(getContext(), "Scan button Clicked", Toast.LENGTH_SHORT).show();
    }
}