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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.Result;
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
    TextView txtTongTien;
    RecyclerView rcvSPofDH;
    ArrayList<SanPhamDTO>list;
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


        barcodeView = findViewById(R.id.barcode_scanner);
        barcodeView.decodeContinuous(callback);
        rcvSPofDH = findViewById(R.id.rcvSPofDH);
        txtTongTien = findViewById(R.id.txtTongTien);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        list = new ArrayList<>();
        sPofDHAdapter = new SPofDHAdapter(this, list, new SPofDHAdapter.OnNumberPickerValueChangedListener() {
            @Override
            public void onNumberPickerValueChanged(int newProductValue) {
                tongTien = newProductValue;
                txtTongTien.setText("" + tongTien);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvSPofDH.setLayoutManager(layoutManager);
        rcvSPofDH.setAdapter(sPofDHAdapter);
        sanPhamDAo = new SanPhamDAo(this);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                String user = sharedPreferences.getString("USERNAME", "");
                donHangDTO = new DonHangDTO();
                donHangDTO.setThanhTien(Integer.parseInt(String.valueOf(txtTongTien.getText())));
                donHangDTO.setUsername(user);
                try {
                    donHangDTO.setNgay(sdf.parse(dayOfMonth + "/" + month + "/" + year));
                }catch (Exception e){
                    Toast.makeText(BanHang.this, "Sai r", Toast.LENGTH_SHORT).show();
                }
                donHangDTO.setGio(hour + ":" + minute + ":" + second);
                donHangDTO.setTrangThai(1);
                donHangDAO = new DonHangDAO(BanHang.this);
                long check = donHangDAO.addDonHang(donHangDTO);
                if (check != -1){
                    Toast.makeText(BanHang.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(BanHang.this, "That bai", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            String code = result.getText();
            if (!scannedCodes.contains(code)) {
                scannedCodes.add(code);
                ToneGenerator toneGen = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                toneGen.startTone(ToneGenerator.TONE_PROP_BEEP, 200);
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (vibrator != null) {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vibrator.vibrate(200);
                    }
                }
                SanPhamDTO sanPhamDTO = sanPhamDAo.findProductByBarcode(code);
                if (sanPhamDTO != null){
                    list.add(sanPhamDTO);
                    tongTien = tongTien + sanPhamDTO.getGiaBan();
                    txtTongTien.setText("" + tongTien);
                    sPofDHAdapter.notifyDataSetChanged();
                    Toast.makeText(BanHang.this, "Đã thấy " + sanPhamDTO.getTenSanPham(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(BanHang.this, "Không tìm thấy sản phẩm với mã vạch " + code, Toast.LENGTH_SHORT).show();
                }
                handler.postDelayed(() -> scannedCodes.remove(code), 1000);
            }
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
}