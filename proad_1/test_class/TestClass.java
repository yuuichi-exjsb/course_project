/** クラスに関するサンプルプログラム その１
    Teki クラスを利用する TestClass クラス
*/

class TestClass {
  public static void main(String[] args) {
    Teki teki01, teki02;  // Teki クラス型で変数を２つ宣言
    teki01 = new Teki();  // Teki クラス型で初期化しオブジェクトを作成
                          //（メモリ空間の確保、コンストラクタの実行）
    teki02 = new Teki();  // Teki クラス型で初期化しオブジェクトを作成
                          //（メモリ空間の確保、コンストラクタの実行）

    for (int i=1; i<100; i++) {
      System.out.print("敵１の位置： ");
      teki01.dispXY();   // Teki01 の位置を確認
      System.out.print("　敵２の位置： ");
      teki02.dispXY();   // Teki02 の位置を確認
      System.out.println(""); // 改行
      teki01.update();   // Teki01 の状態を更新
      teki02.update();   // Teki02 の状態を更新
    }
  }
}