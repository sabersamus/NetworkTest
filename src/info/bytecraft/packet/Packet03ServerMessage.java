package info.bytecraft.packet;

public class Packet03ServerMessage extends Packet
{
    
    private boolean global;
    private String message;
    
    public Packet03ServerMessage()
    {
        super(PacketType.SERVER_MESSAGE);
    }
    
    public Packet03ServerMessage(String message, boolean global)
    {
        super(PacketType.SERVER_MESSAGE);
        this.message = message;
        this.global = global;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public boolean isGlobal()
    {
        return global;
    }

    public void setGlobal(boolean global)
    {
        this.global = global;
    }
}
