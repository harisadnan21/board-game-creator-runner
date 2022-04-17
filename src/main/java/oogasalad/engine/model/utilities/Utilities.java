package oogasalad.engine.model.utilities;

public class Utilities {

  public static boolean isPositive(int num){
    return num > 0;
  }

  public static boolean isNegative(int num){
    return num < 0;
  }

  public static boolean isNonNegative(int num){
    return num >= 0;
  }

  public static boolean isNonPositive(int num){
    return num <= 0;
  }

}
