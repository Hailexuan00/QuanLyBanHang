package fpoly.hailxph49396.duan1_quanlybanhang.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


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

        db.execSQL("CREATE TABLE " + TABLE_CHI_TIET_DON_HANG + " (" +
                "id_ctdh INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_don_hang INTEGER, " +
                "id_san_pham INTEGER, " +
                "so_luong INTEGER, " +
                "FOREIGN KEY(id_don_hang) REFERENCES " + TABLE_DON_HANG + "(id_don_hang), " +
                "FOREIGN KEY(id_san_pham) REFERENCES " + TABLE_SAN_PHAM + "(id_san_pham))");

        db.execSQL("CREATE TABLE " + TABLE_SAN_PHAM + " (" +
                "id_san_pham INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_danh_muc INTEGER, " +
                "ten_san_pham TEXT NOT NULL, " +
                "don_gia INTEGER, " +
                "mo_ta TEXT, " +
                "FOREIGN KEY(id_danh_muc) REFERENCES " + TABLE_DANH_MUC + "(id_danh_muc))");


        db.execSQL("CREATE TABLE " + TABLE_DANH_MUC + " (" +
                "id_danh_muc INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ten_danh_muc TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_HOA_DON + " (" +
                "id_hoa_don INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_don_hang INTEGER, " +
                "ma_so_thue TEXT, " +
                "FOREIGN KEY(id_don_hang) REFERENCES " + TABLE_DON_HANG + "(id_don_hang))");

        db.execSQL("INSERT INTO " + TABLE_TAI_KHOAN + " (username, password, ten, ho_va_ten_dem, gioi_tinh, so_dien_thoai, email, dia_chi) VALUES " +
                "('admin', 'admin', 'Admin', 'Hai','Nam', '0987654321', 'admin@example.com', 'Hà Nội'), " +
                "('user1', 'password123', 'Nguyen', 'Van A', 'Nam', '0987654321', 'user1@example.com', 'Hà Nội'), " +
                "('user2', 'password456', 'Tran', 'Thi B', 'Nữ', '0987654322', 'user2@example.com', 'TP. Hồ Chí Minh'), " +
                "('user3', 'password789', 'Le', 'Minh C', 'Nam', '0987654323', 'user3@example.com', 'Đà Nẵng'), " +
                "('user4', 'passwordabc', 'Pham', 'Thi D', 'Nữ', '0987654324', 'user4@example.com', 'Hải Phòng'), " +
                "('user5', 'passwordxyz', 'Hoang', 'Van E', 'Nam', '0987654325', 'user5@example.com', 'Thanh Hóa');");



        db.execSQL("INSERT INTO " + TABLE_DANH_MUC + "(id_danh_muc, ten_danh_muc) VALUES " +
                "                (1, 'Điện tử'), " +
                "                (2, 'Gia dụng'), " +
                "        (3, 'Thời trang')");
        db.execSQL("INSERT INTO " + TABLE_SAN_PHAM + " (id_san_pham, id_danh_muc, ten_san_pham, don_gia, mo_ta) VALUES\n" +
                "                (1, 1, 'Điện thoại iPhone 14', 25000000, 'Điện thoại cao cấp Apple'), " +
                "                (2, 2, 'Nồi cơm điện', 500000, 'Nồi cơm đa năng'), " +
                "        (3, 3, 'Áo thun nam', 200000, 'Chất liệu cotton')");
        db.execSQL("INSERT INTO "+TABLE_DON_HANG+" (id_don_hang, username, so_dien_thoai_kh, thanh_tien, ngay, gio, trang_thai) VALUES\n" +
                "                (1, 'user1', '0987654321', 25500000, '2024-11-01', 10, 1)," +
                "        (2, 'user2', '0987654322', 200000, '2024-11-02', 15, 0)");
        db.execSQL("INSERT INTO "+TABLE_CHI_TIET_DON_HANG+" (id_ctdh, id_don_hang, id_san_pham, so_luong) VALUES " +
                "                (1, 1, 1, 1), " +
                "        (2, 1, 2, 1), " +
                "        (3, 2, 3, 1);");
        db.execSQL("INSERT INTO "+TABLE_HOA_DON+" (id_hoa_don, id_don_hang, ma_so_thue) VALUES\n" +
                "                (1, 1, '123456789')," +
                "        (2, 2, '987654321');");
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
}


