package fpoly.hailxph49396.duan1_quanlybanhang.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import fpoly.hailxph49396.duan1_quanlybanhang.DTO.NhanVienDTO;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SalesManagement";
    private static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_TAI_KHOAN = "TaiKhoan";
    public static final String TABLE_THONG_TIN_CA_NHAN = "ThongTinCaNhan";
    public static final String TABLE_DON_HANG = "DonHang";
    public static final String TABLE_CHI_TIET_DON_HANG = "ChiTietDonHang";
    public static final String TABLE_SAN_PHAM = "SanPham";
    public static final String TABLE_CHI_TIET_SAN_PHAM = "ChiTietSanPham";
    public static final String TABLE_DANH_MUC = "DanhMuc";
    public static final String TABLE_HOA_DON = "HoaDon";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_TAI_KHOAN + " (" +
                "username TEXT PRIMARY KEY, " +
                "password TEXT NOT NULL, " +
                "ma_nv INTEGER, " +
                "FOREIGN KEY(ma_nv) REFERENCES " + TABLE_THONG_TIN_CA_NHAN + "(ma_nv))");

        String insertTaiKhoan = "INSERT INTO " + TABLE_TAI_KHOAN +
                " (username, password, ma_nv) VALUES " +
                "('admin', 'admin', NULL), " +
                "('hailx', 'ph49396', NULL)";
        db.execSQL(insertTaiKhoan);

        db.execSQL("CREATE TABLE " + TABLE_THONG_TIN_CA_NHAN + " (" +
                "ma_nv INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ten TEXT NOT NULL, " +
                "ho_va_ten_dem TEXT, " +
                "gioi_tinh TEXT, " +
                "so_dien_thoai TEXT, " +
                "email TEXT, " +
                "dia_chi TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_DON_HANG + " (" +
                "id_don_hang INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, " +
                "so_dien_thoai_kh TEXT, " +
                "thanh_tien INTEGER, " +
                "ngay DATE, " +
                "gio INTEGER, " +
                "trang_thai INTEGER, " +
                "FOREIGN KEY(username) REFERENCES " + TABLE_TAI_KHOAN + "(username))");

        // Bảng TABLE_THONG_TIN_CA_NHAN
        db.execSQL("INSERT INTO " + TABLE_THONG_TIN_CA_NHAN + " (ten, ho_va_ten_dem, gioi_tinh, so_dien_thoai, email, dia_chi) VALUES " +
                "('Nam', 'Nguyen Van', 'Nam', '0901234567', 'nam.nguyen@example.com', 'Thanh Hoa')," +
                "('Linh', 'Pham Thi', 'Nu', '0912345678', 'linh.pham@example.com', 'Ha Noi')," +
                "('Hung', 'Tran Minh', 'Nam', '0923456789', 'hung.tran@example.com', 'Hai Phong')," +
                "('Hoa', 'Le Thi', 'Nu', '0934567890', 'hoa.le@example.com', 'Da Nang')," +
                "('Khanh', 'Do Van', 'Nam', '0945678901', 'khanh.do@example.com', 'Ho Chi Minh City')");

// Bảng TABLE_TAI_KHOAN
        db.execSQL("INSERT INTO " + TABLE_TAI_KHOAN + " (username, password, ma_nv) VALUES " +
                "('user1', 'password1', 1)," +
                "('user2', 'password2', 2)," +
                "('user3', 'password3', 3)," +
                "('user4', 'password4', 4)," +
                "('user5', 'password5', 5)");

        db.execSQL("INSERT INTO " + TABLE_DON_HANG + " (username, so_dien_thoai_kh, thanh_tien, ngay, gio, trang_thai) VALUES " +
                "('user1', '0987654321', 100000, '2024-11-01', 10, 1)," +
                "('user2', '0976543210', 200000, '2024-11-02', 12, 1)," +
                "('user3', '0965432109', 150000, '2024-11-03', 15, 1)," +
                "('user4', '0954321098', 250000, '2024-11-04', 18, 1)," +
                "('user5', '0943210987', 300000, '2024-11-05', 20, 1)");

        db.execSQL("CREATE TABLE " + TABLE_CHI_TIET_DON_HANG + " (" +
                "id_ctdh INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_don_hang INTEGER, " +
                "id_ctsp INTEGER, " +
                "so_luong INTEGER, " +
                "FOREIGN KEY(id_don_hang) REFERENCES " + TABLE_DON_HANG + "(id_don_hang), " +
                "FOREIGN KEY(id_ctsp) REFERENCES " + TABLE_CHI_TIET_SAN_PHAM + "(id_ctsp))");

        db.execSQL("CREATE TABLE " + TABLE_SAN_PHAM + " (" +
                "id_san_pham INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_danh_muc INTEGER, " +
                "ten_san_pham TEXT NOT NULL, " +
                "nha_san_xuat TEXT, " +
                "mo_ta TEXT, " +
                "image TEXT, " +
                "FOREIGN KEY(id_danh_muc) REFERENCES " + TABLE_DANH_MUC + "(id_danh_muc))");

        db.execSQL("CREATE TABLE " + TABLE_CHI_TIET_SAN_PHAM + " (" +
                "id_ctsp INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_san_pham INTEGER, " +
                "don_gia INTEGER, " +
                "ma_vach TEXT, " +
                "phien_ban TEXT, " +
                "FOREIGN KEY(id_san_pham) REFERENCES " + TABLE_SAN_PHAM + "(id_san_pham))");

        db.execSQL("CREATE TABLE " + TABLE_DANH_MUC + " (" +
                "id_danh_muc INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ten_danh_muc TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_HOA_DON + " (" +
                "id_hoa_don INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_don_hang INTEGER, " +
                "ma_so_thue TEXT, " +
                "FOREIGN KEY(id_don_hang) REFERENCES " + TABLE_DON_HANG + "(id_don_hang))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAI_KHOAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THONG_TIN_CA_NHAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DON_HANG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHI_TIET_DON_HANG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAN_PHAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHI_TIET_SAN_PHAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DANH_MUC);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOA_DON);

        onCreate(db);
    }

    public List<NhanVienDTO> getAllEmployees(SQLiteDatabase readableDatabase) {
        return null;
    }

    public void addEmployee(SQLiteDatabase writableDatabase, NhanVienDTO newNhanVien) {
    }
}



