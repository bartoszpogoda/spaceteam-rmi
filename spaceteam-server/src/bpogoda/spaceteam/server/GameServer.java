package bpogoda.spaceteam.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import bpogoda.spaceteam.bean.ControlPanel;

public interface GameServer extends Remote {
    String sayHello() throws RemoteException;
    
    boolean connectCrew(CrewType crewType, ControlPanel controlPanel) throws RemoteException;
    
}
