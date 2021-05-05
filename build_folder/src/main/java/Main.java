package main.java;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
        apiService api = new apiService();
        String [] res = api.apiCall();
        for(int i = 0; i < res.length; i ++){
            System.out.println(res[i]);
        }
    }
}
