package bpogoda.spaceteam.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import bpogoda.spaceteam.bean.ControlPanel;
import bpogoda.spaceteam.server.command.Command;

public interface CrewGameServer extends Remote {
    boolean connectCrew(CrewType crewType, List<Command> randomCommands) throws RemoteException;
    
}
