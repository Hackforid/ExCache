package com.smilehacker.excache;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by zhouquan on 17/7/24.
 */

@Aspect
public class CacheAOP {

    private final static String TAG = CacheAOP.class.getSimpleName();
    private final ExLruCache mCache = new ExLruCache(100);


    @Pointcut("execution(@com.smilehacker.excache.ExCache * *(..))")
    public void ExCacheMethod() {
    }

    @Around("ExCacheMethod()")
    public Object onDebugToolMethodBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.i(TAG, "before test");
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(joinPoint.getSignature());
        if (joinPoint.getArgs() != null) {
            for (Object arg : joinPoint.getArgs()) {
                keyBuilder.append(arg.toString());
            }
        }
        Log.i(TAG, "key is " + keyBuilder.toString());
        String key = keyBuilder.toString();
        Object value = mCache.get(key);
        if (value == null) {
            value = joinPoint.proceed();
            if (value != null) {
                mCache.put(key, value);
            }
        }
        Log.i(TAG, "after test");

        return value;
    }

}
