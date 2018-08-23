package org.somthing.yellow.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by huangshizhe on 2018/8/23
 */
public class RpcImporter {

    InvocationHandler invocationHandler = new InvocationHandler() {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    };
}
