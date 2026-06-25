import entity.Employee;
import entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test {

	public static void main(String[] args) {
		// persistence.xml 의 my-pu 로 EntityManager 를 EntityManagerFactory 로부터 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin(); // transaction 준비, 영속성 컨텍스트(1차 캐시) 준비
		
		// #1. Product 1건 persist
//		{
//			Product p = new Product();
//			p.setId(1);
//			p.setName("Book");
//			
//			em.persist(p);
//		}
		
		// #2. DB -> Entity 객체, 영속화 <= find()
//		{
//			Product p = em.find(Product.class, 1); // 객체 반환, 이미 영속화되어 있다.
//			System.out.println(p);
//			p.setName("Phone"); // Dirty-Check
//			System.out.println(p);
//		}
		
		// ----------------------------------------------------
		
		// #3. persist - PK 중복
//		{
//			Employee e = new Employee();
//			e.setId(1);
//			e.setName("일길동");
//			e.setAddress("대전");
//			
//			em.persist(e);
//		}
		
		
		// #4. find() + Dirty Check
//		{
//			Employee e = em.find(Employee.class, 1); // e 영속화 된 상태. snapshot 이 생성, 관리
//			System.out.println(e);
//			e.setAddress("대전"); // Dirty 발생 - check
//			System.out.println(e);
//		}
		
		// merge() DB 에 없으면 insert, DB 에 있으면 update
		
		// #5. merge() - insert
//		{
//			Employee e = new Employee();
//			e.setId(3);
//			e.setName("삼길동");
//			e.setAddress("광주");
//			
//			// #5-1
////			em.merge(e);  // 영속화 되었기 때문에 insert 수행
////			
////			// 위 insert 테스트 후 다시 3번 데이터 삭제 후 아래 코드 테스트
////			
////			e.setAddress("abc"); // abc 로 dirty-check 되지 않았다!
////			
////			// b = merge(a) 에서 영속화되는 객체는 a 가 아니라 b
////			// a 객체는 merge() 에게 id 등 단순 정보 제공 
//			
//			// #5-2
//			Employee e2 = em.merge(e); 
//			System.out.println(e);
//			System.out.println(e2);
//			System.out.println(e == e2);
//			
//			// e2 에 대한 Dirty-Check
//			e.setAddress("abc");
//			e2.setAddress("def");
//		}
		
		// #6. merge() - update
//		{
//			Employee e = new Employee();
//			e.setId(3);
//			e.setName("삼길동");
//			e.setAddress("광주");
//			
//			// #5-1
//			em.merge(e);  // 리턴된 객체가 영속화
//		}		
		
		// #7. detach() - 엔티티 객체를 영속성 컨텍스트에서 제외, 분리. 이 시점 이후의 변경은 추척 X, commit 에 반영 X
//		{
//			Employee e = em.find(Employee.class, 2);
//			System.out.println(e);
////			e.setAddress("abc"); 
//			em.detach(e);  // snapshot 으로 돌아가는 게 맞음. e.setAddress("abc"); 도 반영 X
//			e.setAddress("def");
//			System.out.println(e);
//		}
		
		// #8. remove() - delete 단, 영속화된 엔티티 객체에 한함.
//		{
//			Employee e = em.find(Employee.class, 2);
//			System.out.println(e);
//			em.remove(e);
//		}
		
		// #8. remove() - 비 영속 객체 delete
//		{
//			Employee e = new Employee();
//			e.setId(3);
//			e.setName("삼길동");
//			
//			System.out.println(e);
//			em.remove(e);
//		}
		
		
		// #9. @Table, @Column 을 이용해서 DB 테이블 명 및 컬럼 명, 속성 설정 등을 변경
		{
			Employee e = new Employee();
			e.setId(1);
			e.setName("일길동");
			e.setAddress("대전");
			
			em.persist(e);
		}
		
		em.getTransaction().commit(); // transaction 완료, 확정 -> DB 반영 ( 내부적으로 flush() 호출 )
		
		em.close();
		emf.close();
	}

}






















