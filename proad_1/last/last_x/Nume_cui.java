import java.io.*;
 
public class Nume_cui {
 
 final static int answer_size = 2;//当てる数。4にすれば4つの数字を当てるゲームになる。
 static int eat = 0;
 static int bite = 0;
 static int challenge = 1;
 
 //メインメソッド
 public static void main(String[] args) {
 
 printDirection();
 int[] right_answer;
 right_answer = makeRightAnswer();
 
 while(true) {
 int[] user_answer;
 user_answer = readUserAnswer();
 checkAnswer(right_answer, user_answer);
 challenge++;
 if(eat == answer_size) {
 System.out.println("正解です。ゲームを終了します。");
 break;
 }
 }
 }
 
 //説明を出力するメソッド
 public static void printDirection() {
 String direction = "ゲームを開始します。\n"
 + "これから0~9の"+answer_size+"つの数字を当ててもらいます。\n"
 + "同じ数字が使用されていることはありません。\n"
 + "数字と位置が合っていた場合、\"eat\"\n"
 + "数字が合っていて位置が間違っていた場合、\"bite\"とカウントされます。\n\n";
 
 System.out.println(direction);
 }
 
 //ランダムに正解を作成するメソッド
 public static int[] makeRightAnswer() {
 int[] answer = new int[answer_size];
 for(int i = 0; i < answer.length; i++) {
 answer[i] = (int)(Math.random()*10);
 for(int j = i-1; j >=0; j--) {
 if(answer[j] == answer[i]) {
 answer[i] = (int)(Math.random()*10);
 }
 }
 }
 return answer;
 }
 
 //入力された数字を読み込むメソッド
 public static int[] readUserAnswer() {
 eat = 0;
 bite = 0;
 int[] answer = new int[answer_size];
 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
 System.out.println("\n"+challenge+"回目のチャレンジ");
 for(int i = 0; i < answer.length; i++) {
 try {
 System.out.print((i+1)+"つ目の数字:");
 String a = reader.readLine();
 int b = Integer.parseInt(a);
 answer[i] = b;
 }catch (NumberFormatException e) {
 System.out.println("数字を入力してください。");
 i--;
 }catch (IOException e) {
 System.out.println("形式が間違っています。");
 i--;
 }
 }
 return answer;
 }
 
 //解答と照らし合わせるメソッド
 public static void checkAnswer(int[] right_answer,int[] user_answer) {
 for(int i = 0; i < right_answer.length; i++) {
 for(int j = 0; j < user_answer.length; j++) {
 if(right_answer[j] == user_answer[i]) {
 if(j==i) {
 eat++;
 }else {
 bite++;
 }
 }
 }
 }
 System.out.println(eat+" eat  "+bite+" bite");
 }
}