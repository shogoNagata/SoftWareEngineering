package db_create;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class fileTest{
	  public static void main(String args[]){
		          try{
		              //出力先ファイルのFileオブジェクトを作成
		              File file = new File("c:\\tmp\\1min.txt");

		              //FileWriterオブジェクトを作成（追記モード）
		              FileWriter fw = new FileWriter(file, true);

		              //BufferedWriterオブジェクトを作成
		              BufferedWriter bw = new BufferedWriter(fw);
		              
		              //PrintWriterオブジェクトを作成
		              PrintWriter pw = new PrintWriter(bw);
		              
		              //乱数のオブジェクトを作成
		              Random rnd1 = new Random(100);
		              
		              //混雑状況に使う変数の定義
		              double c = 0;
		              //エリアコードに使う変数の定義
		              int b = 1;
		              
		              //時間を回数でカウントする変数の定義
		              int z = 1;
		              
		              //時間を作成
		              int a1 = 11;  //時を表す変数の定義 
		              int a2 = 0;   //分を表す変数の定義
		              for(int i = 11; i <=21; i++){            //時のfor文
		            	  for(int j = 0; j <= 59; j++) {       //分のfor文
		            			for(int k = 0; k <= 6; k++) {  //エリアコードのfor文
		            				//乱数を生成
		            				int ran1 = rnd1.nextInt(2);
		            				//混雑状況を時間ごとに上下させるif文
		            				if(z <= 60 && b >= 7) {
		            					c += 0.25;
		            				}else if(z >= 61  && z <= 100 && b >= 7){
		            					c += 4;
		            				}else if(z >= 101 && z <= 120 && b >= 7){
		            					c -= 3;
		            				}else if(z >= 121 && z <= 300 && b >= 7){
		            					c -= 0.5;
		            				}else if(z >= 301 && z <= 420 && b >= 7){
		            					c += 0.25;
		            				}else if(z >= 421 && z <= 465 && b >= 7){
		            					c += 1;
		            				}else if(z >= 466 && z <= 540 && b >= 7){
		            					c -= 1.5;
		            				}else if(z >= 540 && b >= 7){
		            					c -= 0.75;
		            				}
		            				//エリアコードが1の場合
		            				if(b < 2) {
		            					//c(混雑状況)が0に近くなった場合
		            					if(c <= 9) {
		            						c = 9;
		            					}
		            					//c(混雑状況)が100に近くなった場合
		            					if(c >= 98) {
		            						c = 98;
		            					}
		            					b = 1;
		            					c += ran1-0.5;
		            					int c2 = (int)c;
		            					if(a2 < 10){
		            						pw.println(""+a1 + "0" + a2 + "," + b + "," + c2);
		            					}else{
		            						pw.println(""+a1 + a2 + ","  + b + "," + c2);
		            					}
		            					b++;
		            				//エリアコードが1以外の場合
		            				}else if(b >= 2 && b <= 6){
		            					//c(混雑状況)が0になった場合
		            					if(c <= 9) {
		            						c = 9;
		            					}
		            					//c(混雑状況)が100に近くなった場合
		            					if(c >= 98) {
		            						c = 98;
		            					}
		            					c += ran1-0.5;
		            					int c2 = (int)c;
		            					//a2(時刻)が一桁の場合
		            					if(a2 < 10){
		            						pw.println(""+a1 + "0" + a2 + "," + b + "," + c2);
		            					}else{
		            						pw.println(""+a1 + a2 + "," + b + "," + c2);
		            					}
		            					b++;
		            				}else if(b >= 7) {
		            					b = 1;
		            				}
		            			}
		            			z++;
		            			a2++;
		            			if(a2 > 59) {
		            				a2 = 00;
		            			}
		            		} 
		            	  	a1++;
		              }
		              //FileWriterオブジェクトをクローズ
		              pw.close();

		          } catch(IOException e) {
		              System.out.println(e);
		          }
	  }
}