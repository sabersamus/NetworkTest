package info.bytecraft.packet;

import info.bytecraft.entity.Player;

public class Packet00PlayerConnect extends Packet
{
    
    private String username;
    private String ip;
    private Player player;
    
    
    public Packet00PlayerConnect() {
        super(PacketType.PLAYER_CONNECT);
    }

    public Packet00PlayerConnect(Player p)
    {
        super(PacketType.PLAYER_CONNECT);
        
        this.setUsername(p.getName());
        this.setIp(p.getIp().getHostAddress());
        this.player = p;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }
    
    public Player getPlayer()
    {
        return player;
    }


    
}
