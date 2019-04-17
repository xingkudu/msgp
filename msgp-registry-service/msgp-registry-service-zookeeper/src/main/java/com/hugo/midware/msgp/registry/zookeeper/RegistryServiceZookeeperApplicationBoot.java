package com.hugo.midware.msgp.registry.zookeeper;

import org.apache.zookeeper.server.quorum.QuorumPeerMain;

/**
 * @ClassName RegistryServiceZookeeperApplicationBoot
 * @Description zookeeper 启动入口
 * @Author hugo
 * @Date 2019-04-12 14:47
 * @Version 1.0
 **/
public class RegistryServiceZookeeperApplicationBoot {
    public static void main(String[] args) {
        QuorumPeerMain.main(new String[]{"/Users/hugo/Documents/zoo.cfg"});
    }
}
