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

/**
 * Exporter. (API/SPI, Prototype, ThreadSafe)
 * Invoker 暴露服务在 Protocol 上的对象,可以在不同的Protocol协议实现取消暴露逻辑
 * @see org.apache.dubbo.rpc.Protocol#export(Invoker)
 * @see org.apache.dubbo.rpc.ExporterListener
 * @see org.apache.dubbo.rpc.protocol.AbstractExporter
 *
 * InjvmExporter --- 继承 --->  AbstractExporter  |
 *                                                 ---  实现 ---> Exporter
 *                      ListenerExporterWrapper  |
 */
public interface Exporter<T> {

    /**
     * get invoker.
     * 获取对应的 Invoker 实例
     * @return invoker
     */
    Invoker<T> getInvoker();

    /**
     * unexport.
     *  取消暴露
     * <p>
     * <code>
     * getInvoker().destroy();
     * </code>
     */
    void unexport();

}
