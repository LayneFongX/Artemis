package com.farid.artemis.aspect;

import com.alibaba.fastjson.JSONObject;
import com.farid.artemis.annotation.AtopPermissionAuth;
import com.farid.artemis.domain.AtopPermissionAuthMeta;
import com.farid.artemis.enums.VerifyBusinessEnum;
import com.farid.artemis.enums.VerifyMethodEnum;
import com.farid.artemis.service.VerifyBizService;
import com.farid.artemis.utils.ClassUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
//@Aspect
//@Component
public class AtopPermissionAuthAspect {

    @Resource
    private VerifyBizService verifyBizService;

    /**
     * key为方法名称 value为该方法元数据
     */
    private static final Map<String, List<AtopPermissionAuthMeta>> permissionMetaMap = new ConcurrentHashMap<>();

    private static final Set<String> unValidMethodSet = new HashSet<>();

    /**
     * 切面，定义拦截指定注解
     */
    @Pointcut(value = "execution(* com.farid.annotation..*.*(..))")
    private void pointcut() {
    }

    /**
     * 环绕切面
     *
     * @param joinPoint 切面点
     */
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        // 获取注解方法的元数据
        List<AtopPermissionAuthMeta> authMetaList = getPermissionAuthMeta(targetMethod);
        for (AtopPermissionAuthMeta authMeta : authMetaList) {
            Object[] args = joinPoint.getArgs();
            Map<String, Object> argsMap = getArgsMap(methodSignature, args);
            log.info("AtopPermissionAuthAspect doAround argsMap = {}", JSONObject.toJSONString(argsMap));
            VerifyMethodEnum methodsEnum = authMeta.getVerifyMethod();
            VerifyBusinessEnum businessEnum = authMeta.getVerifyBusinessEnum();
            verifyBizService.matchVerifyMethodByMethodName(methodsEnum.getMethodName(), businessEnum.getBusinessType(),
                    authMeta.getVerifyMethodParams(), argsMap);
        }
        return joinPoint.proceed();
    }

    /**
     * 获取和初始化权限配置
     *
     * @param method 切面方法
     * @return 返回权限配置
     */
    private List<AtopPermissionAuthMeta> getPermissionAuthMeta(Method method) {
        if (permissionMetaMap.containsKey(method.toGenericString())) {
            return permissionMetaMap.get(method.toGenericString());
        }
        if (unValidMethodSet.contains(method.toGenericString())) {
            return Collections.EMPTY_LIST;
        }

        List<AtopPermissionAuth> permissionAuthList = ClassUtils.getAnnotationBySource(method, AtopPermissionAuth.class);
        if (CollectionUtils.isEmpty(permissionAuthList)) {
            unValidMethodSet.add(method.toGenericString());
            return Collections.EMPTY_LIST;
        }

        List<AtopPermissionAuthMeta> authMetaList = new ArrayList<>();
        for (AtopPermissionAuth permissionAuth : permissionAuthList) {
            if (Objects.isNull(permissionAuth)) {
                continue;
            }
            AtopPermissionAuthMeta authMeta = new AtopPermissionAuthMeta();
            authMeta.setVerifyMethod(permissionAuth.method());
            authMeta.setVerifyMethodParams(permissionAuth.methodParams());
            authMeta.setVerifyBusinessEnum(permissionAuth.business());
            authMetaList.add(authMeta);
        }
        permissionMetaMap.putIfAbsent(method.toGenericString(), authMetaList);
        return authMetaList;
    }


    private Map<String, Object> getArgsMap(MethodSignature methodSignature, Object[] args) {
        Map<String, Object> argsMap = new HashMap<>();
        String[] parameterNames = methodSignature.getParameterNames();
        if (ArrayUtils.isEmpty(parameterNames)) {
            return argsMap;
        }
        for (int i = 0; i < parameterNames.length; i++) {
            argsMap.put(parameterNames[i], args[i]);
        }
        return argsMap;
    }
}
