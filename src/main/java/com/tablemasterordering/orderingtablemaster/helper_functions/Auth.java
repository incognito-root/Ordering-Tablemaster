package com.tablemasterordering.orderingtablemaster.helper_functions;

import com.tablemasterordering.orderingtablemaster.api_service.CustomerService;
import com.tablemasterordering.orderingtablemaster.models.CustomerModel;
import com.tablemasterordering.orderingtablemaster.models.GetCustomerById;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Auth {
    public static long customerId;

    public static CustomerModel customerDetails;

    public static boolean checkSignedInStatus() {
        String idFromCookie = "";

        try {
            idFromCookie = readCookie();
        } catch (IOException e) {
            System.out.println("Error reading cookie from file");
            throw new RuntimeException(e);
        }

        if (idFromCookie == null || idFromCookie.isEmpty()) {
            return false;
        }

        customerId = Long.parseLong(idFromCookie);

        return true;
    }

    private static String readCookie() throws IOException {
        BufferedReader reader = null;
        String data = "";

        try{
            reader = new BufferedReader(new FileReader("cookie.txt"));
            data = reader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return data;
    }

    public static void setCustomerDetails() throws IOException {
        CustomerService customerService = new CustomerService();
        customerDetails = customerService.getCustomerDetails(new GetCustomerById(customerId));
    }

    public static void setCustomerDetails(long cId) throws IOException {
        CustomerService customerService = new CustomerService();
        customerDetails = customerService.getCustomerDetails(new GetCustomerById(cId));
    }
}
