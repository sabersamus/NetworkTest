package info.bytecraft.packet;

public abstract class Packet
{
    private final PacketType packetType;
    private int packetId;
    private static int packetIds;

    public Packet() {
        this.packetType = PacketType.INVALID;
        this.packetId = packetIds++;
    }
    
    public Packet(PacketType packetType)
    {
        this.packetType = packetType;
        this.packetId = packetIds++;
    }

    public PacketType getPacketType()
    {
        return packetType;
    }

    public final int getPacketId()
    {
        return packetId;
    }

}
