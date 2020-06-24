package tw.com.phctw.util;

import java.util.Random;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class SNameCreate {
	private static String[] lastName ={
			"李","王","張","劉","陳","楊","黃","趙","周","吳",
			"徐","孫","朱","馬","胡","郭","林","何","高","梁",
			"鄭","羅","宋","謝","唐","韓","曹","許","鄧","蕭",
			"馮","曾","程","蔡","彭","潘","袁","於","董","餘",
			"蘇","葉","呂","魏","蔣","田","杜","丁","沈","姜",
			"範","江","傅","鐘","盧","汪","戴","崔","任","陸",
			"廖","姚","方","金","邱","夏","譚","韋","賈","鄒",
			"石","熊","孟","秦","閻","薛","侯","雷","白","龍",
			"段","郝","孔","邵","史","毛","常","萬","顧","賴",
			"武","康","賀","嚴","尹","錢","施","牛","洪","龔"};

	private static String[] firstName ={
			"A","B","C","D","E"
	};

	public String creatName() {
		Random r = new Random();
		return (lastName[r.nextInt(lastName.length)] + firstName[r.nextInt(firstName.length)]);
	}
	
	public static void main(String[] args) {
		//
//		Random r = new Random();
//		//System.out.println(r.nextInt(101));
//		int i =0;
//		while(i<=5) {
//			i++;
//			System.out.println(new SNameCreate().creatName());
//			
//		}
		
	}

}
