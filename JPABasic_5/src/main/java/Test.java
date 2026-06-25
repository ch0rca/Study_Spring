import entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Persistence;

// persistence.xml 의 속성들을 추가해 가면서 개발 편의성 확인
public class Test {

	public static void main(String[] args) throws Exception{
		// persistence.xml 의 my-pu 로 EntityManager 를 EntityManagerFactory 로부터 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin(); // transaction 준비, 영속성 컨텍스트(1차 캐시) 준비
		
		// #1. @GeneratedValue(strategy=GenerationType.IDENTITY)
//		{
//			Employee emp = new Employee();
////			emp.setId(0);
//			emp.setName("홍길동");
//			emp.setAddress("서울");
//			
//			em.persist(emp);
//			
//			Employee emp2 = new Employee();
//			emp2.setName("이길동");
//			emp2.setAddress("여주");
//			
//			em.persist(emp2);			
//		}

		// #2. @GeneratedValue(strategy=GenerationType.AUTO) <= ????
		{
			Employee emp = new Employee();
			emp.setId(0);
			emp.setName("홍길동");
			emp.setAddress("서울");
			
			em.persist(emp);		
		}
		
		
		em.getTransaction().commit(); // transaction 완료, 확정 -> DB 반영 ( 내부적으로 flush() 호출 )
		
		em.close();
		emf.close();
	}

}






















