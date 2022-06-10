import java.util.*;

public class Main {
    public static void main(String[] args) /*throws Exception*/ {
        calc("10 + 20");
        calc("10 * 20");
        calc("20 - 13");
        calc("20 / 4");

        // 演算子の優先順位は考慮しない計算
        calc2("11 + 210");
        calc2("10 + 20 - 5");
        calc2("1 + 20 + 30 - 40 * 12 / 4");
    }

    // 質問者さんオリジナルのコードの延長(割り算'/'を追加)
    public static void calc(String str) {
        String str0 = str.replaceAll(" ",""); //スペースの削除
        String num1 = "";
        char ope = 0;

        int value1 = -1;
        int value2 = -1;

        for(int i = 0; i < str0.length() ;i++){
            //一文字ずつ評価していく　数字or演算
            //連続で数字→結合　　　、演算子が決まる→次の処理に行く

            if(Character.isDigit(str0.charAt(i))){   
                //ここの処理が不明
                //連続した数字を結合してnum1等に代入？
                num1 += str0.charAt(i);

            } else {
                // 数文字でなければ演算子のはず
                value1 = Integer.parseInt(num1);
                ope = str0.charAt(i);
                num1 = "";
            }
        }

        value2 = Integer.parseInt(num1);
        int result = 0;
        if (ope == '+') {
            result = value1 + value2;
        } else if (ope == '-') {
            result = value1 - value2;
        } else if (ope == '*') {
            result = value1 * value2;
        } else if (ope == '/') {
            result = value1 / value2;
        }

        // int result = value.parseInt(/*不明*/) + s + value.parseInt(/*不明*/);
        System.out.println("calc(): result=" + result);  
    }

    // 演算子の優先順位を考慮しない四則演算
    public static void calc2(String str) {
        String ope = "";
        String sv1 = "";
        int result = 0;

        // スペース(" ")を区切りとしてトークン分割
        String[] tokens = str.split(" ", -1);
        //for (String token: tokens) {
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (sv1.isEmpty()) {
                // 1つ目の数文字列であれば、まだ計算しない
                sv1 = token;
            } else if (ope.isEmpty()) {
                // 演算子であれば、1つ目の数文字列は存在することになるが、まだ計算しない
                ope = token;
            } else {
                // 1つ目の数文字列、演算子は確定しているので、計算できる。
                int value1 = Integer.parseInt(sv1);
                int value2 = Integer.parseInt(token);
                if (ope.equals("+")) {
                    result = value1 + value2;
                } else if (ope.equals("-")) {
                    result = value1 - value2;
                } else if (ope.equals("*")) {
                    result = value1 * value2;
                } else if (ope.equals("/")) {
                    result = value1 / value2;
                }

                ope = "";
                // 現在の答えを次の1つ目の数文字列として扱うことで、連続した計算を可能にする
                sv1 = String.valueOf(result);
            }
        }

        String s = String.format("calc2(): %s = %d", str, result);  
        System.out.println(s);
    }
}