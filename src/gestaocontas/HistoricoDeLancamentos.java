package gestaocontas;

public class HistoricoDeLancamentos {
    private Lancamento[] lancamentos;
    private int ultimoLancamento;

    public HistoricoDeLancamentos() {
        this.lancamentos = new Lancamento[10];
        this.ultimoLancamento = 0;
    }

    public void registraLancamento(double valor, String descricao) {
        if (ultimoLancamento == lancamentos.length) {
            for (int i = 0; i < lancamentos.length - 1; i++) {
                lancamentos[i] = lancamentos[i + 1];
            }
            ultimoLancamento--;
        }
        lancamentos[ultimoLancamento] = new Lancamento(descricao, valor);
        ultimoLancamento++;
    }

    public String geraHistoricoDeLancamentos() {
        StringBuilder historico = new StringBuilder();
        for (int i = 0; i < ultimoLancamento; i++) {
            historico.append(lancamentos[i].getDescricao())
                    .append(": R$")
                    .append(lancamentos[i].getValor())
                    .append("\n");
        }
        return historico.toString();
    }
}
