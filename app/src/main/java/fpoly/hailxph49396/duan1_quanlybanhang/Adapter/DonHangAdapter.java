package fpoly.hailxph49396.duan1_quanlybanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fpoly.hailxph49396.duan1_quanlybanhang.DAO.DonHangDAO;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.DonHangDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.R;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.DonHangViewHolders> {

    Context context;
    ArrayList<DonHangDTO> list;
    DonHangDTO donHangDTO;
    DonHangDAO donHangDAO;
    OnItemClickListener onItemClickListner;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public DonHangAdapter(Context context, ArrayList<DonHangDTO> list, OnItemClickListener onItemClickListner) {
        this.context = context;
        this.list = list;
        this.onItemClickListner = onItemClickListner;
        donHangDAO = new DonHangDAO(context);
    }
    @NonNull
    @Override
    public DonHangViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_don_hang, parent, false);
        return new DonHangViewHolders(view, onItemClickListner);
    }
    @Override
    public void onBindViewHolder(@NonNull DonHangViewHolders holder, int position) {
        donHangDTO = list.get(position);
        if (donHangDTO.getNgay() != null){
            holder.txtNgay.setText(sdf.format(donHangDTO.getNgay()));
        }else {
            holder.txtNgay.setText("Lỗi ngày");
        }
        holder.txtGio.setText(donHangDTO.getGio());
        holder.txtMaDH.setText(String.valueOf(donHangDTO.getMaDonHang()));
        holder.txtUser.setText(donHangDTO.getUsername());
        holder.txtTongTien.setText(donHangDTO.getThanhTien()+"");
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DonHangViewHolders extends RecyclerView.ViewHolder {

        TextView txtNgay;
        TextView txtGio;
        TextView txtMaDH;
        TextView txtUser;
        TextView txtTongTien;

        public DonHangViewHolders(@NonNull View itemView, OnItemClickListener onItemClickListner) {
            super(itemView);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtGio = itemView.findViewById(R.id.txtGio);
            txtMaDH = itemView.findViewById(R.id.txtMaDH);
            txtUser = itemView.findViewById(R.id.txtUser);
            txtTongTien = itemView.findViewById(R.id.txtTongTien);
            itemView.setOnClickListener(v -> {
                if (onItemClickListner != null) {
                    onItemClickListner.onItemClick(getAdapterPosition());
                }
            });
        }

    }


}
