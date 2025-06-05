package gestaocaixaeletronico;

import java.util.Scanner;

public class Terminal {

    private Caixa meuCaixa;
    private int modoAtual = 1;
    private final int senhaAdmin = 3323;

    public Terminal(CadastroContas bdContas) {
        this.meuCaixa = new Caixa(this, bdContas);
    }

    public void iniciaOperacao() {
        while (true) {
            this.solicitaModo();
            if (modoAtual == 0) {
                if (!menuManutencao()) break;
            } else {
                if (!menuUsuario()) break;
            }
        }
        System.out.println("Encerrando o terminal.");
    }

    private boolean menuManutencao() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=== Menu de Manutenção ===");
            System.out.println("1 - Consultar saldo do caixa");
            System.out.println("2 - Recarregar caixa");
            System.out.println("9 - Voltar ao menu de modo");
            System.out.println("0 - Sair do terminal");
            int opcao = this.getInt("opção desejada");

            switch (opcao) {
                case 1:
                    System.out.println("Saldo atual do caixa: " + meuCaixa.getSaldoCaixa());
                    break;
                case 2:
                    int senha = this.getInt("Senha do Administrador");
                    if (senha == senhaAdmin) {
                        this.meuCaixa.recarrega();
                        System.out.println("Caixa recarregado com sucesso.");
                    } else {
                        System.out.println("Senha de administrador incorreta. Operação não permitida.");
                    }
                    break;
                case 9:
                    return true;
                case 0:
                    return false;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private boolean menuUsuario() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=== Menu do Usuário ===");
            System.out.println("1 - Consulta Saldo");
            System.out.println("2 - Saque");
            System.out.println("3 - Depósito em dinheiro");
            System.out.println("4 - Depósito em cheque");
            System.out.println("5 - Transferência");
            System.out.println("6 - Consultar extrato");
            System.out.println("9 - Voltar ao menu de modo");
            System.out.println("0 - Sair do terminal");
            int opcao = this.getInt("opção desejada");

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
                case 9:
                    return true;
                case 0:
                    return false;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void solicitaModo() {
        Scanner sc = new Scanner(System.in);
        int modo;
        do {
            System.out.println("Escolha o modo do terminal:");
            System.out.println("0 - Manutenção (Administrador)");
            System.out.println("1 - Operação Normal");
            System.out.print("Modo: ");
            while (!sc.hasNextInt()) {
                System.out.println("Digite 0 ou 1.");
                sc.next();
            }
            modo = sc.nextInt();
        } while (modo != 0 && modo != 1);
        this.setModo(modo);
    }

    public void setModo(int modo) {
        if (modo == 0 || modo == 1) {
            this.modoAtual = modo;
        }
    }

    private int getInt(String mensagem) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entre com " + mensagem + ":");
        while (!sc.hasNextInt()) {
            System.out.println("Valor inválido. Tente novamente.");
            sc.next();
        }
        return sc.nextInt();
    }
}