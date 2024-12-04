package fpoly.hailxph49396.duan1_quanlybanhang.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import fpoly.hailxph49396.duan1_quanlybanhang.DTO.NhanVienDTO;


public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SalesManagement";
    private static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_TAI_KHOAN = "TaiKhoan";
    public static final String TABLE_DON_HANG = "DonHang";
    public static final String TABLE_CHI_TIET_DON_HANG = "ChiTietDonHang";
    public static final String TABLE_SAN_PHAM = "SanPham";
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
                "gio TEXT, " +
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
                "ton_kho INTEGER, " +
                "ma_vach TEXT," +
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
                "('user5', 'passwordxyz', 'Hoang', 'Van E', 'Nam', '0987654325', 'user5@example.com', 'Thanh Hóa'), " +
                "('user6', 'password555', 'Nguyen', 'Thi G', 'Nữ', '0987654326', 'user6@example.com', 'Hà Nội'), " +
                "('user7', 'password666', 'Nguyen', 'Hoang H', 'Nam', '0987654327', 'user7@example.com', 'TP. Hồ Chí Minh'), " +
                "('user8', 'password777', 'Truong', 'Thi J', 'Nữ', '0987654328', 'user8@example.com', 'Đà Nẵng'), " +
                "('user9', 'password888', 'Le', 'Anh K', 'Nam', '0987654329', 'user9@example.com', 'Hải Phòng'), " +
                "('user10', 'password999', 'Pham', 'Viet L', 'Nam', '0987654330', 'user10@example.com', 'Thanh Hóa');");

        db.execSQL("INSERT INTO " + TABLE_DANH_MUC + "(id_danh_muc, ten_danh_muc) VALUES " +
                "(1, 'Điện tử'), " +
                "(2, 'Gia dụng'), " +
                "(3, 'Thời trang'), " +
                "(4, 'Nhà sách'), " +
                "(5, 'Văn phòng phẩm'), " +
                "(6, 'Sức khỏe')");

        db.execSQL("INSERT INTO " + TABLE_SAN_PHAM + " (id_san_pham, id_danh_muc, ten_san_pham, don_gia, ton_kho, ma_vach, mo_ta) VALUES " +
                "(1, 1, 'Điện thoại iPhone 14', 25000000, 10, '9780486996844', 'Điện thoại cao cấp Apple'), " +
                "(2, 2, 'Tea+', 500000, 50, '8934588870118', 'Nước uống'), " +
                "(3, 3, 'Áo thun nam', 200000, 100, '8936050360974', 'Chất liệu cotton'), " +
                "(4, 1, 'Samsung Galaxy S22', 22000000, 15, '9780486996871', 'Điện thoại Samsung cao cấp'), " +
                "(5, 2, 'Nồi cơm điện', 700000, 25, '8934588870125', 'Nồi cơm điện thông minh'), " +
                "(6, 3, 'Áo sơ mi nữ', 300000, 50, '8934588870129', 'Áo sơ mi thời trang'), " +
                "(7, 4, 'Sách lập trình Java', 150000, 30, '8936050360975', 'Sách hướng dẫn lập trình Java'), " +
                "(8, 5, 'Bút bi', 5000, 200, '8934588870133', 'Bút bi loại tốt'), " +
                "(9, 6, 'Thuốc giảm đau', 15000, 100, '8936050360976', 'Thuốc giảm đau hiệu quả'), " +
                "(10, 1, 'Apple Watch', 10000000, 20, '9780486996895', 'Đồng hồ thông minh Apple');");

        db.execSQL("INSERT INTO " + TABLE_DON_HANG + " (id_don_hang, username, so_dien_thoai_kh, thanh_tien, ngay, gio, trang_thai) VALUES " +
                "(1, 'user1', '0987654321', 25500000, '01/11/2024', '10:00:00', 1), " +
                "(2, 'user2', '0987654322', 200000, '02/11/2024', '15:00:00', 1), " +
                "(3, 'user3', '0987654323', 5000000, '03/11/2024', '11:30:00', 1), " +
                "(4, 'user4', '0987654324', 1000000, '04/11/2024', '09:15:00', 1), " +
                "(5, 'user5', '0987654325', 15000000, '05/11/2024', '17:00:00', 1), " +
                "(6, 'user6', '0987654326', 2500000, '06/11/2024', '13:45:00', 1), " +
                "(7, 'user7', '0987654327', 10000000, '07/11/2024', '14:20:00', 1), " +
                "(8, 'user8', '0987654328', 1200000, '08/11/2024', '16:30:00', 1), " +
                "(9, 'user9', '0987654329', 8000000, '09/11/2024', '18:00:00', 1), " +
                "(10, 'user10', '0987654330', 500000, '10/11/2024', '12:00:00', 1);");

        db.execSQL("INSERT INTO " + TABLE_CHI_TIET_DON_HANG + " (id_ctdh, id_don_hang, id_san_pham, so_luong) VALUES " +
                "(1, 1, 1, 1), " +   // Đơn hàng 1: Điện thoại iPhone 14
                "(2, 1, 2, 1), " +   // Đơn hàng 1: Tea+
                "(3, 2, 3, 1), " +   // Đơn hàng 2: Áo thun nam
                "(4, 2, 5, 1), " +   // Đơn hàng 2: Nồi cơm điện
                "(5, 3, 4, 2), " +   // Đơn hàng 3: Samsung Galaxy S22
                "(6, 3, 6, 1), " +   // Đơn hàng 3: Áo sơ mi nữ
                "(7, 4, 8, 1), " +   // Đơn hàng 4: Bút bi
                "(8, 4, 9, 1), " +   // Đơn hàng 4: Thuốc giảm đau
                "(9, 5, 7, 1), " +   // Đơn hàng 5: Sách lập trình Java
                "(10, 5, 10, 1), " +  // Đơn hàng 5: Apple Watch
                "(11, 6, 3, 2), " +  // Đơn hàng 6: Áo thun nam
                "(12, 6, 10, 1), " +  // Đơn hàng 6: Apple Watch
                "(13, 7, 4, 1), " +  // Đơn hàng 7: Samsung Galaxy S22
                "(14, 7, 5, 1), " +  // Đơn hàng 7: Nồi cơm điện
                "(15, 8, 2, 3), " +  // Đơn hàng 8: Tea+
                "(16, 8, 8, 1), " +  // Đơn hàng 8: Bút bi
                "(17, 9, 6, 1), " +  // Đơn hàng 9: Áo sơ mi nữ
                "(18, 9, 9, 2), " +  // Đơn hàng 9: Thuốc giảm đau
                "(19, 10, 7, 1), " +  // Đơn hàng 10: Sách lập trình Java
                "(20, 10, 8, 2);");   // Đơn hàng 10: Bút bi

        db.execSQL("INSERT INTO " + TABLE_HOA_DON + " (id_hoa_don, id_don_hang, ma_so_thue) VALUES " +
                "(1, 1, '123456789')," +
                "(2, 2, '987654321')," +
                "(3, 3, '555555555')," +
                "(4, 4, '666666666')," +
                "(5, 5, '777777777')," +
                "(6, 6, '888888888')," +
                "(7, 7, '999999999')," +
                "(8, 8, '101010101')," +
                "(9, 9, '202020202')," +
                "(10, 10, '303030303');");



//        db.execSQL("INSERT INTO " + TABLE_TAI_KHOAN + " (username, password, ten, ho_va_ten_dem, gioi_tinh, so_dien_thoai, email, dia_chi) VALUES " +
//                "('admin', 'admin', 'Admin', 'Hai','Nam', '0987654321', 'admin@example.com', 'Hà Nội'), " +
//                "('user1', 'password123', 'Nguyen', 'Van A', 'Nam', '0987654321', 'user1@example.com', 'Hà Nội'), " +
//                "('user2', 'password456', 'Tran', 'Thi B', 'Nữ', '0987654322', 'user2@example.com', 'TP. Hồ Chí Minh'), " +
//                "('user3', 'password789', 'Le', 'Minh C', 'Nam', '0987654323', 'user3@example.com', 'Đà Nẵng'), " +
//                "('user4', 'passwordabc', 'Pham', 'Thi D', 'Nữ', '0987654324', 'user4@example.com', 'Hải Phòng'), " +
//                "('user5', 'passwordxyz', 'Hoang', 'Van E', 'Nam', '0987654325', 'user5@example.com', 'Thanh Hóa');");
//
//        db.execSQL("INSERT INTO " + TABLE_DANH_MUC + "(id_danh_muc, ten_danh_muc) VALUES " +
//                "(1, 'Điện tử'), " +
//                "(2, 'Gia dụng'), " +
//                "(3, 'Thời trang')");
//        db.execSQL("INSERT INTO " + TABLE_SAN_PHAM + " (id_san_pham, id_danh_muc, ten_san_pham, don_gia, ton_kho, ma_vach, mo_ta) VALUES " +
//                "(1, 1, 'Điện thoại iPhone 14', 25000000, 10, '9780486996844', 'Điện thoại cao cấp Apple'), " +
//                "(2, 2, 'Tea+', 500000, 50, '8934588870118', 'Nuoc uong'), " +
//                "(3, 3, 'Áo thun nam', 200000, 100, '8936050360974', 'Chất liệu cotton')");
//        db.execSQL("INSERT INTO " + TABLE_DON_HANG + " (id_don_hang, username, so_dien_thoai_kh, thanh_tien, ngay, gio, trang_thai) VALUES\n" +
//                "(1, 'user1', '0987654321', 25500000, '01/11/2024', '10:00:00', 1)," +
//                "(2, 'user2', '0987654322', 200000, '02/11/2024', '15:00:00', 1)");
//        db.execSQL("INSERT INTO " + TABLE_CHI_TIET_DON_HANG + " (id_ctdh, id_don_hang, id_san_pham, so_luong) VALUES " +
//                "(1, 1, 1, 1), " +
//                "(2, 1, 2, 1), " +
//                "(3, 2, 3, 1);");
//        db.execSQL("INSERT INTO " + TABLE_HOA_DON + " (id_hoa_don, id_don_hang, ma_so_thue) VALUES\n" +
//                "(1, 1, '123456789')," +
//                "(2, 2, '987654321');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAI_KHOAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DON_HANG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHI_TIET_DON_HANG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAN_PHAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DANH_MUC);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOA_DON);
        onCreate(db);
    }



}


