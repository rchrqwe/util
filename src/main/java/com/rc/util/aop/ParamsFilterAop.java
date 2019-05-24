package com.rc.util.aop;

import com.alibaba.fastjson.JSON;
import com.rc.util.Impl.CustomParamsFilter;
import com.rc.util.util.CheckJsonParams;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Order(1)
@Aspect
@Component
public class ParamsFilterAop {

    @Around("within(*..*) && @annotation(rl)")
    public Object checkParams(ProceedingJoinPoint pjp, CustomParamsFilter rl) throws Throwable {
        HashMap<String, Object> rowParamMap = null;
        Object object = null;
        System.out.println("自定义注解aop校验json实现");
        if (pjp.getArgs()[0] instanceof Map) {
            // 创建签名对象
            Signature sig = (Signature) pjp.getSignature();
            MethodSignature msig = null;
            // 获取方法签名对象
            msig = (MethodSignature) sig;
            // 获取对象
            Object target = pjp.getTarget();
            // 反射获取方法名
            Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
            // String string = (String) currentMethod.getDefaultValue();
            Class<?> clazz = target.getClass();
            // 获取反射的类变量数组
            Field[] field = clazz.getDeclaredFields();
            Map<String, String> variableMap = new HashMap<>();
            rowParamMap = ((HashMap<String, Object>) pjp.getArgs()[0]);
            for (Field f : field) {
                // System.out.println(f.getName());
                // 强制变量变为可视
                f.setAccessible(true);
                // 如果变量类型为String类型
                if (f.get(target) instanceof String) {
                    // 将控制器类里面定义需要过滤参数的名字为方法名string装到Map里
                    variableMap.put(f.getName().toString(), f.get(target).toString());
                }
            }
            if (!variableMap.get(currentMethod.getName()).equals("")) {
                // 自己写的参数过滤器
                CheckJsonParams checkJsonParams = new CheckJsonParams();
                // 获取参数过滤结果
                Map result = checkJsonParams.checkJsonParams(rowParamMap, variableMap.get(currentMethod.getName()));
                // 如果不正常
                if (Integer.valueOf(result.get("status").toString()) != 200) {
                    // 手动执行对象方法，如不执行没结果
                    // object = pjp.proceed();
                    // 参数不完整，手动返回结果，注意返回的结果类型必须与方法一致
                    object = JSON.toJSONString(result);
                }else {
                    // 正常直接执行方法
                    object = pjp.proceed();
                }
            } else {
                // 正常直接执行方法
                object = pjp.proceed();
            }
        } else if (pjp.getArgs()[0] instanceof String) {
            throw new Exception("annotationUsedError:Params first is not Map");
        }

        return object;
    }
}
