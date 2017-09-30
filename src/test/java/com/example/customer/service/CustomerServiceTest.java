package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class CustomerServiceTest {

        @Autowired
        CustomerRepository customerRepository;

        @Test
        public void testAddGet() {
            // Get unique names every time this test runs
            String firstName = Long.toString(System.currentTimeMillis());
            String lastName = Long.toString(System.currentTimeMillis());

            Customer customer1 = new Customer();
            customer1.setFirstName(firstName);
            customer1.setLastName(lastName);
            customerRepository.add(customer1);

            List<Customer> people = customerRepository.get();

            Customer foundPerson = findInList(people, firstName, lastName);
            Assert.assertNotNull(foundPerson);

            Customer customer3 = customerRepository.getById(foundPerson.getId());
            Assert.assertNotNull(customer3);   //person3 is found person again
            Assert.assertEquals(firstName, customer3.getFirstName());
            Assert.assertEquals(lastName, customer3.getLastName());
        }

        private Customer findInList(List<Customer> people, String first, String last) {
            // Find the new person in the list
            for (Customer customer : people) {
                if (customer.getFirstName().equals(first) && customer.getLastName().equals(last)) {
                    return customer;
                }
            }
            return null;
        }
    }