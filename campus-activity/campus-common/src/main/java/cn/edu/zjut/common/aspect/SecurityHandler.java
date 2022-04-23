package cn.edu.zjut.common.aspect;

import cn.edu.zjut.common.dao.ActivityInfoDao;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class SecurityHandler {
    @Autowired
    private ActivityInfoDao activityInfoDao;

    public void logs(JoinPoint jp) { // Joinpoint就是被切的方法,通过被切的方法可以获取参数信息,和方法信息
        System.out.println("日志入库........");
        System.out.println("被切的目标对象:" + jp.getTarget());
        System.out.println("被切的方法声明:" + jp.getSignature().toString());
        System.out.println("被切的方法的参数信息:" + Arrays.toString(jp.getArgs()));
    }

    public void ex(Exception e) { // e与xml中指定的名称要相匹配
        System.out.println("异常处理==>" + e);
    }

    private void checkSecurity(JoinPoint jp) {
        try {

            System.out.println("日志入库........");
            System.out.println("被切的目标对象:" + jp.getTarget());
            System.out.println("被切的方法声明:" + jp.getSignature().toString());
            System.out.println("被切的方法的参数信息:" + Arrays.toString(jp.getArgs()));
            int theId = ((Long) jp.getArgs()[0]).intValue();
            int changePeople = ((Integer)jp.getArgs()[1]).intValue();
//        System.out.println("isis " + theId);
            int registerPeople = activityInfoDao.getRegisterPeople(theId);
            if (registerPeople > changePeople)
                throw new Exception();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
