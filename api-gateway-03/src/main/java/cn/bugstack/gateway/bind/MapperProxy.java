package cn.bugstack.gateway.bind;

import cn.bugstack.gateway.session.GatewaySession;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MapperProxy implements MethodInterceptor {
    private GatewaySession gatewaySession;
    private final String uri;
    public MapperProxy(GatewaySession gatewaySession, String uri)  {
        this.gatewaySession = gatewaySession;
        this.uri = uri;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        MapperMethod linkMethod = new MapperMethod(uri, method, gatewaySession.getConfiguration());
        return linkMethod.execute(gatewaySession, args);
    }
}
