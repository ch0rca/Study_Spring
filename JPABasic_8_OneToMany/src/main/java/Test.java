import java.util.List;

import entity.Comment;
import entity.Post;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test {

	public static void main(String[] args) throws Exception{
		// persistence.xml 의 my-pu 로 EntityManager 를 EntityManagerFactory 로부터 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin(); // transaction 준비, 영속성 컨텍스트(1차 캐시) 준비
		
		// 게시글
		Post p = new Post();
		p.setTitle("제목 1");
		p.setContent("내용 1");
		
		// 댓글
		Comment c1 = new Comment();
		c1.setContent("코멘트 1");
		
		Comment c2 = new Comment();
		c2.setContent("코멘트 2");		
		
		// #1. Post 만 persist
//		{
//			em.persist(p);
//		}
		
		// #2. Comment 2건 만 persist
//		{
//			em.persist(c1);
//			em.persist(c2);
//		}	
		
		// #3. Post 1건, Comment 2건 persist
//		{
//			em.persist(p);
//			em.persist(c1);
//			em.persist(c2);
//		}		
		
		// #4. Post 와 Comment 연결 후 Post 만 persist
		//     => org.hibernate.TransientObjectException <= c1, c2 가 영속화 X
//		{
//			p.setComments(List.of(c1, c2)); // Post 객체에 c1, c2 의 List 연결
//			
//			em.persist(p);
//		}		
		
		// #5. Post 와 Comment 연결 후 Post -> Comment  persist
		//     => 3 건 p, c1, c2 insert + Post_Comment 에 연결 2건 insert
//		{
//			p.setComments(List.of(c1, c2)); // Post 객체에 c1, c2 의 List 연결
//			
//			em.persist(p);
//			em.persist(c1);
//			em.persist(c2);			
//		}	

		// #5. Post 와 Comment 연결, CascadeType.PERSIST 후 Post 만 persist
		//     => 
		{
			p.setComments(List.of(c1, c2)); // Post 객체에 c1, c2 의 List 연결
			
			em.persist(p);		
		}			
		
		
		em.getTransaction().commit(); // transaction 완료, 확정 -> DB 반영 ( 내부적으로 flush() 호출 )
		
		em.close();
		emf.close();
	}

}






















