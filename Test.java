package student;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;



class Test {


	public static void main(String[] args) {

		Student_Info st = new Student_Info();
		Student_Info st2 = new Student_Info();
		Student_Info st3 = new Student_Info();
		st.setStudentName("James");
		st.setStudentLastName("Murray");
		st2.setStudentName("Chris");
		st2.setStudentLastName("Ferdinand");
		st3.setStudentName("Aaron");
		st3.setStudentLastName("Milner");

		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(st);
		session.save(st2);
		session.save(st3);
		st.setStudentName("Keit");
		session.update(st);
		
		session.delete(st2);

		session.getTransaction().commit();
		session.close();

	}



}
