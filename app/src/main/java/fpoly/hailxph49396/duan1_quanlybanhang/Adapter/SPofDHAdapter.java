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

import fpoly.hailxph49396.duan1_quanlybanhang.DAO.DonHangDAO;
import fpoly.hailxph49396.duan1_quanlybanhang.DAO.SanPhamDAo;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.SanPhamDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.R;

public class SPofDHAdapter extends RecyclerView.Adapter<SPofDHAdapter.SanPhamViewHolder>{
    private Context context;
    private ArrayList<SanPhamDTO> list;
    SanPhamDAo sanPhamDAo;

    public SPofDHAdapter(Context context, ArrayList<SanPhamDTO> list) {
        this.context = context;
        this.list = list;
        sanPhamDAo = new SanPhamDAo(context);
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_san_pham, parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        SanPhamDTO sanPham = list.get(position);

        holder.txtTenSP.setText(sanPham.getTenSanPham());
        holder.txtDonGiaSP.setText(String.format(sanPham.getGiaBan() + "VNĐ"));

        holder.nbpSoLuong.setMinValue(0);
        holder.nbpSoLuong.setMaxValue(sanPham.getTonKho());
        holder.nbpSoLuong.setValue(1);

        holder.txtGiaSP.setText(String.format(sanPham.getGiaBan() + "VNĐ"));
        holder.nbpSoLuong.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                holder.txtGiaSP.setText(sanPham.getGiaBan()*newVal + "VNĐ");
            }
        });

        holder.btnDelete.setOnClickListener(v -> {
            list.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Xóa", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SanPhamViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSP, txtDonGiaSP, txtGiaSP;
        NumberPicker nbpSoLuong;
        ImageButton btnDelete;

        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTenSP = itemView.findViewById(R.id.txtTenSP);
            txtDonGiaSP = itemView.findViewById(R.id.txtDonGiaSP);
            txtGiaSP = itemView.findViewById(R.id.txtGiaSP);
            nbpSoLuong = itemView.findViewById(R.id.nbpSoLuong);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
