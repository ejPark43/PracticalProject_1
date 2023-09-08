package com.mycom.word;
import java.util.Scanner;
//WordCRUD를 이용해서 CURD 기능을 구현 
public class WordManager {
Scanner s = new Scanner(System.in);
WordCRUD wordCRUD;
	
WordManager(){
	wordCRUD = new WordCRUD(s);
}

	public int selectMenu() {
		System.out.print("***** 영단어 마스터 *****\n"
				+ "====================== \n"
				+ "1. 모든 단어 보기\n"
				+ "2. 수준별 단어 보기 \n"
				+ "3. 단어 검색 \n"
				+ "4. 단어 추가 \n"
				+ "5. 단어 수정 \n"
				+ "6. 단어 삭제 \n"
				+ "7. 파일 저장 \n"
				+ "0. 나가기 \n"
				+ "====================== \n"
				+ "=> 원하는 메뉴는? ");
		return s.nextInt();
	}
	
	public void start() {
		
		wordCRUD.loadFile(); //파일 불러옴 (while 문 안에서 loadFile 호출하면 계속 반복되기 때문에 시작하자마자 로드함.)
		
		while(true) {
			int menu = 	selectMenu();
			if(menu == 0) break;
			if(menu == 1) { // show list 
				wordCRUD.listAll();
			}
			else if(menu == 2) { // list words by levels
				wordCRUD.searchLevel();
				}
			else if(menu == 3) { // serach word 
				  wordCRUD.searchWord();
				}
			else if(menu == 4) { // add word 
			  wordCRUD.addItem();
			}
			else if(menu == 5) { // update 
				wordCRUD.updateItem();
				}
			else if(menu == 6) { // delete
				wordCRUD.deleteItem();
				}
			else if(menu == 7) { // save data
				wordCRUD.saveFile();
				}
		
		}
	}
	
}
