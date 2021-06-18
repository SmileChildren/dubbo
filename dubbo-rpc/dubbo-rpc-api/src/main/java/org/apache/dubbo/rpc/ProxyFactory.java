/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.rpc;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

import static org.apache.dubbo.rpc.Constants.PROXY_KEY;

/**
 * ProxyFactory. (API/SPI, Singleton, ThreadSafe)
 *
 * JavassistProxyFactory   |
 *                          ---  继承 ---> AbstractProxyFactory  |
 * JdkProxyFactory         |                                      ---  实现 ---> ProxyFactory
 *                                      StubProxyFactoryWrapper |
 *
 *  由上可以看出 Dubbo 支持 Javassist 和 JDK Proxy 两种方式生成代理
 */
@SPI("javassist")
public interface ProxyFactory {

    /**
     * create proxy.
     *  创建proxy,引用服务时调用[invoker参数表示: COnsumer 对 Provider 调用的Invoker]
     * @param invoker
     * @return proxy
     */
    @Adaptive({PROXY_KEY})
    <T> T getProxy(Invoker<T> invoker) throws RpcException;

    /**
     * create proxy.
     * 创建proxy,引用服务时调用{generic: 区分泛型}
     * @param invoker
     * @return proxy
     */
    @Adaptive({PROXY_KEY})
    <T> T getProxy(Invoker<T> invoker, boolean generic) throws RpcException;

    /**
     * create invoker.
     * 创建Invoker,暴露服务时调用
     * @param <T>
     * @param proxy  Service对象
     * @param type   Service接口类型
     * @param url    Service对应的 Dubbo URL
     * @return invoker
     */
    @Adaptive({PROXY_KEY})
    <T> Invoker<T> getInvoker(T proxy, Class<T> type, URL url) throws RpcException;

}
