
import java.util.concurrent.Semaphore;
class Thread4 {
 
  // スレッド間で共有される変数
  static int myglobal = 0;

  static Semaphore sem = new Semaphore(20);
 
  // メインスレッドと並行動作するスレッド
  static class MyThread extends Thread {

 
    public void run() {
      for (int i = 0; i < 10; i++) {
         try {
            sem.acquire();
        
        int temp = myglobal;      // myglobal の値を取得

        
        temp = temp + 1;
        
        System.out.print("(A)");  // "(A)" を表示
    
    
 
        try {
          Thread.sleep((int)(Math.random() * 100));      // 0-100ms スリープ
        } catch (InterruptedException e) {
          System.out.println(e);
        }
 
        myglobal = temp;          // myglobal を1つ増やす


        }
        
        catch (InterruptedException e) {
            System.out.println(e);
        }
        sem.release();
       
      }
    }
  }
 
 
  public static void main(String args[]) {
 
    // 新しいスレッドを生成
    MyThread th = new MyThread();
    th.start();   // 実行開始
 
    for (int i = 0; i < 10; i++) {
         try {
            sem.acquire();
        
      myglobal = myglobal + 1;     // myglobal を1つ増やす
      
      } 
     
      catch (InterruptedException e) {
            System.out.println(e);
        }
        sem.release();

     
      
     
      System.out.print("(M)");     // "(M)" を表示
      
      
 
      try {
        Thread.sleep((int)(Math.random() * 100));         // 0-100ms スリープ
      } catch (InterruptedException e) {
        System.out.println(e);
      }
     
    }

    
    try {
      th.join();                  // th の終了を待つ
    } catch (InterruptedException e) {
      System.out.println(e);
    }

 
    // myglobal の値を表示
    System.out.println("\n myglobal = " + myglobal);
  }
}