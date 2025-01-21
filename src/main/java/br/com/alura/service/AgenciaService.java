package br.com.alura.service;

import br.com.alura.domain.Agencia;
import br.com.alura.domain.enums.SituacaoCadastral;
import br.com.alura.domain.http.AgenciaHttp;
import br.com.alura.exception.AgenciaNaoAtivaOuNaoEncontradaException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

public class AgenciaService {

    @RestClient
    private SituacaoCadastralHttpService situacaoCadastralHttpService;

    private List<Agencia> agencias = new ArrayList<>();

    public void cadastrar(Agencia agencia) {
        AgenciaHttp agenciaHttp =
                situacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());
        if (agenciaHttp.getSituacaoCadastral() == SituacaoCadastral.ATIVO) {
            agencias.add(agencia);
        } else {
            throw new AgenciaNaoAtivaOuNaoEncontradaException();
        }
    }
}
