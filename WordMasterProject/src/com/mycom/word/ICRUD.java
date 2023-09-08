package com.mycom.word;
//인터페이스// 
/*
 인터페이스를 구분하는 이유: 
 inter : between이라는 뜻을 가짐. international, internet, 등등..
 inter+face : '얼굴과 얼굴 간', 서로가 의사소통을 하기 위해 정해놓은 "규칙"을 말함. 
 UI = user interface = 사용자와의 인터페이스. 사용자가 어떤 화면을 보고 상호작용하는지 정한 것. 
*/
public interface ICRUD {
	
		public Object add(); // 데이터 추가하는 함수 
		public int update(Object obj); // 데이터 업데이트하는 함수 
		public int delete(Object obj); // 데이터 삭제하는 함수 
		public void selectOne(int id); // 데이터 한 개만 선택하는 함수 
}
