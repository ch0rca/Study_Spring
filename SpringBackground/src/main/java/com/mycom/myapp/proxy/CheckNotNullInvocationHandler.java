package com.mycom.myapp.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CheckNotNullInvocationHandler implements InvocationHandler {
	
	private Object target;
	
	public CheckNotNullInvocationHandler(Object target) {
		this.target = target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		Method targetMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
		if(targetMethod.isAnnotationPresent(CheckNotNull.class)) {
			return handleCheckNotNull(targetMethod, args);
		}
		return method.invoke(target, args);
	}
	
	private Object handleCheckNotNull(Method method, Object[] args) throws Exception {
		CheckNotNull annotation = method.getAnnotation(CheckNotNull.class);
		String[] parameterNames = annotation.parameterNames();
		
		for (int i=0; i<args.length; i++) {
			if(args[i]==null) {
				throw new IllegalArgumentException("Parameter " + parameterNames[i] + " is null!!!");
			}
		}
		System.out.println("Proxy 의 handleCheckNotNull() 호출");
		return method.invoke(target, args);
	}
}
