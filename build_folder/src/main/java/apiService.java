package main.java;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

//apiService is responsible for interacting with all services in a sequence,
// and take care of the dependencies between the services.

public class apiService {
    profileService p = new profileService();
    dataService d = new dataService();
    public String [] apiCall() throws SQLException, IOException, InterruptedException {
        ArrayList<userDatatype> users = new ArrayList<>();
        users.add(new userDatatype(55, true, true));
        users.add(new userDatatype(89, true, true));
        users.add(new userDatatype(2234, true, true));
        String [] results = new String[4];
        results[0] = p.newUser(2, 2);
        results[1] = p.existingUser(users);
        results[2] = d.push(users);
        int [] user_id = new int[]{55, 89, 2234};
        results[3] = d.pull(user_id);
        return results;
    }

}
