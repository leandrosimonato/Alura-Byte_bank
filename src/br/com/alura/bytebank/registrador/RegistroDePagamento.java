package br.com.alura.bytebank.registrador;

import br.com.alura.bytebank.model.Pagamento;
import br.com.alura.bytebank.model.Tipo;

import java.util.ArrayList;
import java.util.List;

public class RegistroDePagamento {

    private final List<Pagamento> PAGAMENTOS = new ArrayList<>();

    public void registra(List<Pagamento> pagamentos) {
        for (Pagamento pagamento : pagamentos) {
            verificaTipo(pagamento);
            salva(pagamento);
        }
    }

    private void verificaTipo(Pagamento pagamento) {
        if (pagamento.getTipo().equals(Tipo.CREDITO)) {
            double valorComAcrescimo = pagamento.getValor() * 1.02;
            pagamento.setValor(valorComAcrescimo);
        }

        if (pagamento.getTipo().equals(Tipo.DEBITO)) {
            double valorComAcrescimo = pagamento.getValor() * 1.001;
            pagamento.setValor(valorComAcrescimo);
        }
    }

    private void salva(Pagamento pagamento) {
        PAGAMENTOS.add(pagamento);
        System.out.println("Pagamento realizado " + pagamento);
    }

    public void exibeEfetuados() {
        if (PAGAMENTOS.isEmpty()) {
            System.out.println("Não tem pagamentos registrados");
        } else {
            System.out.println("Todos os pagamentos");
            for (Pagamento pagamentosRegistrado : PAGAMENTOS) {
                System.out.println(pagamentosRegistrado);
            }
        }
    }

}