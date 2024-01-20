package info.bytecraft.packet;

public enum PacketType {
    INVALID(-01),
    PLAYER_CONNECT(00),
    PLAYER_DISCONNECT(01),
    PLAYER_CHAT(02),
    SERVER_MESSAGE(03);
    
    
    private final int id;
    
    private PacketType(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }
    
    public static PacketType getPacketTypeFromId(int id)
    {
        for(PacketType type: values())
        {
            if(type.id == id)
            {
                return type;
            }
        }
        return INVALID;
    }
    
}
