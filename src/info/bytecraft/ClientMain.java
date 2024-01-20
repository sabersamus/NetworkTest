package info.bytecraft;

import java.io.IOException;
import java.net.Inet4Address;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

import info.bytecraft.entity.CraftPlayer;
import info.bytecraft.entity.Player;
import info.bytecraft.packet.Packet;
import info.bytecraft.packet.Packet00PlayerConnect;
import info.bytecraft.packet.Packet02PlayerChat;
import info.bytecraft.packet.Packet03ServerMessage;
import info.bytecraft.packet.PacketType;

public class ClientMain
{
    
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String args[])
    {
        Client client = new Client();
        client.start();
        
        client.getKryo().register(Player.class);
        client.getKryo().register(CraftPlayer.class);
        client.getKryo().register(Inet4Address.class);
        client.getKryo().register(PacketType.class);
        client.getKryo().register(Packet.class);
        client.getKryo().register(Packet00PlayerConnect.class);
        client.getKryo().register(Packet02PlayerChat.class);
        client.getKryo().register(Packet03ServerMessage.class);
        
        try {
            client.connect(5000, JOptionPane.showInputDialog("Please enter a server ip"), 54555, 54777);
            
            
            String username = JOptionPane.showInputDialog(null, "Please enter a username");
            
            Player player = new CraftPlayer(username, client.getRemoteAddressTCP().getAddress());
            
            Packet00PlayerConnect packet = new Packet00PlayerConnect(player);
            
            client.addListener(new Listener() {
                public void received (Connection connection, Object object) {
                    if(!(object instanceof Packet))
                        return;
                    
                    if(object instanceof Packet02PlayerChat) {
                        Packet02PlayerChat packet = (Packet02PlayerChat) object;
                        Log.info("[chat] " + packet.getUsername() + ": " + packet.getMessage());
                    }else if(object instanceof Packet03ServerMessage) {
                       // System.out.println("The server has sent a server message");
                        Packet03ServerMessage packet = (Packet03ServerMessage)object;
                        Log.info(packet.getMessage());
                    }
                   
                }
             });
            
            client.sendTCP(packet);
            
            while(client.isConnected()) {
                while(scanner.hasNext()) {
                    
                    String message = scanner.nextLine();
                    
                    if(message.equalsIgnoreCase("exit")) {
                        client.stop();
                        System.exit(0);
                        return;
                    }
                    
                    Packet02PlayerChat chatPacket = new Packet02PlayerChat(packet.getUsername(), message);
                    
                    client.sendTCP(chatPacket);
                    
                }
            }
            
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Server is not running");
            System.exit(1);
        }
    }

}
