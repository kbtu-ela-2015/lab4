/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhibernatetest;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author dauren
 */
public class MyHibernateTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
   /*     Configuration config = new Configuration().configure();
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        
        try {
            Customer customer = (Customer)session.get(Customer.class, 1);
            System.out.println("Cutomer name = "+ customer.getForename());
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
        finally{
            session.close();
        }
            
 */  
        
     
        
        
        Factory factory = Factory.getInstance();
        CustomerDao customerDao = factory.getCustomerDao();
        
       /* Customer newCustomer = new Customer();
        newCustomer.setForename("gfdnewstd");
        newCustomer.setSurname("newnewgf");
        
        customerDao.addCustomer(newCustomer);
      */
       
        
        /*
        Customer customer = customerDao.getCustomer(0);
        //System.out.println(customer.getCustomerid()+ " "+ customer.getForename()+ " "+ customer.getSurname());
        customerDao.deleteCutomer(customer);
        */
        
        List<Customer> customers = customerDao.getCustomers();
        System.out.println("id  forename    surname");
        for(Customer customerr : customers){
            System.out.println(customerr.getCustomerid()+ " "+ customerr.getForename()+ " "+ customerr.getSurname());
        }
        
        
        System.out.println("end");
        System.exit(0);

    }
    
}
