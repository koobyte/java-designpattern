package com.belonk.designpattern.proxy;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by sun on 2020/12/27.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class JdkDynamicProxyDemo {
	//~ Static fields/constants/initializer


	//~ Instance fields


	//~ Constructors


	//~ Methods

	public static void main(String[] args) {
		// 目标对象，它会被动态生成代理
		ISubject subject = new RealSubjectImpl();
		/*
		 * JDK动态代理只能代理实现了某些接口的类，即：代理目标对象必须实现接口，否则不能为其创建代理对象。
		 *
		 * 创建JDK代理对象，需要三个参数：
		 * ClassLoader loader: 指定当前目标对象的类加载器
		 * Class<?>[] interfaces: 指定代理的接口
		 * InvocationHandler h：调用处理器，每个代理对象都有一个关联的调用处理器, 在代理对象上调用方法时，会执行这个处理器。invoke方法有三个参数：
		 *     1、proxy：生成的代理对象
		 *     2、method：当前调用的方法
		 *     3、args1：当前调用的方法的参数
		 */
		ISubject proxyedSubject = (ISubject) Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), (proxy, method, args1) -> {
			System.out.println("代理对象：" + proxy.getClass());
			System.out.println("方法参数：" + Arrays.toString(args));
			System.out.println("before request...");
			Object result = method.invoke(subject, args1);
			System.out.println("after request...");
			return result;
		});
		proxyedSubject.request1("张三");
		proxyedSubject.request2("李四");
	}
}

interface ISubject {
	void request1(String name);

	void request2(String name);
}

class RealSubjectImpl implements ISubject {
	@Override
	public void request1(String name) {
		System.out.println("request : " + name);
	}

	@Override
	public void request2(String name) {
		System.out.println("request : " + name);
	}
}