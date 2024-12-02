package fpoly.hailxph49396.duan1_quanlybanhang.DTO;

public class ChiTietDonHangDTO {
    private int idChiTietDonHang;
    private int idDonHang;
    private int idSanPham;
    private int soLuong;

    // Constructor
    public ChiTietDonHangDTO(int idChiTietDonHang, int idDonHang, int idSanPham, int soLuong) {
        this.idChiTietDonHang = idChiTietDonHang;
        this.idDonHang = idDonHang;
        this.idSanPham = idSanPham;
        this.soLuong = soLuong;
    }

    public ChiTietDonHangDTO() {

    }

    // Getter and Setter
    public int getIdChiTietDonHang() {
        return idChiTietDonHang;
    }

    public void setIdChiTietDonHang(int idChiTietDonHang) {
        this.idChiTietDonHang = idChiTietDonHang;
    }

    public int getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(int idDonHang) {
        this.idDonHang = idDonHang;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}

