package main.java;
import java.util.Scanner;

//This service is an optional service to have, as the name suggests that this only takes care of the transactions
//in terms of how much data was handled at a time by the framework
public class monitoringService {
    //this function returns a String and is called by the profileService whenever new data is added to the system.
    public String dataAdded(int facebook, int twitter){
        int num = facebook + twitter;
        return(" monitoringService.dataAdded() called, Data added: " + num + " Mbs");
    }
    //this function returns a String and is called by the dataService whenever new data is fetched from the system.
    public String dataFetched(int users){
        return(" monitoringService.dataFetched() called, Data fetched: " + users + " Mbs");
    }
    //this function returns a String and is called by the profileService whenever new data is deleted to the system.
    public String dataDeleted(int users){
        return(" monitoringService.dataDeleted() called, Data deleted: " + users + " Mbs");
    }
}
