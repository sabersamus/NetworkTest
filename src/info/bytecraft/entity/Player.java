package info.bytecraft.entity;

import java.net.InetAddress;

public interface Player
{
    
    public String getName();
    
    public InetAddress getIp();
    
    public void setName(String name);
    
    public void setIp(String address);
}
