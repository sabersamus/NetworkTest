package info.bytecraft;

import java.io.IOException;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import info.bytecraft.entity.CraftPlayer;
import info.bytecraft.entity.Player;
import info.bytecraft.packet.*;

public class ServerMain {
    
    private static List<Player> players = new ArrayList<Player>();
    
    public static void main(String[] args) {
        Server server = new Server();
        
        server.start();
        Log.set(Log.LEVEL_INFO);
        
        server.getKryo().register(Player.class);
        server.getKryo().register(CraftPlayer.class);
        server.getKryo().register(Inet4Address.class);
        server.getKryo().register(Packet.class);
        server.getKryo().register(PacketType.class);
        server.getKryo().register(Packet00PlayerConnect.class);
        server.getKryo().register(Packet02PlayerChat.class);
        server.getKryo().register(Packet03ServerMessage.class);
        
        Scanner scanner = new Scanner(System.in);
        try {
            server.bind(54555, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if(!(object instanceof Packet))
               System.out.println(object);
            }
         });
        
        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if(object instanceof Packet)
                {
                    if(object instanceof Packet00PlayerConnect) {
                        Packet00PlayerConnect packet = (Packet00PlayerConnect) object;
                        
                        Player player = packet.getPlayer();
                        
                        players.add(player);
                        
                        Log.info(packet.getPlayer().getName() + " has connected to the server using ip " + packet.getPlayer().getIp().getHostAddress());
                    }else if(object instanceof Packet02PlayerChat) {
                        Packet02PlayerChat packet = (Packet02PlayerChat) object;
                        
                        String message = packet.getMessage();
                        
                        if(message.equalsIgnoreCase("/who")) {
                            message = "Players Online: ";
                            for(int i = 0; i < players.size(); i++) {
                                String append = ", ";
                                if(i + 1 == players.size()) {
                                    append = "";
                                }
                                message += players.get(i).getName() + append;
                            }
                            
                            Packet03ServerMessage p = new Packet03ServerMessage(message, false);
                            connection.sendTCP(p);
                            return;
                        }
                        
                        
                        server.sendToAllTCP(packet);
                    }
                }
            }
         });
        
        while(scanner.hasNext()) {
            if(scanner.next().equalsIgnoreCase("exit")) {
                server.stop();
                scanner.close();
                System.exit(0);
            }
            
        }
        
        Log.info("This is a test");
        
        
    }
    
    
    
    private class MyLogger extends com.esotericsoftware.minlog.Log.Logger {
        
    }

}
