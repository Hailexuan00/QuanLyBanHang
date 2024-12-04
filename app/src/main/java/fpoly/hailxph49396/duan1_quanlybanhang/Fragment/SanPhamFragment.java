package fpoly.hailxph49396.duan1_quanlybanhang.Fragment;

import android.app.AlertDialog;
import android.content.Context;
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
import androidx.recyclerview.widget.GridLayoutManager;
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
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvSanPham = view.findViewById(R.id.rvSanPham);
        btnThem = view.findViewById(R.id.btnThemSanPham);
        btnQuetMaVach = view.findViewById(R.id.btnQuetMaVach);

        sanPhamDao = new SanPhamDAo(getContext());
        sanPhamList = sanPhamDao.getAllProducts();

        sanPhamAdapter = new SanPhamAdapter(getContext(), sanPhamList, new SanPhamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showProductDetailsDialog(sanPhamList.get(position));
            }
        });
        rvSanPham.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvSanPham.setAdapter(sanPhamAdapter);
    }

    private void showProductDetailsDialog(SanPhamDTO sanPham) {
        // Tạo một View từ layout dialog_san_pham.xml
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View dialogView = inflater.inflate(R.layout.dialog_san_pham, null);

        // Khởi tạo các TextView từ dialog XML
        TextView tvTenSanPham = dialogView.findViewById(R.id.tvTenSanPham);
        TextView tvGiaBan = dialogView.findViewById(R.id.tvGiaBan);
        TextView tvTonKho = dialogView.findViewById(R.id.tvTonKho);
        TextView tvMaVach = dialogView.findViewById(R.id.tvMaVach);
        TextView tvMoTa = dialogView.findViewById(R.id.tvMoTa);
        Button btnClose = dialogView.findViewById(R.id.btnClose);

        // Điền dữ liệu vào các TextView
        tvTenSanPham.setText(sanPham.getTenSanPham());
        tvGiaBan.setText("Giá bán: " + sanPham.getGiaBan());
        tvTonKho.setText("Tồn kho: " + sanPham.getTonKho());
        tvMaVach.setText("Mã vạch: " + sanPham.getMaVach());
        tvMoTa.setText("Mô tả: " + sanPham.getMoTa());

        // Tạo AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_shape);

        // Hiển thị dialog
        dialog.show();

        // Đóng dialog khi click vào nút đóng
        btnClose.setOnClickListener(v -> dialog.dismiss());
    }
}