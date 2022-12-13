
import com.shf.dao.AdminDao;
import com.shf.entity.Admin;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class TestAdmin {

   @Autowired
    AdminDao adminDao;
    @Test
    public void test01(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Admin admin = new Admin();
        admin.setName("lucky");
        admin.setPassword(encoder.encode("123456"));
        admin.setName("lucky");
        admin.setPhone("18548909895");
        admin.setDescription("这是一个管理权限");
        adminDao.insert(admin);



    }
}
