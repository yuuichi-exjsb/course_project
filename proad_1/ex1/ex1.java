public class Combination {
  public static void main(String[] args) {
    for (int n=1; n<11; n++){
      for (int k=1; k<n+1; k++) {
         System.out.println("C("+n+","+k+")"+"="+ Combination_math(n,k));
      }
    }
  }
  //階乗を計算する関数
  private static int kaijo(int n){
    int sum = 1;
    for(int i =1;i<n+1;i++){
        sum *= i;
    }
    return sum;

  }
  //組み合わせを計算する関数,n!/k!*(n-k)!
  private static int Combination_math(int n, int k){
    int permi = kaijo(n);
    int kai = kaijo(k);
    int dis = kaijo(n-k);



    int result = permi/(kai*dis);
    return result;
  }
}