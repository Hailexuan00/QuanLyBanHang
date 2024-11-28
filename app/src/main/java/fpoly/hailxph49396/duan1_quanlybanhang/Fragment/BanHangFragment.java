package fpoly.hailxph49396.duan1_quanlybanhang.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.hailxph49396.duan1_quanlybanhang.Adapter.DonHangAdapter;
import fpoly.hailxph49396.duan1_quanlybanhang.BanHang;
import fpoly.hailxph49396.duan1_quanlybanhang.DAO.DonHangDAO;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.DonHangDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.Database.DbHelper;
import fpoly.hailxph49396.duan1_quanlybanhang.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BanHangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BanHangFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView rcvDonHang;
    DonHangDAO donHangDAO;
    ArrayList<DonHangDTO> listDH = new ArrayList<>();
    DonHangAdapter donHangAdapter;

    private String mParam1;
    private String mParam2;

    public BanHangFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BanHangFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ban_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton fabAddDonHang = view.findViewById(R.id.fabAddDH);
        rcvDonHang = view.findViewById(R.id.rcvDH);
        donHangDAO = new DonHangDAO(getContext());
        listDH = donHangDAO.getListDonHang();
        donHangAdapter = new DonHangAdapter(getContext(), listDH);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvDonHang.setLayoutManager(layoutManager);
        rcvDonHang.setAdapter(donHangAdapter);
        Toast.makeText(getContext(), "" + listDH.size(), Toast.LENGTH_SHORT).show();
        donHangAdapter.notifyDataSetChanged();

        fabAddDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BanHang.class);
                startActivity(intent);
            }
        });

    }
}