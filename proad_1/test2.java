public class HelloWorld2 {
  public static void main(String[] args) {
    System.out.print("Hello 1 ");         // 表示後、改行しない
    System.out.println("Hello 2 ");       // 表示後、改行する
    System.out.println("Hello 3 " + "Hello 4"); // 文字列の連結
    System.out.println("Hello " + 5);     // 数字は自動的に文字列に変換される
    System.out.println("Hello " + (2+4)); // 括弧の中は数式として計算
    System.out.println("Hello " + 2 + 4); 
    System.out.println(""); 
    int i;
    for (i=1; i<4; i++){
      System.out.println("Hello " + (6+i)); 
    }
  }
}