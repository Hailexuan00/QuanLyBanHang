package fpoly.hailxph49396.duan1_quanlybanhang;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import fpoly.hailxph49396.duan1_quanlybanhang.Adapter.SPofDHAdapter;
import fpoly.hailxph49396.duan1_quanlybanhang.DAO.SanPhamDAo;
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
    int tongTien = 0;
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
        list = new ArrayList<>();
        list.add(new SanPhamDTO(1,1,"Haha",1,1,"Hehe", "Hihi"));
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