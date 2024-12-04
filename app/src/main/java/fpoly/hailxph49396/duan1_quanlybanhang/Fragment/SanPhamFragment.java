package fpoly.hailxph49396.duan1_quanlybanhang.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

import fpoly.hailxph49396.duan1_quanlybanhang.Adapter.SanPhamAdapter;
import fpoly.hailxph49396.duan1_quanlybanhang.DAO.SanPhamDAo;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.SanPhamDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.R;
import fpoly.hailxph49396.duan1_quanlybanhang.ShowMoney.ShowMoney;

public class SanPhamFragment extends Fragment {

    private RecyclerView rvSanPham;
    private Button btnThem, btnSua, btnXoa, btnQuetMaVach;
    private SanPhamDAo sanPhamDao;
    private ArrayList<SanPhamDTO> sanPhamList;
    private SanPhamAdapter sanPhamAdapter;
    ShowMoney showMoney;
    View dialogScanView;
    private AlertDialog.Builder barcodeDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_san_pham, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvSanPham = view.findViewById(R.id.rvSanPham);
        btnThem = view.findViewById(R.id.btnThemSanPham);
        btnQuetMaVach = view.findViewById(R.id.btnQuetMaVach);

        sanPhamDao = new SanPhamDAo(getContext());
        sanPhamList = sanPhamDao.getAllProducts();

        sanPhamAdapter = new SanPhamAdapter(getContext(), sanPhamList, new SanPhamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.dialog_san_pham, null);
                TextView tvTenSanPham = dialogView.findViewById(R.id.tvTenSanPham);
                TextView tvGiaBan = dialogView.findViewById(R.id.tvGiaBan);
                TextView tvTonKho = dialogView.findViewById(R.id.tvTonKho);
                TextView tvMaVach = dialogView.findViewById(R.id.tvMaVach);
                TextView tvMoTa = dialogView.findViewById(R.id.tvMoTa);
                Button btnNhap = dialogView.findViewById(R.id.btnNhap);
                Button btnSua = dialogView.findViewById(R.id.btnSua);
                Button btnXoa = dialogView.findViewById(R.id.btnXoa);
                // Điền dữ liệu vào các TextView
                tvTenSanPham.setText(sanPhamList.get(position).getTenSanPham());
                tvGiaBan.setText("Giá bán: " + showMoney.formatCurrency(sanPhamList.get(position).getGiaBan()) + " VNĐ");
                tvTonKho.setText("Tồn kho: " + sanPhamList.get(position).getTonKho());
                tvMaVach.setText("Mã vạch: " + sanPhamList.get(position).getMaVach());
                tvMoTa.setText("Mô tả: " + sanPhamList.get(position).getMoTa());
                btnNhap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        LayoutInflater inflater = LayoutInflater.from(getContext());
                        View dialogView = inflater.inflate(R.layout.dialog_nhap, null);

                        // Tham chiếu các thành phần
                        EditText etNumberInput = dialogView.findViewById(R.id.etNumberInput);
                        Button btnOk = dialogView.findViewById(R.id.btnOk);
                        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

                        builder.setView(dialogView);
                        AlertDialog dialogNhap = builder.create();
                        dialogNhap.setCancelable(false);

                        // Xử lý nút OK
                        btnOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String input = etNumberInput.getText().toString().trim();
                                if (input.isEmpty()) {
                                    etNumberInput.setError("Vui lòng nhập số");
                                } else {
                                    int number = Integer.parseInt(input);
                                    sanPhamList.get(position).setTonKho(sanPhamList.get(position).getTonKho() + number);
                                    int update = sanPhamDao.updateProduct(sanPhamList.get(position));
                                    sanPhamAdapter.notifyDataSetChanged();
                                    dialogNhap.dismiss();
                                }
                            }
                        });

                        // Xử lý nút Cancel
                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogNhap.dismiss();
                            }
                        });

                        // Hiển thị dialogNhap
                        dialogNhap.show();
                    }
                });

                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Xác nhận")
                                .setMessage("Bạn có chắc chắn muốn tiếp tục?");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int delete = sanPhamDao.deleteProduct(sanPhamList.get(position).getMaVach());
                                sanPhamList.clear();
                                sanPhamList.addAll(sanPhamDao.getAllProducts());
                                sanPhamAdapter.notifyDataSetChanged();
                                if (delete > 0) {
                                    Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        // Tạo và hiển thị dialogSP
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });

                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LayoutInflater inflater = LayoutInflater.from(getContext());
                        dialogScanView = inflater.inflate(R.layout.dialog_sua, null);

                        // Tạo dialogSP
                        barcodeDialog = new AlertDialog.Builder(getContext());
                        barcodeDialog.setView(dialogScanView);

                        // Tham chiếu các EditText trong dialogSP
                        EditText etTenSanPham = dialogScanView.findViewById(R.id.etTenSanPham);
                        EditText etGiaBan = dialogScanView.findViewById(R.id.etGiaBan);
                        EditText etMaVach = dialogScanView.findViewById(R.id.etMaVach);
                        EditText etMoTa = dialogScanView.findViewById(R.id.etMoTa);

                        // Set giá trị hiện tại (nếu có)
                        etTenSanPham.setText(sanPhamList.get(position).getTenSanPham());
                        etGiaBan.setText(sanPhamList.get(position).getGiaBan() + "");
                        etMaVach.setText(sanPhamList.get(position).getMaVach());
                        etMoTa.setText(sanPhamList.get(position).getMoTa());

                        // Nút Lưu và Huỷ
                        ImageButton btnScan = dialogScanView.findViewById(R.id.btnScan);
                        Button btnSave = dialogScanView.findViewById(R.id.btnSave);
                        Button btnCancel = dialogScanView.findViewById(R.id.btnCancel);

                        AlertDialog dialog = barcodeDialog.create();
                        dialog.setCancelable(false);

                        btnScan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                                integrator.setPrompt("Đang quét mã vạch...");
                                integrator.setCameraId(0); // Dùng camera sau
                                integrator.setBeepEnabled(true);
                                integrator.setBarcodeImageEnabled(false);
                                integrator.initiateScan();
                                dialog.dismiss();
                            }
                        });

                        btnSave.setOnClickListener(saveView -> {
                            sanPhamList.get(position).setTenSanPham(etTenSanPham.getText().toString());
                            sanPhamList.get(position).setGiaBan(Integer.parseInt(etGiaBan.getText().toString()));
                            sanPhamList.get(position).setMaVach(etMaVach.getText().toString());
                            sanPhamList.get(position).setMoTa(etMoTa.getText().toString());
                            int update = sanPhamDao.updateProduct(sanPhamList.get(position));
                            Toast.makeText(getContext(), "" + update, Toast.LENGTH_SHORT).show();
                            sanPhamAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        });

                        btnCancel.setOnClickListener(cancelView -> {
                            dialog.dismiss();
                        });

                        dialog.show();
                    }
                });

                // Tạo AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(dialogView);
                AlertDialog dialogSP = builder.create();
                dialogSP.getWindow().setBackgroundDrawableResource(R.drawable.dialog_shape);
                // Hiển thị dialogSP
                dialogSP.show();
                // Đóng dialogSP khi click vào nút đóng
            }
        });
        rvSanPham.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvSanPham.setAdapter(sanPhamAdapter);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                String barcode = result.getContents();
                if (barcodeDialog != null) {
                    barcodeDialog.show(); // Hiển thị lại Dialog nếu cần
                    EditText etBarcode = dialogScanView.findViewById(R.id.etMaVach);
                    if (etBarcode != null) {
                        etBarcode.setText(barcode);
                        Toast.makeText(getContext(), " "+ barcode, Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(getContext(), "Không quét được mã", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
