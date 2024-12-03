package fpoly.hailxph49396.duan1_quanlybanhang.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.hailxph49396.duan1_quanlybanhang.Adapter.SanPhamAdapter;
import fpoly.hailxph49396.duan1_quanlybanhang.DAO.SanPhamDAo;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.SanPhamDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.R;

public class SanPhamFragment extends Fragment {

    private RecyclerView rvSanPham;
    private Button btnThem, btnSua, btnXoa, btnQuetMaVach;
    private SanPhamDAo sanPhamDao;
    private ArrayList<SanPhamDTO> sanPhamList;
    private SanPhamAdapter sanPhamAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_san_pham, container, false);

        // Ánh xạ view
        rvSanPham = view.findViewById(R.id.rvSanPham);
        btnThem = view.findViewById(R.id.btnThemSanPham);
        btnSua = view.findViewById(R.id.btnSuaSanPham);
        btnXoa = view.findViewById(R.id.btnXoaSanPham);
        btnQuetMaVach = view.findViewById(R.id.btnQuetMaVach);

        // Khởi tạo DAO và dữ liệu
        sanPhamDao = new SanPhamDAo(getContext());
        sanPhamList = sanPhamDao.getAllProducts();

        // Thiết lập RecyclerView
        sanPhamAdapter = new SanPhamAdapter(getContext(), sanPhamList);
        rvSanPham.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSanPham.setAdapter(sanPhamAdapter);

        // Sự kiện nút Thêm
        btnThem.setOnClickListener(v -> {
            // Mở dialog thêm sản phẩm
            Toast.makeText(getContext(), "Chức năng Thêm", Toast.LENGTH_SHORT).show();
        });

        // Sự kiện nút Sửa
        btnSua.setOnClickListener(v -> {
            // Lấy sản phẩm đã chọn để sửa
            Toast.makeText(getContext(), "Chức năng Sửa", Toast.LENGTH_SHORT).show();
        });

        // Sự kiện nút Xóa
        btnXoa.setOnClickListener(v -> {
            // Xóa sản phẩm đã chọn
            Toast.makeText(getContext(), "Chức năng Xóa", Toast.LENGTH_SHORT).show();
        });

        // Sự kiện nút Quét mã vạch
        btnQuetMaVach.setOnClickListener(v -> {
            // Sử dụng Barcode scanner API hoặc thư viện Zxing
            Toast.makeText(getContext(), "Chức năng Quét mã vạch", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
