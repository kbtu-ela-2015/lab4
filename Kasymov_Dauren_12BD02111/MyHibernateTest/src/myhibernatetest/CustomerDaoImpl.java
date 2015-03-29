/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhibernatetest;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 *
 * @author dauren
 */
public class CustomerDaoImpl implements CustomerDao{

    @Override
    public void addCustomer(Customer customer) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(customer);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if((session != null) && (session.isOpen()))
                session.close();
        }
        
    }

    @Override
    public void deleteCutomer(Customer customer) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(customer);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public Customer getCustomer(int id) throws SQLException {
        
        Customer result= null;
        
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = (Customer) session.get(Customer.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if((session != null) && (session.isOpen()))
                session.close();
        }
        
        return result;
    }

    @Override
    public List<Customer> getCustomers() throws SQLException {
        List<Customer> customers= null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            customers = session.createCriteria(Customer.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if((session != null) && (session.isOpen()))
                session.close();
        }
        return customers;
    }

   

    @Override
    public void updateCustomer(int id, String fn, String sn) throws SQLException {
        Session session = null;
        Customer result= null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = (Customer) session.get(Customer.class, id);
            
            
            session.beginTransaction();
            result.setForename(fn);
            result.setSurname(sn);
            session.update(result);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if((session != null) && (session.isOpen()))
                session.close();
        }
    }

    @Override
    public List<Customer> getCustomerByName(String name) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Customer> customer= null;
        try {
            //customer = getCustomers();
            for (Customer customer1 : getCustomers()) {
                if (customer1.getForename().equalsIgnoreCase(name)) {
                    
                
                customer.add(customer1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return customer;
    }
    
}
