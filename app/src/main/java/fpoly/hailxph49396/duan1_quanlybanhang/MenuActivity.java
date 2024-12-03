package fpoly.hailxph49396.duan1_quanlybanhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import fpoly.hailxph49396.duan1_quanlybanhang.Fragment.BanHangFragment;
import fpoly.hailxph49396.duan1_quanlybanhang.Fragment.CaNhanFragment;
import fpoly.hailxph49396.duan1_quanlybanhang.Fragment.DonHangFragment;
import fpoly.hailxph49396.duan1_quanlybanhang.Fragment.MatKhauFragment;
import fpoly.hailxph49396.duan1_quanlybanhang.Fragment.NhanVienFragment;
import fpoly.hailxph49396.duan1_quanlybanhang.Fragment.NhapHangFragment;
import fpoly.hailxph49396.duan1_quanlybanhang.Fragment.SanPhamFragment;
import fpoly.hailxph49396.duan1_quanlybanhang.Fragment.ThongKeFragment;

public class MenuActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        toolbar = findViewById(R.id.toolbar1);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nvView);
        navigationView.getMenu().findItem(R.id.nav_nhanVien).setVisible(true);
        Intent intent = getIntent();
        String username = intent.getStringExtra("user");
        if (!username.equalsIgnoreCase("admin")){
            navigationView.getMenu().findItem(R.id.nav_nhanVien).setVisible(false);
        }


        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Quản lý Sản Phẩm");
        BanHangFragment banHangFragment = new BanHangFragment();
        manager.beginTransaction()
                .replace(R.id.flContent, banHangFragment)
                .commit();

        NavigationView nv = findViewById(R.id.nvView);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                if (item.getItemId() == R.id.nav_banHang) {
                    setTitle("Quản lý Bán Hàng");
                    BanHangFragment banHangFragment = new BanHangFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, banHangFragment)
                            .commit();
                } else if (item.getItemId() == R.id.nav_nhapHang) {
                    setTitle("Quản lý Nhập Hàng");
                    NhapHangFragment nhapHangFragment = new NhapHangFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, nhapHangFragment)
                            .commit();
                } else if (item.getItemId() == R.id.nav_sanPham) {
                    setTitle("Quản lý Sản Phẩm");
                    SanPhamFragment sanPhamFragment = new SanPhamFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, sanPhamFragment)
                            .commit();
                } else if (item.getItemId() == R.id.nav_donHang) {
                    setTitle("Quản lý Dơn Hàng");
                    DonHangFragment donHangFragment = new DonHangFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, donHangFragment)
                            .commit();
                } else if (item.getItemId() == R.id.nav_nhanVien) {
                    setTitle("Quản lý Nhân Viên");
                    NhanVienFragment nhanVienFragment = new NhanVienFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, nhanVienFragment)
                            .commit();
                } else if (item.getItemId() == R.id.nav_thongKe) {
                    setTitle("Thống kê doanh thu");
                    ThongKeFragment thongKeFragment = new ThongKeFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, thongKeFragment)
                            .commit();
                }
                else if (item.getItemId() == R.id.sub_Pass) {
                    setTitle("Đổi mật khẩu");
                    MatKhauFragment matKhauFragment = new MatKhauFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, matKhauFragment)
                            .commit();
                } else if (item.getItemId() == R.id.sub_ThongTin) {
                    setTitle("Thông Tin Cá Nhân");
                    CaNhanFragment caNhanFragment = new CaNhanFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, caNhanFragment)
                            .commit();
                } else if (item.getItemId() == R.id.sub_Logout) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }

                drawer.closeDrawers();
                return true;
            }
        });
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}