import Entity.Pessoa;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface InterfVotacao extends Remote {

    public void fecharUrna(List<Pessoa> votosUrna) throws RemoteException;
}
