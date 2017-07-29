package strman;
import java.text.DecimalFormat;
public class test {

  public static boolean debugMode = true;



    public static class NumberFormatOptions {

      private int precision;
      private String thousandsSeparator;
      private String decimalPoint;


      // Constructor
      public NumberFormatOptions(int prec, String tSep, String dPoint) {
        this.precision = prec;
        this.thousandsSeparator = tSep;
        this.decimalPoint = dPoint;
      }



      // getters
      public int getPrecision() {
        return this.precision;
      }

      public String getSeparator() {
        return this.thousandsSeparator;
      }

      public String getDecimalPoint() {
        return this.decimalPoint;
      }

    }




  public static String formatPrefix(String s){
    String rVal = s;
    int length = s.length();


     if(length > 3){
        for(int i = length - 3; i > 0; i -= 3) {
          String sub1 = rVal.substring(0, i); // get digits before comma to be inserted
          String sub2 = rVal.substring(i);             // get the rest of number
	  rVal = sub1 + "," + sub2;
	}
	return rVal;
      }
      else{
	return s;
      }
}



public static String formatNumber(double number) {

  String retVal = null;
  String numToStr = Double.toString(number);
  int splitIndex = numToStr.indexOf('.');
  String end = numToStr.substring(splitIndex+1);
  if(end.startsWith("0") == false || end.length() > 1) {
    String pre = numToStr.substring(0, splitIndex);
    String post = numToStr.substring(splitIndex+1);
    retVal = formatPrefix(pre) + "." + post;
    return retVal;
  } else {
    retVal = formatPrefix(numToStr.substring(0, splitIndex));
    return retVal;
  }

} 



public static String formatNumber(double number, NumberFormatOptions options) {

      String accumulator = "";
      // round to options.precision
      // double toRound = Double.parseDouble(formatted);// call formatNumber after as this line doesnt deal with commas
      for(int i = 0; i < options.getPrecision(); ++i) {
        accumulator = accumulator.concat("#");
      }
      String pattern = "#." + accumulator;
      debugPrint(accumulator);
      DecimalFormat df = new DecimalFormat(pattern);
      String s1 = df.format(number);
      String s2 = formatNumber(Double.parseDouble(s1));
      String s3 = s2.replaceAll(",", "+");
      // issue below
      //System.out.println(options.getSeparator());
      String s4 = s3.replaceAll(".", options.getDecimalPoint());
     // String s5 = s4.replaceAll("+", options.getSeparator());
      System.out.println(s4);

      // formatNumber()
      // replace ',' '.' etc.

      return null;

    }


public static void debugPrint(Object o) {
  if (debugMode == true) {
    System.out.println(o.toString());
  }
}



public static void main(String[] args) {

 NumberFormatOptions nfo = new NumberFormatOptions(2, ",", ".");
 double l = 1234.789;
 double d = 1234789;
 String output = formatNumber(d);
 System.out.println(formatNumber(1234.000)); 
 System.out.println(formatNumber(1234.003));
 System.out.println(formatNumber(l));
 System.out.println(formatNumber(d));
 return;

}




}
