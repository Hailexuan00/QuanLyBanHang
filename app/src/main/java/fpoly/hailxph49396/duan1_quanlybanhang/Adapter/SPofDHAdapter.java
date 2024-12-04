package fpoly.hailxph49396.duan1_quanlybanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import fpoly.hailxph49396.duan1_quanlybanhang.DAO.ChiTietDonHangDAO;
import fpoly.hailxph49396.duan1_quanlybanhang.DAO.DonHangDAO;
import fpoly.hailxph49396.duan1_quanlybanhang.DAO.SanPhamDAo;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.ChiTietDonHangDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.SanPhamDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.R;
import fpoly.hailxph49396.duan1_quanlybanhang.ShowMoney.ShowMoney;

public class SPofDHAdapter extends RecyclerView.Adapter<SPofDHAdapter.SanPhamViewHolder>{
    private Context context;
    private ArrayList<ChiTietDonHangDTO> list;
    SanPhamDAo sanPhamDAo;
    ChiTietDonHangDAO chiTietDonHangDAO;

    private final OnItemActionListener listener;
    private int total = 0;
    private HashMap<Integer, Integer> itemValues = new HashMap<>();
    ShowMoney showMoney;


    public interface OnItemActionListener {
        void onAction(int position, String actionType);
    }

    public SPofDHAdapter(Context context, ArrayList<ChiTietDonHangDTO> list, OnItemActionListener listener) {
        this.context = context;
        this.list = list;
        sanPhamDAo = new SanPhamDAo(context);
        chiTietDonHangDAO = new ChiTietDonHangDAO(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sp_dh, parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        ChiTietDonHangDTO chiTietDonHangDTO = list.get(position);
        SanPhamDTO sanPhamDTO = sanPhamDAo.findProductById(chiTietDonHangDTO.getIdSanPham());
        showMoney = new ShowMoney();
        holder.txtTenSP.setText(sanPhamDTO.getTenSanPham() + "");
        holder.txtDonGiaSP.setText(showMoney.formatCurrency(sanPhamDTO.getGiaBan()) + "VNĐ");

        holder.txtSoLuong.setText("x" + chiTietDonHangDTO.getSoLuong());
        holder.txtGiaSP.setText( showMoney.formatCurrency(sanPhamDTO.getGiaBan() * chiTietDonHangDTO.getSoLuong())  + "VNĐ");
        holder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAction(position, "cong");
                }
            }
        });
        holder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAction(position, "tru");
                }
            }
        });


    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class SanPhamViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSP, txtDonGiaSP, txtGiaSP, txtSoLuong;
//        NumberPicker nbpSoLuong;
//        ImageButton btnDelete;
        ImageButton btnCong, btnTru;

        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSP = itemView.findViewById(R.id.txtTenSP);
            txtDonGiaSP = itemView.findViewById(R.id.txtDonGiaSP);
            txtGiaSP = itemView.findViewById(R.id.txtGiaSP);
//            nbpSoLuong = itemView.findViewById(R.id.nbpSoLuong);
//            btnDelete = itemView.findViewById(R.id.btnDelete);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            btnCong = itemView.findViewById(R.id.btnCong);
            btnTru = itemView.findViewById(R.id.btnTru);
        }
    }

}
