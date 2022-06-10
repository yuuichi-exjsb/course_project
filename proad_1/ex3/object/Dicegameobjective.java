/**
 * オブジェクト指向によるサイコロ転がしゲーム
 * JankenObjective は main メソッドのみを持ち，
 * プログラムのスタートと各クラスのインスタンスの作成を担う
 */

public class Dicegameobjective{
  public static void main(String[] args) {
    // 審判（佐藤さん）のインスタンス生成
    // 設計図（クラス）は Judge.class
    Judge sato = new Judge();

    
    //5人のplayerを作成した
    Player player_a = new Player("Aさん");

    Player player_b = new Player("Bさん");
    Player player_c = new Player("Cさん");
    Player player_d = new Player("Dさん");
    Player player_e = new Player("Eさん");

    //五人をPlayerクラス型配列に格納する
    Player[] player = {player_a,player_b,player_c,player_d,player_e};

    //この配列を渡して、ゲームを開始する。
    // Judge クラスの startJanken メソッド使用
    sato.startDiceGame(player);
  }
}