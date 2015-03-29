/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhibernatetest;

/**
 *
 * @author dauren
 */
public class Factory {
    public static Factory instance = new Factory();
    public CustomerDao customerDao;
    
    private Factory(){
        
    }
    
    public static Factory getInstance(){
        
        return Factory.instance;
    }
    
    public CustomerDao getCustomerDao(){
        if(customerDao == null) customerDao = new CustomerDaoImpl();
        return customerDao;
    }
}
