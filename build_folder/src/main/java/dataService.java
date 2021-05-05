package main.java;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

//This service is used to push and pull the data required by the user which in this case would be the
//companies using the user data.
public class dataService {
    entityService entity = new entityService();
    //function returns a String value depending upon the amount of data that was fetched from
    // the different tables
    public String pull(int [] userid) throws SQLException {
        int count = 0;
        String result = "dataService.pull() called,";
        monitoringService monitor = new monitoringService();
        for(int i = 0; i < userid.length; i++){
            result = result + entity.fetchData(userid[i]);
            if (entity.fetchData(userid[i]) != " entityService.fetchData() called, User not found "){
                count++;
            }
        }
        result = result + monitor.dataFetched(count);
        return result;
    }
    //function returns a String value depending upon the amount of data that was pushed to the table
    //NOTE:- once the push function is called, the changes won't be reflected in the embeddings_table
    //till the encoder-decoder.ipynb script is run.
    public String push(ArrayList<userDatatype> users) throws SQLException, IOException, InterruptedException {
        modellingService m = new modellingService();
        String result = "dataService.push() called,";
        for(int i = 0; i < users.size(); i++) {
           result = result + entity.writeData(users.get(i));
        }
        System.out.println("dataService.push() called, to push data successfully run encoder-decoder.py");
        return result;
    }
}
