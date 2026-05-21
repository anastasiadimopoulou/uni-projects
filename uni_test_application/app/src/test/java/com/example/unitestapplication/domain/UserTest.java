package com.example.unitestapplication.domain;



import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.Assert.*;
import com.example.unitestapplication.*;


public class UserTest {
    @Test
    public void testgetfirstname(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);
        String e="john";
        String act=user.getFirst_name();
        assertEquals(e,act);
    }
    @Test
    public void testgetlastname(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);
        String e="smith";
        String act=user.getLast_name();
        assertEquals(e,act);
    }
    @Test
    public void testgetusername(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);
        String e="johns13";
        String act=user.getUsername();
        assertEquals(e,act);
    }
    @Test
    public void testgetpassword(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);
        String e="1234a";
        String act=user.getPassword();
        assertEquals(e,act);
    }
    @Test
    public void testgetdepartment(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);
        String e="IT";
        String act=user.getDepartment();
        assertEquals(e,act);
    }
    @Test
    public void testgetuniversity(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);
        String e="aueb";
        String act=user.getUniversity();
        assertEquals(e,act);
    }
    @Test
    public void testgetdateofbirth(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);
        String e="11-1-2000";
        String act=user.getDate_of_birth();
        assertEquals(e,act);
    }
    @Test
    public void testgetId(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);
        int e=2323;
        int act=user.getId();
        assertEquals(e,act);
    }
    @Test
    public void testsetfirstname(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);

        user.setFirst_name("Maria");
        assertEquals("Maria",user.getFirst_name());
    }
@Test
    public void testsetlastname(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);

        user.setLast_name("kappa");
        assertEquals("kappa",user.getLast_name());
    }

    @Test
    public void testsetusername(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);

        user.setUsername("Maria12");
        assertEquals("Maria12",user.getUsername());
    }

    @Test
    public    void testsetpassword(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);

        user.setPassword("1212e");
        assertEquals("1212e",user.getPassword());
    }

    @Test
    public void testsetdepartment(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);

        user.setDepartment("loxri");
       assertEquals("loxri",user.getDepartment());
    }

    @Test
    public void testsetuniversity(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);

        user.setUniversity("uoa");
        assertEquals("uoa",user.getUniversity());
    }
    @Test
    public void testsetdateofbirth(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);

        user.setDate_of_birth("12-3-1987");
        assertEquals("12-3-1987",user.getDate_of_birth());
    }

    @Test
    public void testsetId(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);

        user.setId(12345);
        assertEquals(12345,user.getId());
    }
    @Test
    public  void testconstructor(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);

            assertEquals("john",user.getFirst_name());
            assertEquals("smith",user.getLast_name());
            assertEquals("1234a",user.getPassword());
            assertEquals("johns13",user.getUsername());
            assertEquals("aueb",user.getUniversity());
            assertEquals("IT",user.getDepartment());
            assertEquals("11-1-2000",user.getDate_of_birth());
            assertEquals(2323,user.getId());



    }

    @Test
    public  void testcheckIfUserExists(){
        User user=new User("john", "smith", "johns13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);
        User user2=new User("Maria", "kappa", "Maria13", "1256a", "IT", "aueb" , "11-1-2003" ,2324);
        int wantedid=2323;
       boolean result= user.check_if_user_exists(wantedid);
       assertTrue(result);
       boolean rs2= user2.check_if_user_exists(wantedid);
       assertFalse(rs2);


    }
@Test
    public  void Testuniqueusername(){
    User user=new User("john", "smith", "apple13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);
    User user2=new User("Maria", "kappa", "apple13", "1256a", "IT", "aueb" , "11-1-2003" ,2324);
    String wantedusername ="apple13";
    String username2="banana23";
    boolean res2=user.unique_username(username2);
    boolean res=user.unique_username(wantedusername );
    ///boolean res2=user.unique_username(username2);
    //boolean res=user2.unique_username(wantedusername );
    assertFalse(res);
    assertTrue(res2);


}
@Test
    public  void testCheckLogIn(){
    User user=new User("Maria", "kappa", "apple13", "1256a", "IT", "aueb" , "11-1-2003" ,2324);
    User user2=new User("john", "smith", "apple13", "1234a", "IT", "aueb" , "11-1-2000" ,2323);
    User u=new User();
    String username="apple13";
    String passuser="1234a";

    boolean result=user2.check_login_fields(username,passuser);
    assertTrue(result);
    boolean r =user.check_login_fields(username,passuser);
    assertFalse(r);


}

}
