package fpoly.hailxph49396.duan1_quanlybanhang.DTO;

public class TaiKhoanDTO {
    private  String so_dien_thoai;
    private String username;
    private String password;

    public TaiKhoanDTO() {
    }
    public TaiKhoanDTO(String so_dien_thoai, String username, String password) {
        this.so_dien_thoai = so_dien_thoai;
        this.username = username;
        this.password = password;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
