//package fpoly.hailxph49396.duan1_quanlybanhang.Adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//import fpoly.hailxph49396.duan1_quanlybanhang.DTO.NhanVienDTO;
//import fpoly.hailxph49396.duan1_quanlybanhang.R;
//
//public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.EmployeeViewHolder> {
//
//    private List<NhanVienDTO> NhanVienList;
//
//    public NhanVienAdapter(List<NhanVienDTO> NhanVienList) {
//        this.NhanVienList = NhanVienList;
//    }
//
//    @NonNull
//    @Override
//    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
//        return new EmployeeViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
//        NhanVienDTO nhanVien = NhanVienList.get(position);
//        holder.nameTextView.(nhanVien.getName());
//        holder.phoneTextView.(nhanVien.getPhone());
//    }
//
//    @Override
//    public int getItemCount() {
//        return NhanVienList.size();
//    }
//
//    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
//        TextView nameTextView, phoneTextView;
//
//        public EmployeeViewHolder(View itemView) {
//            super(itemView);
//            nameTextView = itemView.findViewById(R.id.nameTextView);
//            phoneTextView = itemView.findViewById(R.id.phoneTextView);
//        }
//    }
//}
//
