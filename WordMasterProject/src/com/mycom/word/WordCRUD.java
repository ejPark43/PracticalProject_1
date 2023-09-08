package com.mycom.word;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//WordCRUD는 ICRUD(인터페이스)를 구현한 것이기 때문에 implements를 써야 함. 
public class WordCRUD implements ICRUD{
	ArrayList<Word> list; // Word 를 타입으로 하는 리스트 생성 
	Scanner s = new Scanner(System.in);
	final String fname = "Dictionary.txt";
	
	WordCRUD(Scanner s){
		list = new ArrayList<>(); // arraylist로 생성 
		this.s = s;
	}
	
	
	/*
	 override란? 
	 inheritance 를 이해해야함. 
	  
	*/
	
	@Override 
	public Object add() { // 사용자에게 입력받는 함수 
		System.out.print("=> 난이도(1,2,3): ");
		int level = s.nextInt();
		s.nextLine();
		System.out.print("추가할 단어: ");
		String word = s.nextLine();
		System.out.print("뜻 입력 : ");
		String meaning = s.nextLine();
		return new Word(0, level, word, meaning);
	}
	
	public void addItem() { // 입력받은 함수를 추가하는 함수 
	Word one = (Word)add();	
	list.add(one);
	System.out.println("새 단어가 단어장에 추가되었습니다. ");
	}

	@Override
	public int update(Object obj) {

		return 0;
	}

	@Override
	public int delete(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void selectOne(int id) {
		// TODO Auto-generated method stub
		
	}
	
//기본 listAll , 모든 내용 출력 
	public void listAll() {
		System.out.println("----------------------------------");
		for(int i = 0; i<list.size(); i++) {
			System.out.print((i+1) + "  ");
			System.out.println(list.get(i).toString());
		}
		System.out.println("----------------------------------");
	}
//키워드를 파라미터로 갖는 listAll, 키워드가 포함된 단어만 출력  
	public ArrayList <Integer> listAll(String keyword) { // ArrayList로 리턴하도록 함, listAll로 overloading  
		ArrayList <Integer> idlist = new ArrayList<>();
		int j=0;
		System.out.println("----------------------------------");
		for(int i = 0; i<list.size(); i++) {
			String word = list.get(i).getWord();
			if(!word.contains(keyword)) continue; // 키워드 포함 안하면 출력 건너뜀 
			System.out.print((j+1) + "  "); // 키워드가 포함된 단어를 세는 변수가 j이므로 j로 단어의 번호를 매김 
			System.out.println(list.get(i).toString());
			idlist.add(i);
			j++; // 반복될때마다 j값 증가 
		}
		System.out.println("----------------------------------");
		return idlist;
	}
	
	public void listAll(int level) { // 위와 같은 이름의 함수, => overloading으로 같은 이름이지만 다른 용도로 사용하도록 함 (레벨에 따라 단어 검색하는 기능)
		
		int j=0;
		System.out.println("----------------------------------");
		for(int i = 0; i<list.size(); i++) {
			int ilevel = list.get(i).getLevel(); // 레벨 가져옴. 
			if(ilevel!=level) continue; // 검색한 레벨과 동일하지 않으면 건너뜀 
			System.out.print((j+1) + "  "); // 검색한 레벨과 같은 단어를 세는 변수가 j이므로 j로 단어의 번호를 매김 
			System.out.println(list.get(i).toString());
			j++; // 반복될때마다 j값 증가 
		}
		System.out.println("----------------------------------");
		
	}
	
	public void updateItem() {
		System.out.print("=> 수정할 단어 검색 : ");
		String keyword = s.next(); 
		ArrayList<Integer> idlist = this.listAll(keyword); // listall 메서드에 keyword를 넘겨주고 받아온 결과를 idlist에 저장. 
		System.out.print("=> 수정할 번호 선택: ");
		int id = s.nextInt();
		s.nextLine(); // 엔터 포함 안되게 처리 
		System.out.print("뜻 입력: ");
		String meaning = s.nextLine(); // 뜻 입력해야하므로 공백도 포함하는 nextLine으로 받음. 
		
		Word word = list.get(idlist.get(id-1));
		word.setMeaning(meaning); // 입력한 뜻으로 변경 
		System.out.println("단어가 수정되었습니다. ");
		
		
	}

	public void deleteItem() {
		System.out.print("=> 삭제할 단어 검색 : ");
		String keyword = s.next(); 
		ArrayList<Integer> idlist = this.listAll(keyword); // listAll에 keyword를 넘겨주고 받아온 결과를 idlist에 저장. 
		System.out.print("=> 삭제할 번호 선택: ");
		int id = s.nextInt();
		s.nextLine(); // 엔터 포함 안되게 처리 
		
		System.out.print("정말로 삭제하시겠습니까?(Y/N)");
		String ans = s.next(); // 응답이 Y/N 인지 받음 
		if(ans.equalsIgnoreCase("y")) { // 케이스 신경쓰지 않고 y이기만 하면 ok 
			list.remove((int)idlist.get(id-1)); // int 캐스팅을 안하면 삭제가 안됨.
			System.out.println("단어가 삭제되었습니다. ");
		}
		else System.out.println("취소되었습니다. ");

		
	}
	public void loadFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));
			String line;
			int count = 0; // 단어 수 세기 위한 변수 
			while(true) {
				line = br.readLine();
				if(line == null)break; // 파일 끝나면 종료 
				String data[] = line.split("\\|");
				int level = Integer.parseInt(data[0]); // 파일 내에선 숫자도 문자로 인식되므로 Integer.parseInt로 정수변환. 
				String word = data[1];
				String meaning = data[2];
				
				list.add(new Word(0,level,word,meaning)); // List에 새로운 word 추가 
				count ++; // 단어 성공적으로 추가했으면 count 증가. 
			}
			
			
			br.close(); // 닫기 
			System.out.println("==> 총 "+count+"개 단어 로딩 완료! "); // 오류 없었으면 로딩된 총 단어 수 출력 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveFile() {
		try {
			PrintWriter pr = new PrintWriter(new FileWriter(fname));// Dictionary.txt파일에 저장 
			for(Word one : list) { // list의 모든 내용을 가져오기 위해 each 문 사용 
				pr.write(one.toFileString()+"\n"); // 가져온 one을 toFileString 포맷으로 변경해서 pr에 write 
				
			}
			
			pr.close();
			System.out.println("\n ==> 파일 저장 완료! \n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public void searchLevel() {
		System.out.print("=> 원하는 레벨은? (1~3 입력): ");
		int level = s.nextInt();
		listAll(level); // 레벨에 따라 단어 출력하는 함수 사용 
	}

	public void searchWord() {
		System.out.print("=> 검색하려는 단어는? :");
		String keyword = s.next();
		listAll(keyword); // 위에서 만들어둔 listAll로 키워드 찾음 
		
		/*
		 * 아무것도 없으면 해당 단어가 없다고 출력하기. 
		 * 다시 입력하시겠습니까? 묻기 
		 * 
		 * 
		 * */
	}

}
