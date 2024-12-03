package fpoly.hailxph49396.duan1_quanlybanhang;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Handler;
import android.os.Looper;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.media.ToneGenerator;
import android.media.AudioManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import fpoly.hailxph49396.duan1_quanlybanhang.Adapter.SPofDHAdapter;
import fpoly.hailxph49396.duan1_quanlybanhang.DAO.ChiTietDonHangDAO;
import fpoly.hailxph49396.duan1_quanlybanhang.DAO.DonHangDAO;
import fpoly.hailxph49396.duan1_quanlybanhang.DAO.SanPhamDAo;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.ChiTietDonHangDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.DonHangDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.SanPhamDTO;

public class BanHang extends AppCompatActivity {
    private DecoratedBarcodeView barcodeView;
    private Set<String> scannedCodes = new HashSet<>();
    private Handler handler = new Handler(Looper.getMainLooper());
    private Handler scanHandler = new Handler();  // Handler mới để trì hoãn quét
    private Runnable scanRunnable;  // Lưu trữ Runnable để quản lý việc quét

    TextView txtTongTien;
    RecyclerView rcvSPofDH;
    ArrayList<ChiTietDonHangDTO> list;
    SPofDHAdapter sPofDHAdapter;
    SanPhamDAo sanPhamDAo;
    ChiTietDonHangDAO chiTietDonHangDAO;
    ChiTietDonHangDTO chiTietDonHangDTO;
    DonHangDAO donHangDAO;
    DonHangDTO donHangDTO;
    SharedPreferences sharedPreferences;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    int tongTien = 0;
    Button btnThanhToan;
    private final Object scanningLock = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ban_hang);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        donHangDAO = new DonHangDAO(BanHang.this);
        barcodeView = findViewById(R.id.barcode_scanner);
        barcodeView.decodeContinuous(callback);
        rcvSPofDH = findViewById(R.id.rcvSPofDH);
        txtTongTien = findViewById(R.id.txtTongTien);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        list = new ArrayList<>();
        donHangDTO = donHangDAO.getLatestDonHang();
        chiTietDonHangDAO = new ChiTietDonHangDAO(this);
        sPofDHAdapter = new SPofDHAdapter(this, list, new SPofDHAdapter.OnNumberPickerValueChangedListener() {
            @Override
            public void onNumberPickerValueChanged(int newProductValue) {
                tongTien = newProductValue;
                txtTongTien.setText(String.valueOf(tongTien));
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvSPofDH.setLayoutManager(layoutManager);
        rcvSPofDH.setAdapter(sPofDHAdapter);
        sanPhamDAo = new SanPhamDAo(this);
        btnThanhToan.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
            String user = sharedPreferences.getString("USERNAME", "");
            donHangDTO.setThanhTien(Integer.parseInt(String.valueOf(txtTongTien.getText())));
            donHangDTO.setUsername(user);
            try {
                donHangDTO.setNgay(sdf.parse(calendar.get(Calendar.DAY_OF_MONTH) + "/" +
                        (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR)));
            } catch (Exception e) {
                Toast.makeText(BanHang.this, "Sai ngày", Toast.LENGTH_SHORT).show();
            }
            donHangDTO.setGio(calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                    calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
            donHangDTO.setTrangThai(1);
            int check = donHangDAO.updateOrder(donHangDTO);
            Toast.makeText(this, donHangDTO.getUsername(), Toast.LENGTH_SHORT).show();
            Toast.makeText(BanHang.this, check > 0 ? "Xác nhận đơn hàng thành công" : "Xác nhận đơn hàng thất bại", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            String code = result.getText();
            synchronized (scanningLock) {
                if (!scannedCodes.contains(code)) {
                    scannedCodes.add(code);
                    // Lập lịch quét sau 1 giây
                    if (scanRunnable != null) {
                        scanHandler.removeCallbacks(scanRunnable); // Hủy bỏ nhiệm vụ cũ nếu có
                    }
                    scanRunnable = () -> processBarcode(code);
                    scanHandler.postDelayed(scanRunnable, 1000); // Delay 1 giây
                }
            }
        }

        private void processBarcode(final String code) {
            new Thread(() -> {
                SanPhamDTO sanPhamDTO = sanPhamDAo.findProductByBarcode(code);
                runOnUiThread(() -> {
                    if (sanPhamDTO != null) {
                        boolean chk = false;
                        for (ChiTietDonHangDTO item : list) {
                            if (item.getIdSanPham() == sanPhamDTO.getMaSanPham()) {
                                item.setSoLuong(item.getSoLuong() + 1);
                                int check2 = chiTietDonHangDAO.updateChiTietDonHang(item);
                                chk = true;
                                break;
                            }
                        }
                        if (chk){
                            sPofDHAdapter.notifyDataSetChanged();
                            return;
                        }else {
                            chiTietDonHangDTO = new ChiTietDonHangDTO();
                            chiTietDonHangDTO.setIdSanPham(sanPhamDTO.getMaSanPham());
                            chiTietDonHangDTO.setIdDonHang(donHangDTO.getMaDonHang());
                            chiTietDonHangDTO.setSoLuong(1);
                            list.add(chiTietDonHangDTO);
                            long check = chiTietDonHangDAO.insertChiTietDonHang(chiTietDonHangDTO);
                            String message = check != -1 ? "Thêm chi tiết đơn hàng thành công" : "Thêm chi tiết đơn hàng thất bại";
                            Toast.makeText(BanHang.this, message, Toast.LENGTH_SHORT).show();

                            tongTien += sanPhamDTO.getGiaBan();
                            txtTongTien.setText(String.valueOf(tongTien));
                            sPofDHAdapter.notifyDataSetChanged();
                        }

                    } else {
                        Toast.makeText(BanHang.this, "Không tìm thấy sản phẩm với mã vạch " + code, Toast.LENGTH_SHORT).show();
                    }

                    handler.postDelayed(() -> scannedCodes.remove(code), 1000);
                });
            }).start();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        int check = donHangDAO.deleteDonHang(donHangDTO.getMaDonHang());
//        Toast.makeText(this, check > 0 ? "Hủy đơn hàng thành công" : "Lỗi hủy đơn hàng", Toast.LENGTH_SHORT).show();
    }
}

