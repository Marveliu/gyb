package cn.wizzer.app.gy.modules.models;

import cn.wizzer.app.TestBase;
import cn.wizzer.app.gy.modules.services.GyInfService;
import cn.wizzer.app.gy.modules.services.impl.GyInfServiceImpl;
import org.junit.*;
import org.nutz.dao.Dao;

/** 
* gy_inf Tester. 
* 
* @author <Authors name> 
* @since <pre>十一月 14, 2017</pre> 
* @version 1.0 
*/ 
public class gy_infTest extends TestBase {

    /**
     * Method: getId()
     */
    @Test
    public void testGetId() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setId(String id)
     */
    @Test
    public void testSetId() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getUserid()
     */
    @Test
    public void testGetUserid() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setUserid(String userid)
     */
    @Test
    public void testSetUserid() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getRealname()
     */
    @Test
    public void testGetRealname() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setRealname(String realname)
     */
    @Test
    public void testSetRealname() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getQq()
     */
    @Test
    public void testGetQq() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setQq(String qq)
     */
    @Test
    public void testSetQq() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getPhone()
     */
    @Test
    public void testGetPhone() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setPhone(String phone)
     */
    @Test
    public void testSetPhone() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getBirthday()
     */
    @Test
    public void testGetBirthday() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setBirthday(Integer birthday)
     */
    @Test
    public void testSetBirthday() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getSex()
     */
    @Test
    public void testGetSex() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setSex(Integer sex)
     */
    @Test
    public void testSetSex() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getIdcard()
     */
    @Test
    public void testGetIdcard() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setIdcard(String idcard)
     */
    @Test
    public void testSetIdcard() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getCollege()
     */
    @Test
    public void testGetCollege() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setCollege(String college)
     */
    @Test
    public void testSetCollege() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getSchool()
     */
    @Test
    public void testGetSchool() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setSchool(String school)
     */
    @Test
    public void testSetSchool() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getMajor()
     */
    @Test
    public void testGetMajor() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setMajor(String major)
     */
    @Test
    public void testSetMajor() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getRegYear()
     */
    @Test
    public void testGetRegYear() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setRegYear(Integer regYear)
     */
    @Test
    public void testSetRegYear() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getStuLevel()
     */
    @Test
    public void testGetStuLevel() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setStuLevel(int stuLevel)
     */
    @Test
    public void testSetStuLevel() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getStatus()
     */
    @Test
    public void testGetStatus() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setStatus(int status)
     */
    @Test
    public void testSetStatus() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: gyid()
     */
    @Test
    public void testGyid() throws Exception {
        //TODO: 测试一下雇员id生成好不好使
        gy_inf gy = new gy_inf();
        gy.setCollege("1");
        gy.setSex(1);
        gy.setStuLevel(1);
        Dao dao =  ioc.get((Dao.class));
        GyInfService gyservice = new GyInfServiceImpl(dao);
        int n = gyservice.count();
        System.out.print(gy.gyid()+n);
    }
}
