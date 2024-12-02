package fpoly.hailxph49396.duan1_quanlybanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.hailxph49396.duan1_quanlybanhang.DTO.SanPhamDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.R;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<SanPhamDTO> sanPhamList;

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
        SanPhamDTO sanPham = sanPhamList.get(position);
        holder.tvTenSanPham.setText(sanPham.getTenSanPham());
        holder.tvGiaBan.setText("Giá: " + sanPham.getGiaBan() + " VNĐ");

        holder.btnSua.setOnClickListener(v -> {
            // Xử lý sự kiện sửa
            Toast.makeText(context, "Sửa sản phẩm: " + sanPham.getTenSanPham(), Toast.LENGTH_SHORT).show();
            // Mở màn hình sửa hoặc dialog
        });

        holder.btnXoa.setOnClickListener(v -> {
            // Xử lý sự kiện xóa
            sanPhamList.remove(position);  // Xóa sản phẩm khỏi danh sách
            notifyItemRemoved(position);  // Cập nhật RecyclerView
            Toast.makeText(context, "Xóa sản phẩm: " + sanPham.getTenSanPham(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSanPham, tvGiaBan;
        Button btnSua, btnXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSanPham = itemView.findViewById(R.id.tvTenSanPham);
            tvGiaBan = itemView.findViewById(R.id.tvGiaBan);
            btnSua = itemView.findViewById(R.id.btnSuaSanPham);
            btnXoa = itemView.findViewById(R.id.btnXoaSanPham);
        }
    }
}
