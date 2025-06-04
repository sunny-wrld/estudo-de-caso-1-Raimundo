package gestaocaixaeletronico;

import java.util.Scanner;

public class Terminal {

    private Caixa meuCaixa;
    private int modoAtual = 1; // Modo padrão como cliente

    public Terminal(CadastroContas bdContas) {
        this.meuCaixa = new Caixa(this, bdContas);
    }

    public void iniciaOperacao() {
        int opcao;
        opcao = this.getOpcao();

        while (opcao != 0) {
            switch (opcao) {
                case 1:
                    double saldo = this.meuCaixa.verificaSaldo(
                            this.getInt("Número da Conta"),
                            this.getInt("Senha"));
                    if (saldo != -1) {
                        System.out.println("Saldo Atual: " + saldo);
                    } else {
                        System.out.println("Conta/senha inválida");
                    }
                    break;
                case 2:
                    boolean saque = this.meuCaixa.efetuaSaque(
                            this.getInt("Número da Conta"),
                            this.getInt("Valor"),
                            this.getInt("Senha"));
                    if (saque) {
                        System.out.println("Saque realizado");
                    } else {
                        System.out.println("Pedido de saque recusado");
                    }
                    break;
                case 3:
                    boolean depositoDinheiro = this.meuCaixa.efetuaDepositoDinheiro(
                            this.getInt("Número da Conta"),
                            this.getInt("Valor"));
                    if (depositoDinheiro) {
                        System.out.println("Depósito em dinheiro realizado");
                    } else {
                        System.out.println("Depósito recusado");
                    }
                    break;
                case 4:
                    boolean depositoCheque = this.meuCaixa.efetuaDepositoCheque(
                            this.getInt("Número da Conta"),
                            this.getInt("Valor"));
                    if (depositoCheque) {
                        System.out.println("Depósito em cheque realizado");
                    } else {
                        System.out.println("Depósito recusado");
                    }
                    break;
                case 5:
                    boolean transferencia = this.meuCaixa.efetuaTransferencia(
                            this.getInt("Conta de Origem"),
                            this.getInt("Conta de Destino"),
                            this.getInt("Valor"),
                            this.getInt("Senha"));
                    if (transferencia) {
                        System.out.println("Transferência realizada");
                    } else {
                        System.out.println("Transferência recusada");
                    }
                    break;
                case 6:
                    String extrato = this.meuCaixa.consultaExtrato(
                            this.getInt("Número da Conta"),
                            this.getInt("Senha"));
                    if (extrato != null) {
                        System.out.println("Extrato:\n" + extrato);
                    } else {
                        System.out.println("Conta/senha inválida");
                    }
                    break;
                case 7:
                    this.meuCaixa.recarrega();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
            opcao = this.getOpcao();
        }
    }

    public void setModo(int modo) {
        if (modo == 0 || modo == 1) {
            this.modoAtual = modo;
        }
    }

    private int getOpcao() {
        int opcao;
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Consulta Saldo");
            System.out.println("2 - Saque");
            System.out.println("3 - Depósito em dinheiro");
            System.out.println("4 - Depósito em cheque");
            System.out.println("5 - Transferência");
            System.out.println("6 - Consultar extrato");
            System.out.println("7 - Recarregar Caixa");
            System.out.println("0 - Sair");
            opcao = this.getInt("opção desejada");
            if (opcao < 0 || opcao > 7) {
                System.out.println("Opção inválida.");
                opcao = 0;
            }
        } while (opcao == 0);

        return opcao;
    }

    private int getInt(String mensagem) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entre com " + mensagem + ":");
        if (sc.hasNextInt()) {
            return sc.nextInt();
        } else {
            System.out.println("Erro na leitura de dados.");
            sc.next(); // descarta entrada inválida
            return 0;
        }
    }
}
