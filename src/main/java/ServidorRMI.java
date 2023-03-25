import Entity.Candidato;
import Entity.Pessoa;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServidorRMI extends UnicastRemoteObject implements InterfVotacao{
    private List<Pessoa> totalVotos;

    public ServidorRMI() throws RemoteException {
        super();
        this.totalVotos = new ArrayList<>();
    }

    public static void main(String[] args) {
        try {
            ServidorRMI servidorRMI = new ServidorRMI();
            Registry servidorRegistry = LocateRegistry.createRegistry(1099);
            Naming.rebind("sistemaVotos", servidorRMI);
            System.out.println("Aguardando conexão clientes");
            while(true) {
                Thread.sleep(5000);
                servidorRMI.contarVotos();
            }
        } catch (RemoteException | MalformedURLException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Servidor não pode ser inicializado. Tente novamente.");
        }
    }

    @Override
    public void fecharUrna(List<Pessoa> votosUrna) {
        System.out.println("ENTRANDO NO MÉTODO FECHAR URNA");
        totalVotos.addAll(votosUrna);
    }

    public void contarVotos() {
        Arrays.stream(Candidato.values()).forEach(candidato -> {
            System.out.printf("Total de votantes do " + candidato + ": ");
            Long contaVontantes = totalVotos.stream().filter(pessoa -> pessoa.getCandidato().equals(candidato)).count();
            System.out.println(contaVontantes);
        });
        System.out.println("=====================");

    }
}
