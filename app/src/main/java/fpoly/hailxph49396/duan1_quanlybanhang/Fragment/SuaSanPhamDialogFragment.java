package fpoly.hailxph49396.duan1_quanlybanhang.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.jetbrains.annotations.Nullable;

import fpoly.hailxph49396.duan1_quanlybanhang.DAO.SanPhamDAo;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.SanPhamDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.R;

public class SuaSanPhamDialogFragment extends DialogFragment {
    private ScanDialogFragment.OnScanResultListener listener;
    SanPhamFragment sanPhamFragment;
    SanPhamDTO sanPham;
    SanPhamDAo sanPhamDAo;

    public interface OnScanResultListener {
        void onScanResult(String result);
    }

    public void setOnScanResultListener(ScanDialogFragment.OnScanResultListener listener) {
        this.listener = listener;
    }

    public static SuaSanPhamDialogFragment newInstance(SanPhamDTO sanPhamDTO) {
        SuaSanPhamDialogFragment fragment = new SuaSanPhamDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("sanPham", sanPhamDTO);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_sua, container, false);


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                if (listener != null) {
                    listener.onScanResult(result.getContents());
                }
            } else {
                Toast.makeText(getActivity(), "Không quét được mã nào!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Dialog dialog = super.onCreateDialog(savedInstanceState);
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua, null);

        sanPhamDAo = new SanPhamDAo(getActivity());
        EditText etMaVach = view.findViewById(R.id.etMaVach);
        EditText etTenSanPham = view.findViewById(R.id.etTenSanPham);
        EditText etGiaBan = view.findViewById(R.id.etGiaBan);
        EditText etMoTa = view.findViewById(R.id.etMoTa);
        ImageButton btnScan = view.findViewById(R.id.btnScan);
        Button btnSave = view.findViewById(R.id.btnSave);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        if (getArguments() != null) {
            sanPham = (SanPhamDTO) getArguments().getSerializable("sanPham");
            etTenSanPham.setText(sanPham.getTenSanPham());
            etGiaBan.setText(String.valueOf(sanPham.getGiaBan()));
            etMaVach.setText(sanPham.getMaVach());
            etMoTa.setText(sanPham.getMoTa());
        }

        btnScan.setOnClickListener(v -> {
            IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
            integrator.setPrompt("Đưa mã vạch vào khung quét");
            integrator.setBeepEnabled(true);
            integrator.setOrientationLocked(true);
            integrator.initiateScan();
        });

        btnSave.setOnClickListener(v -> {
            String tenSanPham = etTenSanPham.getText().toString();
            int giaBan = Integer.parseInt(etGiaBan.getText().toString());
            String maVach = etMaVach.getText().toString();
            String moTa = etMoTa.getText().toString();
            sanPham.setTenSanPham(tenSanPham);
            sanPham.setGiaBan(giaBan);
            sanPham.setMaVach(maVach);
            sanPham.setMoTa(moTa);
            int update = sanPhamDAo.updateProduct(sanPham);
            Toast.makeText(getActivity(), update > 0 ? "Cập nhật thành công" : "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            dismiss();
        });

        btnCancel.setOnClickListener(v -> dismiss());

        builder.setView(view);
        return builder.create();
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    public void setDialogTitle(String title) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setTitle(title);
        }
    }
}
