package fpoly.hailxph49396.duan1_quanlybanhang.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fpoly.hailxph49396.duan1_quanlybanhang.DTO.DonHangDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.Database.DbHelper;

public class DonHangDAO {
    private final DbHelper dbHelper;
    DonHangDTO donHangDTO;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public DonHangDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    @SuppressLint("Range")
    public ArrayList<DonHangDTO> getListDonHang() {
        ArrayList<DonHangDTO> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM DonHang", null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    Date ngay = null;
                    try {
                        String ngayString = cursor.getString(cursor.getColumnIndex("ngay"));
                        if (ngayString != null) {
                            ngay = sdf.parse(ngayString);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    DonHangDTO donHang = new DonHangDTO(
                            cursor.getInt(cursor.getColumnIndex("id_don_hang")),
                            cursor.getString(cursor.getColumnIndex("username")),
                            cursor.getString(cursor.getColumnIndex("so_dien_thoai_kh")),
                            cursor.getInt(cursor.getColumnIndex("thanh_tien")),
                            ngay,
                            cursor.getInt(cursor.getColumnIndex("gio")),
                            cursor.getString(cursor.getColumnIndex("ghi_chu")),
                            cursor.getInt(cursor.getColumnIndex("trang_thai"))
                    );

                    list.add(donHang);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DonHang", "Error: " + e);
        } finally {
            if (cursor != null) cursor.close();
            if (db != null && db.isOpen()) db.close();
        }

        return list;
    }

    public long addDonHang(DonHangDTO donHang) {
        SQLiteDatabase db = null;
        long result = -1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("username", donHang.getUsername());
            values.put("so_dien_thoai_kh", donHang.getSoDienThoaiKH());
            values.put("thanh_tien", donHang.getThanhTien());
            values.put("ngay", sdf.format(donHang.getNgay()));
            values.put("gio", donHang.getGio());
            values.put("ghi_chu", donHang.getGhiChu());
            values.put("trang_thai", donHang.getTrangThai());

            result = db.insert("DonHang", null, values);
        } catch (Exception e) {
            Log.e("DonHang", "Error: " + e);
        } finally {
            if (db != null && db.isOpen()) db.close();
        }

        return result;
    }

    @SuppressLint("Range")
    public ArrayList<DonHangDTO> getListDonHangToday() {
        ArrayList<DonHangDTO> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());

        try {
            db = dbHelper.getReadableDatabase();
            String sql = "SELECT * FROM DonHang WHERE ngay = ?";
            cursor = db.rawQuery(sql, new String[]{today});

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    Date ngay = sdf.parse(cursor.getString(cursor.getColumnIndex("ngay")));

                    DonHangDTO donHang = new DonHangDTO(
                            cursor.getInt(cursor.getColumnIndex("id_don_hang")),
                            cursor.getString(cursor.getColumnIndex("username")),
                            cursor.getString(cursor.getColumnIndex("so_dien_thoai_kh")),
                            cursor.getInt(cursor.getColumnIndex("thanh_tien")),
                            ngay,
                            cursor.getInt(cursor.getColumnIndex("gio")),
                            cursor.getString(cursor.getColumnIndex("ghi_chu")),
                            cursor.getInt(cursor.getColumnIndex("trang_thai"))
                    );

                    list.add(donHang);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DonHang", "Error: " + e.toString());
        } finally {
            if (cursor != null) cursor.close();
            if (db != null && db.isOpen()) db.close();
        }

        return list;
    }

    public int updateTrangThaiDonHang(int maDonHang, int trangThaiMoi) {
        SQLiteDatabase db = null;
        int result = -1;

        try {
            db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("trang_thai", trangThaiMoi);

            result = db.update("DonHang", values, "id_don_hang = ?", new String[]{String.valueOf(maDonHang)});
        } catch (Exception e) {
            Log.e("DonHang", "Error: " + e);
        } finally {
            if (db != null && db.isOpen()) db.close();
        }

        return result;
    }

//    public ArrayList<DonHangDTO> getListDonHang() {
//        ArrayList<DonHangDTO> list = new ArrayList<>();
//        SQLiteDatabase db = null;
//        Cursor cursor = null;
//        try {
//            db = dbHelper.getReadableDatabase();
//            cursor = db.rawQuery("SELECT * FROM DonHang", null);
//            if (cursor != null && cursor.getCount() > 0) {
//                cursor.moveToFirst();
//                do {
//                    list.add(new DonHangDTO(cursor.getInt(0),
//                            cursor.getString(1),
//                            cursor.getString(2),
//                            cursor.getString(3),
//                            cursor.getString(4),
//                            cursor.getString(5)));
//                } while (cursor.moveToNext());
//            }
//        } catch (Exception e) {
//            Log.e("DonHang", e.toString());
//        } finally {
//        if (cursor != null) {
//            cursor.close();
//        }
//        if (db != null && db.isOpen()) {
//            db.close();
//        }
//    }
//        return list;
//    }
}
