package fpoly.hailxph49396.duan1_quanlybanhang.Adapter;

<<<<<<< HEAD
public class DonHangAdapter {
=======
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.hailxph49396.duan1_quanlybanhang.DAO.DonHangDAO;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.DonHangDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.R;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.DonHangViewHolders> {

    Context context;
    ArrayList<DonHangDTO> list;
    DonHangDTO donHangDTO;
    DonHangDAO donHangDAO;

    public DonHangAdapter(Context context, ArrayList<DonHangDTO> list) {
        this.context = context;
        this.list = list;
        donHangDAO = new DonHangDAO(context);
    }

    @NonNull
    @Override
    public DonHangViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_don_hang, parent, false);
        return new DonHangViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangViewHolders holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DonHangViewHolders extends RecyclerView.ViewHolder {

        TextView txtNgay;
        TextView txtGio;
        TextView txtMaDH;
        TextView txtUser;

        public DonHangViewHolders(@NonNull View itemView) {
            super(itemView);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtGio = itemView.findViewById(R.id.txtGio);
            txtMaDH = itemView.findViewById(R.id.txtMaDH);
            txtUser = itemView.findViewById(R.id.txtUser);
        }

    }
>>>>>>> origin/master
}
