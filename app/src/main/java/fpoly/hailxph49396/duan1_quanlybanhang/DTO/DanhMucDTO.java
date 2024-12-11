package fpoly.hailxph49396.duan1_quanlybanhang.DTO;

import androidx.annotation.NonNull;

public class DanhMucDTO {
    int maDanhMuc;
    String tenDanhMuc;

    public DanhMucDTO() {

    }

    public DanhMucDTO(int maDanhMuc, String tenDanhMuc) {
        this.maDanhMuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
    }



    public int getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    @NonNull
    @Override
    public String toString() {
        return tenDanhMuc;
    }
}
