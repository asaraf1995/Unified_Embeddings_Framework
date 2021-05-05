
import main.java.monitoringService;
import main.java.profileService;
import main.java.userDatatype;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class profileServiceTest {
    profileService p = new profileService();
    monitoringService m = new monitoringService();
    ArrayList<userDatatype> users = new ArrayList<userDatatype>();

    @Test
    void newUser() throws SQLException, IOException, InterruptedException {
        String result = p.newUser(1, 1);
        System.out.println(result);
        String rs = m.dataAdded(1, 1);
        System.out.println(rs);
    }

    @Test
    void existingUser() throws SQLException {
        int min = 40000;
        int max = 300000;
        int user_id = (int)Math.floor(Math.random()*(max-min+1)+min);
        users.add(new userDatatype(user_id, true, true));
        String result = p.existingUser(users);
        assertEquals(" profileService.existingUser() called, Deleted a facebook and twitter user  monitoringService.dataDeleted() called, Data deleted: 1 Mbs", result);
        String rs = m.dataDeleted(users.size());
       System.out.println(rs);
    }
}