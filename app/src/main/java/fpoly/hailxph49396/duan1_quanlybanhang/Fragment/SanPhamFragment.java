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

        sanPhamAdapter = new SanPhamAdapter(getContext(), sanPhamList);
        rvSanPham.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSanPham.setAdapter(sanPhamAdapter);
    }
}
