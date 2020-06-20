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

        User damian2 = userDao.read(2);
        damian2.setUserName("Dami");
        damian2.setEmail("equador@gmail.com");
        damian2.setPassword(userDao.hashPassword("abdeee"));
        userDao.update(damian2);
    }

}
