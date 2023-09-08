package com.mycom.word;

public class Word {
	//private으로 멤버변수를 설정했기 때문에 이 멤버변수에 접근하기 위해선 getter, setter 필요
	private int id;
	private int level;
	private String word;
	private String meaning;
	
	//생성자 
	Word(){}
	Word(int id, int level, String word, String meaning){
		//받아온 데이터를 멤버변수에 세팅 
		this.id = id;
		this.level = level;
		this.word = word;
		this.meaning = meaning;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	

	
	
	@Override
	public String toString() {
		
		String slevel = "";
		for(int i = 0; i<level; i++)
			slevel +="*";
		// 1 *         electric   전기의, 전기를 생산하는  
		String str = String.format("%-3s", slevel) //단어의 레벨은 왼쪽정렬 
				+ String.format("%15s", word) // 단어는 오른쪽 정렬 
				+ "  " + meaning; // 단어 뜻은 딱히 정렬 필요 없으므로 공백 두고 적음 
		return str;
	}
	public String toFileString() { // 파일에 넣을 포맷으로 변경함 
		return this.level + "|" + this.word+"|"+this.meaning;
	}
}
