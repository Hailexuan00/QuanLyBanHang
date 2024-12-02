package fpoly.hailxph49396.duan1_quanlybanhang.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import fpoly.hailxph49396.duan1_quanlybanhang.DTO.NhanVienDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.Database.DbHelper;
import fpoly.hailxph49396.duan1_quanlybanhang.R;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.ViewHolder> {
    private List<NhanVienDTO> NhanVienList;
    private Context context;
    private DbHelper dbHelper;

    public NhanVienAdapter(List<NhanVienDTO> NhanVienList, Context context, DbHelper dbHelper) {
        this.NhanVienList = NhanVienList;
        this.context = context;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nhanvien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NhanVienDTO NhanVien = NhanVienList.get(position);  NhanVienList.get(position);
        holder.tvName.setText(NhanVien.getName());
        holder.tvPhone.setText(NhanVien.getPhone());

        holder.btnEdit.setOnClickListener(v -> {
            // Handle edit logic (similar to adding dialog)
        });

        holder.btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Xóa nhân viên")
                    .setMessage("Bạn có chắc chắn muốn xóa nhân viên này?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        dbHelper.getWritableDatabase().delete(DbHelper.TABLE_TAI_KHOAN,
                                "ma_nv = ?", new String[]{String.valueOf(NhanVien.getId())});
                        NhanVienList.remove(position);
                        notifyItemRemoved(position);
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return NhanVienList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone;
        ImageButton btnEdit, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvTenNhanVien);
            tvPhone = itemView.findViewById(R.id.tvSoDienThoai);
            btnEdit = itemView.findViewById(R.id.btnEditNhanVien);
            btnDelete = itemView.findViewById(R.id.btnDeleteNhanVien);
        }
    }
}

