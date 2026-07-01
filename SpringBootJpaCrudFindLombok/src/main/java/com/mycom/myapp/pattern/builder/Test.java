package com.mycom.myapp.pattern.builder;

public class Test {

	public static void main(String[] args) {
		// #1. Book
//		Book book = Book.builder()
//						.isbn("1234")
//						.title("한국 축구 이대로 좋은가?")
//						.author("김대섭")
//						.description("2030년 월드컵을 어떻게 준비할 것인가?")
//						.price(1000);
//		
//		System.out.println(book);

		// #2. Board
//		Board board = new Board.Builder()
//							.title("게시글 제목")
//							.content("게시글 내용")
//							.category("A 게시판")
//							.build();
//		
//		System.out.println(board);
		
		// #3. title 누락 Board
		Board board = new Board.Builder()
//				.title("게시글 제목")
				.content("게시글 내용")
				.category("A 게시판")
				.build();

		System.out.println(board);		
	}

}
