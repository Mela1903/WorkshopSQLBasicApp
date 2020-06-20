package pl.coderslab.entity;

public class MainDao {

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("Agnieszka");
        user.setEmail("agnieska.flak1@gmail.com");
        user.setPassword("Mela");

        UserDao userDao = new UserDao();
     //   userDao.create(user);

        User damian = new User();
        damian.setUserName("Damian");
        damian.setEmail("blebleble@gmail.com");
        damian.setPassword("Hello");

     //   userDao.create(damian);

     //   User read = userDao.read(3);

     //   User update = userDao.update();
    }

}
