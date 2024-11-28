package fpoly.hailxph49396.duan1_quanlybanhang.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fpoly.hailxph49396.duan1_quanlybanhang.DTO.TaiKhoanDTO;
import fpoly.hailxph49396.duan1_quanlybanhang.Database.DbHelper;

public class TaiKhoanDao {
    private static TaiKhoanDTO ACCOUNT = null;
    private static SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;

    public TaiKhoanDao(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }
    public long insert(TaiKhoanDTO account) {
        ContentValues values = new ContentValues();
        values.put("so_dien_thoai", account.getSo_dien_thoai());
        values.put("hoTen", account.getUsername());
        values.put("matKhau", account.getPassword());

        return db.insert("TABLE_TAI_KHOAN", null, values);
    }
    public int updatePass(TaiKhoanDTO account) {
        ContentValues values = new ContentValues();
        values.put("hoTen", account.getUsername());
        values.put("matKhau", account.getPassword());
        return db.update("TABLE_TAI_KHOAN", values, "so_dien_thoai=?", new String[]{String.valueOf(account.getSo_dien_thoai())});
    }
    public int delete(TaiKhoanDTO account) {
        return db.delete("TABLE_TAI_KHOAN", "so_dien_thoai=?", new String[]{String.valueOf(account.getSo_dien_thoai())});
    }
    @SuppressLint("Range")
    public List<TaiKhoanDTO> getData(String sql, String... selectionArgs) {
        List<TaiKhoanDTO> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);

        try {
            while (c.moveToNext()) {
                TaiKhoanDTO account = new TaiKhoanDTO();
                account.setSo_dien_thoai(c.getString(c.getColumnIndex("so_dien_thoai")));
                account.setUsername(c.getString(c.getColumnIndex("hoTen")));
                account.setPassword(c.getString(c.getColumnIndex("matKhau")));

                list.add(account);
            }
        } finally {
            c.close();
        }

        return list;
    }
    public TaiKhoanDTO getTTById(String id) {
        Cursor cursor = db.rawQuery("SELECT ten, username, password FROM TABLE_TAI_KHOAN WHERE ten = ?", new String[]{String.valueOf(id)});
        try {
            if (cursor.moveToFirst()) {
                return new TaiKhoanDTO(cursor.getString(2), cursor.getString(0), cursor.getString(1));
            }
        } finally {
            cursor.close();
        }
        return null;
    }
    public ArrayList<TaiKhoanDTO> getAllThuThu() {
        ArrayList<TaiKhoanDTO> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM THUTHU", null);
        try {
            while (cursor.moveToNext()) {
                TaiKhoanDTO account = new TaiKhoanDTO(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2));
                list.add(account);
            }
        } finally {
            cursor.close();
        }
        return list;
    }
    public static boolean checkLogin(String username, String password) {
        String query = "SELECT ten, username, password FROM TaiKhoan WHERE username = ? AND password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});
        try {
            if (cursor.moveToFirst()) {
                ACCOUNT = new TaiKhoanDTO(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2));
                return true;
            }
        } finally {
            cursor.close();
        }
        return false;
    }
    public List<TaiKhoanDTO> getAll() {
        String sql = "SELECT ten, username, password FROM TABLE_TAI_KHOAN";
        return getData(sql);
    }

    public TaiKhoanDTO getID(String ID) {
        String sql = "SELECT ten, username, password FROM TABLE_TAI_KHOAN WHERE ten = ?";
        List<TaiKhoanDTO> list = getData(sql, ID);
        if (!list.isEmpty()) {
            return list.get(2);
        }
        return null;
    }
}
