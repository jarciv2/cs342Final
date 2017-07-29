import java.text.DecimalFormat;
public class FinalTester {

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

      public NumberFormatOptions(int prec) {
        this.precision = prec;
        this.thousandsSeparator = null;
        this.decimalPoint = null;
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




  /**
     * Formats the integer part of a number a helper function for format numbers.
     *
     * @param s A string representation of a number to be formatted and returned as a string
     * @return The formatted string
     */
    public static String formatPrefix(String s) {
        String retVal = s;
        int length = s.length();
        if (length > 3) {
            for (int i = length - 3; i > 0; i -= 3) {
                String sub1 = retVal.substring(0, i);
                String sub2 = retVal.substring(i);
                retVal = sub1 + "," + sub2;
            }
            return retVal;
        } else {
            return s;
        }
    }

    /**
     * Formats numbers by adding a separator commas to a whole number
     *
     * @param number Our number we are formatting
     * @return String  Our number in string form with separators added
     */
    public static String formatNumber(double number) {

        String retVal = null;
        String numToStr = Double.toString(number);
        int splitIndex = numToStr.indexOf('.');
        String end = numToStr.substring(splitIndex + 1);
        if (!end.startsWith("0") || end.length() > 1) {
            String pre = numToStr.substring(0, splitIndex);
            String post = numToStr.substring(splitIndex + 1);
            retVal = formatPrefix(pre) + "." + post;
            return retVal;
        } else {
            retVal = formatPrefix(numToStr.substring(0, splitIndex));
            return retVal;
        }
    }

    /**
     * Formats our number using options decided by NumberFormatOptions
     *
     * @param number Our double that we are formatting
     * @param options The options we will use to format our number from the NumberFormatOptions class
     * @return String Our thousands seperator being used
     */
    public static String formatNumber(double number, NumberFormatOptions options) {

        String accumulator = "";
        for (int i = 0; i < options.getPrecision(); ++i) {
        accumulator = accumulator.concat("#");
        }
        String pattern = "#." + accumulator;
        DecimalFormat df = new DecimalFormat(pattern);
        String s1 = df.format(number);
        String s2 = formatNumber(Double.parseDouble(s1));
        String s3 = s2.replaceAll(",", "+");
        String s4 = s3.replace(".", options.getDecimalPoint());
        String retVal = s4.replaceAll("\\+", options.getSeparator());
        String acc = "";
        debugPrint(retVal);
        if (!retVal.contains(".") && options.getPrecision() > 0) {
            acc = ".";
            for (int i = 0; i < options.getPrecision(); i++) {
                acc = acc.concat("0");
            }
        }
        retVal = retVal + acc;

        return retVal;
    }


public static void debugPrint(Object o) {
  if (debugMode == true) {
    System.out.println(o.toString());
  }
}



public static void main(String[] args) {

 NumberFormatOptions nfo = new NumberFormatOptions(2);
 double l = 1234.789;
 double d = 1234789.9999;
 String output = formatNumber(d);
 System.out.println(formatNumber(1234.000)); 
 System.out.println(formatNumber(1234.003));
 System.out.println(formatNumber(l));
 System.out.println(formatNumber(d, nfo));
 return;

}




}
