package info.bytecraft.entity;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class CraftPlayer implements Player
{
    private String name;
    private String ip;
    
    public CraftPlayer() {}

    public CraftPlayer(String name, InetAddress ip)
    {
        this.setName(name);
        this.setIp(ip.getHostName());
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public InetAddress getIp()
    {
        try {
            return InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            return InetAddress.getLoopbackAddress();
        }
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

}
