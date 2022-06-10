/**
 * サイコロゲームの審判のクラス
 *   startDiceGame： ゲームの開始、進行、終了と結果の表示を行うメソッド
 *   judgeDiceRoll： サイコロ振りを１回行う毎に勝負の結果を判定するメソッド

 */

import java.util.Arrays;
public class Judge {
  /*■■■ メンバー変数部 ■■■*/
  /*■■■ コンストラクタ部 ■■■*/  
  /*■■■ メソッド部 ■■■*/  
  /**
   * サイコロゲームを進行するメソッド
   * 
   * @return （無し）
   */
  public void startDiceGame(Player player[]) {
    System.out.println("【サイコロ振りゲーム開始】");


    int cnt = 0;

    int flag = 0;

    while(flag != 1){
      System.out.println("【" + (cnt + 1) + "回戦目】"); // 何回戦目か表示

      // ★ プレイヤーの手を見て、どちらが勝ちかを判定する
      // Player クラスタイプで winner という変数を定義し，
      // 自分自身のクラス (this) に所属する judgeDiceRoll メソッドの戻り値を winner に入れる
      Player winner = this.judgeDiceRoll(player);

      if (winner != null) { // どちらかが勝った場合
        System.out.println("\n" + winner.getName() + "が勝ちました\n"); // 勝者を表示
        winner.notifyResult(true); // 勝ったプレイヤーへ結果を伝える
      } else {
        // 引き分けの場合
        System.out.println("\n引き分けです\n");
      }

      for(int i=0;i<player.length;i++){
        if(player[i].getWinCount() == 3){
          flag = 1;
        }
      cnt++;
      }

      for(int i=0;i<player.length;i++){
        System.out.println(player[i].getWinCount());
      }
    }
    // ★ ジャンケンの終了を宣言する
    System.out.println("【サイコロ振りゲーム終了】");

    // ★ 最終的な勝者を判定する
    Player finalWinner = this.judgeFinalWinner(player);
    System.out.println(finalWinner.getName()+"さんの勝利です。");


  }
  public void startDiceGame(Player player[],int n) {
    System.out.println("【サイコロ振りゲーム開始】");


    int cnt = 0;

    int flag = 0;
    //whileで各プレイヤーの勝ち数をチェックして、勝利条件を誰も満たしていない場合はゲーム続行
    while(flag != 1){
      System.out.println("【" + (cnt + 1) + "回戦目】"); // 何回戦目か表示

      // ★ プレイヤーの手を見て、どちらが勝ちかを判定する
      // Player クラスタイプで winner という変数を定義し，
      // 自分自身のクラス (this) に所属する judgeDiceRoll メソッドの戻り値を winner に入れる
      Player winner = this.judgeDiceRoll(player);

      if (winner != null) { // どちらかが勝った場合
        System.out.println("\n" + winner.getName() + "が勝ちました\n"); // 勝者を表示
        winner.notifyResult(true); // 勝ったプレイヤーへ結果を伝える
      } else {
        // 引き分けの場合
        System.out.println("\n引き分けです\n");
      }

      for(int i=0;i<player.length;i++){
        if(player[i].getWinCount() == n){
          flag = 1;
        }
      cnt++;
      }
    }
    // ★ ジャンケンの終了を宣言する
    System.out.println("【サイコロ振りゲーム終了】");

    // ★ 最終的な勝者を判定する
    Player finalWinner = this.judgeFinalWinner(player);

    // ★ 結果を表示する  ここの結果も変更
    /*System.out.print(player1.getWinCount() + " 対 " + player2.getWinCount() + "で");*/
    if (finalWinner != null) {
        System.out.println(finalWinner.getName() + "の勝ちです\n");
    }
    else {
        System.out.println("引き分けです\n");
    }
  }


  /**
   * 各プレイヤーにサイコロを振らせ、それぞれの手を見てどちらが勝ちかを判定するメソッド
   * 
   * @param player1 判定対象プレイヤー1
   * @param player2 判定対象プレイヤー2
   * @return 勝ったプレイヤー。引き分けの場合は null を返す。
   */
  private Player judgeDiceRoll(Player player[]) {
    Player winner = null;

    int[] playernumber = new int[5];
    /*参加者のサイコロの目の取得*/ 
    for(int i=0;i<player.length;i++){
      playernumber[i] = player[i].rollTheDice();
      System.out.println(player[i].getName()+"のサイコロの目は"+playernumber[i]+"です。");
    }

    int[] playernumber_copy = Arrays.copyOf(playernumber, 5);

    int Max = playernumber[0];
    int Max_loc = 0;

    Arrays.sort(playernumber);

    

    
    if(playernumber[3] != playernumber[4]){
      for(int i=0;i<player.length;i++){
        if(playernumber_copy[i] == playernumber[4]){
          Max_loc = i;
        }
    winner = player[Max_loc];
      }
    }
    return winner;
  }
  private Player judgeFinalWinner(Player player[]) {
    Player winner = null;

    int[] player_win_counts = new int[5];

    for(int i=0;i<player.length;i++){
          player_win_counts[i] = player[i].getWinCount(); 
        }
    
    int[] player_win_counts_copy = Arrays.copyOf(player_win_counts, 5);

    int max_win_loc = 0;

    Arrays.sort(player_win_counts);
    for(int i=0;i<player.length;i++){
        if(player_win_counts[3] != player_win_counts[4]){
          for(int j=0;j<player.length;j++){
            if(player_win_counts_copy[j] == player_win_counts[4] ){
              winner = player[j];
            }
          }
        }
      }
    
    return winner;
  }
}