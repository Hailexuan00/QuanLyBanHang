package fpoly.hailxph49396.duan1_quanlybanhang.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SalesManagement.db";
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
        // Create tables
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
                "thanh_tien REAL, " +
                "ngay DATE, " +
                "gio INTEGER, " +
                "ghi_chu TEXT, " +
                "trang_thai TEXT, " +
                "FOREIGN KEY(username) REFERENCES " + TABLE_TAI_KHOAN + "(username))");

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
                "mau_sac TEXT, " +
                "don_gia REAL, " +
                "ma_vach TEXT, " +
                "bao_hanh TEXT, " +
                "phien_ban TEXT, " +
                "ngay_san_xuat TEXT, " +
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
        // Drop older tables if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAI_KHOAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THONG_TIN_CA_NHAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DON_HANG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHI_TIET_DON_HANG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAN_PHAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHI_TIET_SAN_PHAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DANH_MUC);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOA_DON);

        // Recreate tables
        onCreate(db);
    }



