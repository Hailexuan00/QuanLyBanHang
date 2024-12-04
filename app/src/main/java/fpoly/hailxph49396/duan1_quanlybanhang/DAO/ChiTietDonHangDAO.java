package fpoly.hailxph49396.duan1_quanlybanhang.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.hailxph49396.duan1_quanlybanhang.DTO.ChiTietDonHangDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.Database.DbHelper;

public class ChiTietDonHangDAO {
    private SQLiteDatabase db;
    private DbHelper dbHelper;

    public ChiTietDonHangDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // Lấy danh sách chi tiết đơn hàng theo id đơn hàng
    @SuppressLint("Range")
    public ArrayList<ChiTietDonHangDTO> getCTDHByIdDonHang(int idDonHang) {
        ArrayList<ChiTietDonHangDTO> chiTietDonHangList = new ArrayList<>();
        String query = "SELECT * FROM " + DbHelper.TABLE_CHI_TIET_DON_HANG + " WHERE id_don_hang = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idDonHang)});

        if (cursor.moveToFirst()) {
            do {
                int idChiTietDonHang = cursor.getInt(cursor.getColumnIndex("id_ctdh"));
                int idSanPham = cursor.getInt(cursor.getColumnIndex("id_san_pham"));
                int soLuong = cursor.getInt(cursor.getColumnIndex("so_luong"));
                ChiTietDonHangDTO chiTietDonHang = new ChiTietDonHangDTO(idChiTietDonHang, idDonHang, idSanPham, soLuong);
                chiTietDonHangList.add(chiTietDonHang);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return chiTietDonHangList;
    }

    // Thêm mới chi tiết đơn hàng
    public long insertChiTietDonHang(ChiTietDonHangDTO chiTietDonHang) {
        ContentValues values = new ContentValues();
        values.put("id_don_hang", chiTietDonHang.getIdDonHang());
        values.put("id_san_pham", chiTietDonHang.getIdSanPham());
        values.put("so_luong", chiTietDonHang.getSoLuong());
        return db.insert(DbHelper.TABLE_CHI_TIET_DON_HANG, null, values);
    }

    // Cập nhật chi tiết đơn hàng
    public int updateChiTietDonHang(ChiTietDonHangDTO chiTietDonHang) {
        ContentValues values = new ContentValues();
        values.put("so_luong", chiTietDonHang.getSoLuong());

        return db.update(DbHelper.TABLE_CHI_TIET_DON_HANG, values, "id_ctdh = ?", new String[]{String.valueOf(chiTietDonHang.getIdChiTietDonHang())});
    }

    // Xóa chi tiết đơn hàng
    public int deleteChiTietDonHang(int idChiTietDonHang) {
        return db.delete(DbHelper.TABLE_CHI_TIET_DON_HANG, "id_ctdh = ?", new String[]{String.valueOf(idChiTietDonHang)});
    }

    // Đóng kết nối cơ sở dữ liệu
    public void close() {
        db.close();
    }
}

