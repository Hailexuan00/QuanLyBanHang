package fpoly.hailxph49396.duan1_quanlybanhang.ShowMoney;

import java.text.NumberFormat;
import java.util.Locale;

public class ShowMoney {

    public static String formatCurrency(int amount) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return formatter.format(amount);
    }
}
