package fpoly.hailxph49396.duan1_quanlybanhang.Fragment;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fpoly.hailxph49396.duan1_quanlybanhang.Adapter.DonHangAdapter;
import fpoly.hailxph49396.duan1_quanlybanhang.Adapter.SPofDHAdapter;
import fpoly.hailxph49396.duan1_quanlybanhang.BanHang;
import fpoly.hailxph49396.duan1_quanlybanhang.DAO.ChiTietDonHangDAO;
import fpoly.hailxph49396.duan1_quanlybanhang.DAO.DonHangDAO;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.ChiTietDonHangDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.DonHangDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.Database.DbHelper;
import fpoly.hailxph49396.duan1_quanlybanhang.R;


public class BanHangFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView rcvDonHang;
    DonHangDAO donHangDAO;
    ArrayList<DonHangDTO> listDH = new ArrayList<>();
    DonHangAdapter donHangAdapter;
    SPofDHAdapter spofDHAdapter;
    ChiTietDonHangDAO chiTietDonHangDAO;
    ChiTietDonHangDTO chiTietDonHangDTO;
    ArrayList<ChiTietDonHangDTO> listCTDH = new ArrayList<>();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private String mParam1;
    private String mParam2;

    public BanHangFragment() {
    }

    public static BanHangFragment newInstance(String param1, String param2) {
        BanHangFragment fragment = new BanHangFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ban_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton fabAddDonHang = view.findViewById(R.id.fabAddDH);
        rcvDonHang = view.findViewById(R.id.rcvDH);
        donHangDAO = new DonHangDAO(getContext());
        listDH = donHangDAO.getListDonHang();
        chiTietDonHangDAO = new ChiTietDonHangDAO(getContext());
        donHangAdapter = new DonHangAdapter(getContext(), listDH, new DonHangAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DonHangDTO itemDonHang = listDH.get(position);
                showCustomDialog(itemDonHang);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvDonHang.setLayoutManager(layoutManager);
        rcvDonHang.setAdapter(donHangAdapter);
        Toast.makeText(getContext(), "" + listDH.size(), Toast.LENGTH_SHORT).show();
        donHangAdapter.notifyDataSetChanged();

        fabAddDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BanHang.class);
                DonHangDTO donHangDTO = new DonHangDTO();
                try {
                    donHangDTO.setNgay(sdf.parse("12/12/2012"));
                }catch (Exception e){

                }
                long check = donHangDAO.addDonHang(donHangDTO);
                if (check != -1){
                    Toast.makeText(getContext(), "Đơn hàng mới", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Lỗi tạo đơn hàng", Toast.LENGTH_SHORT).show();
                }
                startActivity(intent);
            }
        });

    }
    private void showCustomDialog(DonHangDTO donHangDTO) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_don_hang, null);

        // Tìm các thành phần trong dialog
        TextView txtMaDH = dialogView.findViewById(R.id.txt_maDH);
        TextView txtNgayGio = dialogView.findViewById(R.id.txt_ngay_gio);
        TextView txtUser = dialogView.findViewById(R.id.txt_user);
        TextView txtTongTien = dialogView.findViewById(R.id.txtTongTien);
        RecyclerView rcvSP = dialogView.findViewById(R.id.rcvSP);
        listCTDH = chiTietDonHangDAO.getCTDHByIdDonHang(donHangDTO.getMaDonHang());
        spofDHAdapter = new SPofDHAdapter(getContext(), listCTDH, new SPofDHAdapter.OnItemActionListener() {
            @Override
            public void onAction(int position, String actionType) {

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvSP.setLayoutManager(layoutManager);
        rcvSP.setAdapter(spofDHAdapter);

        txtMaDH.setText("Mã đơn hàng: " + donHangDTO.getMaDonHang());
        try {
            if (donHangDTO.getNgay() != null){
                txtNgayGio.setText("Ngày: " + sdf.format(donHangDTO.getNgay()) + " - Giờ: " + donHangDTO.getGio());
            }else {
                txtNgayGio.setText("Ngày: " + "" + " - Giờ: " + "");
            }
        }catch (Exception e){
            Log.e("Error", e.getMessage());
        }

        txtUser.setText("Người dùng: " + donHangDTO.getUsername());
        txtTongTien.setText("Tổng tiền: " + donHangDTO.getThanhTien());

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(dialogView)
                .setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();

        // Thiết lập sự kiện cho các nút trong Dialog
        Button btnTraHang = dialogView.findViewById(R.id.btnTraHang);
        Button btnHuy = dialogView.findViewById(R.id.btn_huy);

        btnTraHang.setOnClickListener(v -> {

            dialog.dismiss();
        });

        btnHuy.setOnClickListener(v -> {

            dialog.dismiss();
        });
    }
}