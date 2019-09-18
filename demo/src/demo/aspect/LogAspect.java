package demo.aspect;

import demo.annotation.AppLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.Controller;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * 此类是一个日志切面类，在调用Controller方法时会调用此切面类的方法记录操作日志
 */
@Component
@Aspect
public class LogAspect {

    /**
     * 统一定义切点，以达到重用切点表达式的目的
     * 定义多个切点可以用 || 连接 示例：@Pointcut("execution(* demo.controller.*.*(..)) || execution(* com.test.*.*(..))")
     */
    @Pointcut("execution(* demo.controller.*.*(..))")
    public void decalarePointCut(){}

    /**
     * 环绕通知(@Around)，功能最强大，综合了前置通知(@Before)，后置通知(@After)，返回通知(@AfterReturning)，异常通知(@AfterThrowing)
     * @param proceedingJoinPoint
     * @return
     */
    //@Around("execution(* demo.controller.*.*(..))")
    @Around("demo.aspect.LogAspect.decalarePointCut()")
    public Object log(ProceedingJoinPoint proceedingJoinPoint){

        //类全名
        String className = proceedingJoinPoint.getSignature().getDeclaringType().getName();
        //方法简单名称
        String simpleMethodName = proceedingJoinPoint.getSignature().getName();
        //方法全名
        String methodName = className + "#" + simpleMethodName + "()";
        Object[] args = proceedingJoinPoint.getArgs();

        //强制转换获取到方法对象
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        AppLog appLog = targetMethod.getAnnotation(AppLog.class);
        if(appLog!=null){
            String opeaSysName = appLog.opeaSysName();

            System.out.println("MyLog-------->opeaSysName:" + opeaSysName);
        }

        System.out.println("MyLog-------->" + new Date() + ": 执行了" + methodName + "方法, 入参：" + Arrays.asList(args));
        Object result = null;
        try {
            //执行目标方法
            result = proceedingJoinPoint.proceed();
            System.out.println("MyLog-------->执行结果：" + result);
        } catch (Throwable throwable) {
            //throwable.printStackTrace();
            System.err.println("MyLog-------->执行" + methodName + "方法出异常啦：" + throwable.getMessage());
            throw new RuntimeException("MyLog-------->执行" + methodName + "方法出异常啦：" + throwable.getMessage());
        }
        return result;
    }
}
