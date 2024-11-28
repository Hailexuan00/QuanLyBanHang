package fpoly.hailxph49396.duan1_quanlybanhang.DTO;

public class NhanVienDTO {
    private int id;
    private String name;
    private String middleName;
    private String gender;
    private String phone;
    private String email;
    private String address;

    public NhanVienDTO(int id, String name, String middleName, String gender, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.middleName = middleName;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
