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
package org.apache.dubbo.common.extension;

/**
 * ExtensionFactory
 * 扩展工厂接口
 *
 *
 * SpiExtensionFactory                        |
 * SpringExtensionFactory                    |--- 实现 ---> ExtensionFactory(SPI注解)
 * AdaptiveExtensionFactory(Adaptive注解)    |
 */
@SPI
public interface ExtensionFactory {

    /**
     * Get extension.
     *
     * @param type object type.  扩展接口
     * @param name object name.  扩展名
     * @return object instance.  扩展对象实例
     *
     * 获得扩展对象 或 Spring的Bean对象
     */
    <T> T getExtension(Class<T> type, String name);

}
