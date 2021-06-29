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
package org.apache.dubbo.remoting.zookeeper;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.config.configcenter.ConfigItem;

import java.util.List;
import java.util.concurrent.Executor;

public interface ZookeeperClient {
    
    /**
     * 节点创建
     * @param path         节点路径
     * @param ephemeral    是否临时节点
     */
    void create(String path, boolean ephemeral);
    
    /**
     * 节点删除
     * @param path
     */
    void delete(String path);

    List<String> getChildren(String path);
    
    /**
     * 添加 ChildListener
     * @param path
     * @param listener
     * @return    子节点列表
     */
    List<String> addChildListener(String path, ChildListener listener);

    /**
     * @param path:    directory. All of child of path will be listened.
     * @param listener
     */
    void addDataListener(String path, DataListener listener);

    /**
     * @param path:    directory. All of child of path will be listened.
     * @param listener
     * @param executor another thread
     */
    void addDataListener(String path, DataListener listener, Executor executor);

    void removeDataListener(String path, DataListener listener);
    
    /**
     *  移除 ChildListener
     * @param path
     * @param listener
     */
    void removeChildListener(String path, ChildListener listener);

    void addStateListener(StateListener listener);

    void removeStateListener(StateListener listener);
    
    /**
     * @return  是否连接
     */
    boolean isConnected();
    
    /**
     * 关闭
     */
    void close();
    
    /**
     * 获取注册中心 URL
     * @return
     */
    URL getUrl();

    void create(String path, String content, boolean ephemeral);

    void createOrUpdate(String path, String content, boolean ephemeral, int ticket);

    String getContent(String path);

    ConfigItem getConfigItem(String path);

    boolean checkExists(String path);

}
