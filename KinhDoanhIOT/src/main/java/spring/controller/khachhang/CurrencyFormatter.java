package spring.controller.khachhang;

import java.text.DecimalFormat;

public class CurrencyFormatter {

   public static String format(double amount) {
      DecimalFormat formatter = new DecimalFormat("###,###,### VNĐ");
      return formatter.format(amount);
   }
}