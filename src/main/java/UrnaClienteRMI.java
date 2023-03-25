import Entity.Candidato;
import Entity.Pessoa;

import java.net.MalformedURLException;
import java.rmi.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UrnaClienteRMI  {
    private List<Pessoa> listaVotantes;
    private InterfVotacao votosStub;

    public UrnaClienteRMI() throws MalformedURLException, NotBoundException, RemoteException {
        this.listaVotantes = new ArrayList<>();
        votosStub = (InterfVotacao) Naming.lookup("rmi://127.0.0.1/sistemaVotos");
    }

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        UrnaClienteRMI urna = new UrnaClienteRMI();

        boolean finalizaUrna = true;
        while(finalizaUrna) {
            System.out.println(
                    "Digite 1 para adicionar mais um vontate a lista\n" +
                    "Digite 2 para fechar a urna\n");
            Scanner scanner = new Scanner(System.in);
            String leituraTeclado = scanner.nextLine();

            switch(Integer.parseInt(leituraTeclado)) {
                case 1:
                    try {
                        urna.votar();
                    } catch (Exception e) {
                        System.out.println("Os campos devem ser inseridos conforme o formato solicitado e não podem estar vazios. Encerrando cadastro do usuário");
                    }
                    break;
                case 2:
                    urna.votosStub.fecharUrna(urna.listaVotantes);
                    System.out.println("Urna foi fechada. Encerrando o programa");
                    finalizaUrna = false;
                    break;
                default:
                    System.out.println("Comando não encontrado. Tente novamente");
                    break;
            }
        }
    }

    public void votar() {
        Scanner scanner = new Scanner(System.in);
        Pessoa votante = new Pessoa();

        System.out.printf("Digite o nome do votante: ");
        votante.setNome(scanner.nextLine());
        //Seta CPF
        System.out.printf("Digite o cpf do votante: ");
        votante.setCpf(scanner.nextLine());
        //Seta Nascimento
        System.out.printf("Digite a data de nascimento do vontade (formato: YYYY-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
        votante.setDataNascimento(dataNascimento);
        //Seta candidato
        System.out.println("Digite o número do candidato (1, 2, 3 ou 4): ");
        Candidato candidato = Candidato.valueOf("CANDIDATO" + scanner.nextLine());
        votante.setCandidato(candidato);

        this.listaVotantes.add(votante);
        System.out.println("Votante adicionado com sucesso!");
        System.out.println("=====================");
    }
}
