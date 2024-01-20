package info.bytecraft.packet;

public class Packet02PlayerChat extends Packet
{
    
    private String username;
    private String message;

    public Packet02PlayerChat(String username, String message)
    {
        super(PacketType.PLAYER_CHAT);
        this.setUsername(username);
        this.setMessage(message);
    }
    
    public Packet02PlayerChat() 
    {
        this("", "");
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
    
    

}
