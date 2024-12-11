package fpoly.hailxph49396.duan1_quanlybanhang.DAO;

import static fpoly.hailxph49396.duan1_quanlybanhang.Database.DbHelper.TABLE_CHI_TIET_DON_HANG;
import static fpoly.hailxph49396.duan1_quanlybanhang.Database.DbHelper.TABLE_DON_HANG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import fpoly.hailxph49396.duan1_quanlybanhang.DTO.DanhMucDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.Database.DbHelper;
public class DanhMucDAO {

    private final DbHelper dbHelper;

    public DanhMucDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    // Lấy danh sách danh mục
    @SuppressLint("Range")
    public ArrayList<DanhMucDTO> getListDanhMuc() {
        ArrayList<DanhMucDTO> list = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM DanhMuc", null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    DanhMucDTO danhMuc = new DanhMucDTO();
                    danhMuc.setMaDanhMuc(cursor.getInt(cursor.getColumnIndex("id_danh_muc")));
                    danhMuc.setTenDanhMuc(cursor.getString(cursor.getColumnIndex("ten_danh_muc")));
                    list.add(danhMuc);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DanhMuc", "Error: " + e);
        } finally {
            if (cursor != null) cursor.close();
            if (db != null && db.isOpen()) db.close();
        }

        return list;
    }

    // Thêm danh mục mới
    public long addDanhMuc(DanhMucDTO danhMuc) {
        SQLiteDatabase db = null;
        long result = -1;

        try {
            db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("ten_danh_muc", danhMuc.getTenDanhMuc());

            result = db.insert("DanhMuc", null, values);
        } catch (Exception e) {
            Log.e("DanhMuc", "Error: " + e);
        } finally {
            if (db != null && db.isOpen()) db.close();
        }

        return result;
    }

    // Cập nhật danh mục
    public int updateDanhMuc(DanhMucDTO danhMuc) {
        SQLiteDatabase db = null;
        int result = -1;

        try {
            db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("ten_danh_muc", danhMuc.getTenDanhMuc());

            result = db.update("DanhMuc", values, "id_danh_muc = ?", new String[]{String.valueOf(danhMuc.getMaDanhMuc())});
        } catch (Exception e) {
            Log.e("DanhMuc", "Error: " + e);
        } finally {
            if (db != null && db.isOpen()) db.close();
        }

        return result;
    }

    // Xóa danh mục
    public int deleteDanhMuc(int maDanhMuc) {
        SQLiteDatabase db = null;
        int result = -1;

        try {
            db = dbHelper.getWritableDatabase();
            result = db.delete("DanhMuc", "id_danh_muc = ?", new String[]{String.valueOf(maDanhMuc)});
        } catch (Exception e) {
            Log.e("DanhMuc", "Error: " + e);
        } finally {
            if (db != null && db.isOpen()) db.close();
        }

        return result;
    }

}
