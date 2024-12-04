package fpoly.hailxph49396.duan1_quanlybanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.hailxph49396.duan1_quanlybanhang.DTO.SanPhamDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.R;
import fpoly.hailxph49396.duan1_quanlybanhang.ShowMoney.ShowMoney;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<SanPhamDTO> sanPhamList;
    ShowMoney showMoney;

    public SanPhamAdapter(Context context, ArrayList<SanPhamDTO> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_san_pham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        showMoney = new ShowMoney();
        SanPhamDTO sanPham = sanPhamList.get(position);
        holder.tvTenSanPham.setText(sanPham.getTenSanPham());
        holder.tvGiaBan.setText(showMoney.formatCurrency(sanPham.getGiaBan()) + " VNĐ");
        holder.tvTonKho.setText("x" + sanPham.getTonKho());
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSanPham, tvGiaBan, tvTonKho;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSanPham = itemView.findViewById(R.id.tvTenSanPham);
            tvGiaBan = itemView.findViewById(R.id.tvGiaBan);
            tvTonKho = itemView.findViewById(R.id.tvTonKho);
        }
    }
}
