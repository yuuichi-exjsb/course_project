class Kadai02 {
    public static void main(String[] args) {

    Teki[] teki = new Teki[5]; // Teki 型の配列の宣言および作成（配列を new）
    for (int i=0; i < teki.length; i++){
        teki[i] = new Teki();    // 配列の中身（各オブジェクト）を new
        }
    for (int i=1; i<100; i++) {
        System.out.println("========"+i+"時間"+"=========");
        for (int j=0; j<teki.length; j++){
        int x = j+1; 
        System.out.print("敵"+x+"の位置:");
        teki[j].dispXY();
        System.out.println("");

        }
        for (int k=0; k<teki.length; k++){
            teki[k].update();

        }

    }
  }   

}