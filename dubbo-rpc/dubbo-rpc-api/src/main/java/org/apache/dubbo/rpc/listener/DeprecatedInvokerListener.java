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
package org.apache.dubbo.rpc.listener;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;

import static org.apache.dubbo.rpc.Constants.DEPRECATED_KEY;

/**
 * DeprecatedProtocolFilter 引用废弃的服务时,打印错误日志
 *
 * 远程引用服务配置: <dubbo:service interface="com.alibaba.dubbo.demo.DemoService" ref="demoService" deprecated="true" />
 * 本地引用服务配置:
 *    <dubbo:reference id="demoService" nterface="com.alibaba.dubbo.demo.DemoService" protocol="injvm">
 *        <dubbo:parameter key="deprecated" value="true" />
 *    </dubbo:reference>
 */
@Activate(DEPRECATED_KEY) // 基于 Dubbo SPI Activate 机制加载
public class DeprecatedInvokerListener extends InvokerListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeprecatedInvokerListener.class);

    @Override
    public void referred(Invoker<?> invoker) throws RpcException {
        if (invoker.getUrl().getParameter(DEPRECATED_KEY, false)) {
            LOGGER.error("The service " + invoker.getInterface().getName() + " is DEPRECATED! Declare from " + invoker.getUrl());
        }
    }

}
