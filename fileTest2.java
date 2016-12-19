package db_create;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class fileTest2 {
	public static void main(String args[]){
        try{
            //出力先ファイルのFileオブジェクトを作成
            File file = new File("c:\\tmp\\30min.txt");

            //FileWriterオブジェクトを作成（追記モード）
            FileWriter fw = new FileWriter(file, true);

            //BufferedWriterオブジェクトを作成
            BufferedWriter bw = new BufferedWriter(fw);

            //PrintWriterオブジェクトを作成
            PrintWriter pw = new PrintWriter(bw);

            //乱数のオブジェクトを作成
            Random rnd1 = new Random(100);

            //混雑状況に使う変数の定義
            int c = 0;
            //エリアコードに使う変数の定義
            int b = 1;

            String yobi = "金"; //曜日を表す変数
            int count = 1; //5日(月～金)経ったら2日(土日)飛ばすためのカウント
            int zikan = 1100; //時刻を表す変数
            
            for(int i = 2011; i <=2015; i++){      //年(5年分)の繰り返す回数のfor文
          	  for(int j = 1; j <= 12; j++) {       //月の繰り返す回数のfor文
          		  for(int n = 1; n <=30; n++){     //日の繰り返す回数のfor文
          			  if(yobi == "月"){
          				  yobi = "火";
          			  }else if(yobi == "火") {
          				  yobi = "水";
          			  }else if(yobi == "水") {
          				  yobi = "木";
          			  }else if(yobi == "木") {
          				  yobi = "金";
          			  }else if(yobi == "金") {
          				  yobi = "月";
          			  }
          			  for(int m = 1; m <= 22; m++) { //11時00分、11時30分…21時30分、22時00分の繰り返す回数(計22回)のfor文
          				  //pw.println(count0);
          				  //乱数を生成
      					  int ran1 = rnd1.nextInt(2);
      					  
      					  //混雑状況を時間ごとに上下させるif文
      					  if(m == 1) {   //11:00^11:30
      						  c = 15;
      					  }else if(m == 2){  //11:30^12:00
      						  c += 10;
      					  }else if(m == 3) {  //12:00^12:30
      						  c += 70;
      					  }else if(m == 4 || m == 5){ //12:30^13:30
      						  c -= 25;
      					  }else if(m >= 6 && m <= 10){ //13:30^16:00
      						  c -= 10;
      					  }else if(m >= 11 && m <= 14){  //16:00^18:00
      						  c += 5;
      					  }else if(m == 15){  //18:00^18:30
      						  c += 55;
      					  }else if(m == 16){  //18:30^19:00
      						  c = c;
      					  }else if(m >= 17){  //19:00^22:00
      						  c -= 25;
      					  }
          				  for(int k = 0; k <= 6; k++) {  //エリアコードの繰り返す回数のfor文
          					  
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
          						  c += ran1;
          						  //月と日が一桁の場合とそうでない場合のif文
          						  if(j < 10 && n < 10){
          							  pw.println(i + "0" + j + "0" + n + zikan + "," + b + "," + yobi + "," + c);
          						  }else if(j < 10){
          							  pw.println(i + "0" + "" + j + n + zikan + "," + b + "," + yobi + "," + c);
          						  }else if(n < 10) {
          							  pw.println("" + i + j + "0" + n + zikan + "," + b + "," + yobi + "," + c);
          						  }else{
          							  pw.println("" + i + j + n + zikan + "," + b + "," + yobi + "," + c);
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
          						  //a2(時刻)が一桁の場合
          					
          						  if(j < 10 && n < 10){
          							  pw.println(i + "0" + j + "0" + n + zikan + "," + b + "," + yobi + "," + c);
          						  }else if(j < 10){
          							  pw.println(i + "0" + "" + j + n + zikan + "," + b + "," + yobi + "," + c);
          						  }else if(n < 10) {
          							  pw.println("" + i + j + "0" + n + zikan + "," + b + "," + yobi + "," + c);
          						  }else{
          							  pw.println("" + i + j + n + zikan + "," + b + "," + yobi + "," + c);
          						  }
          						  b++;
          					  }else if(b >= 7) {
          						  b = 1;
          					  }
          				  }
          				  //時間を更新する
          			  	  if(zikan == 1100) {
      						  zikan = 1130;
      					  }else if(zikan == 1130) {
      						  zikan = 1200;
      					  }else if(zikan == 1200) {
      						  zikan = 1230;
      					  }else if(zikan == 1230) {
      						  zikan = 1300;
      					  }else if(zikan == 1300) {
      						  zikan = 1330;
      					  }else if(zikan == 1330) {
      						  zikan = 1400;
      					  }else if(zikan == 1400) {
      						  zikan = 1430;
      					  }else if(zikan == 1430) {
      						  zikan = 1500;
      					  }else if(zikan == 1500) {
      						  zikan = 1530;
      					  }else if(zikan == 1530) {
      						  zikan = 1600;
      					  }else if(zikan == 1600) {
      						  zikan = 1630;
      					  }else if(zikan == 1630) {
      						  zikan = 1700;
      					  }else if(zikan == 1700) {
      						  zikan = 1730;
      					  }else if(zikan == 1730) {
      						  zikan = 1800;
      					  }else if(zikan == 1800) {
      						  zikan = 1830;
      					  }else if(zikan == 1830) {
      						  zikan = 1900;
      					  }else if(zikan == 1900) {
      						  zikan = 1930;
      					  }else if(zikan == 1930) {
      						  zikan = 2000;
      					  }else if(zikan == 2000) {
      						  zikan = 2030;
      					  }else if(zikan == 2030) {
      						  zikan = 2100;
      					  }else if(zikan == 2100) {
      						  zikan = 2130;
      					  }else if(zikan == 2130) {
      						  zikan = 1100;
      					  }
          			  }
          			  count++;
          			  if(count == 6) {
          				  count = 1;
          				  n += 2;
          			  }
          		  }
          	  }
            }
            //FileWriterオブジェクトをクローズ
            pw.close();

        } catch(IOException e) {
            System.out.println(e);
        }
}
}
