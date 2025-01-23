package br.com.alura.service;

import br.com.alura.domain.Agencia;
import br.com.alura.domain.enums.SituacaoCadastral;
import br.com.alura.domain.http.AgenciaHttp;
import br.com.alura.exception.AgenciaNaoAtivaOuNaoEncontradaException;
import br.com.alura.repository.AgenciaRepository;
import br.com.alura.service.http.SituacaoCadastralHttpService;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AgenciaService {

    @RestClient
    private SituacaoCadastralHttpService situacaoCadastralHttpService;

    private final AgenciaRepository agenciaRepository;

    public AgenciaService(AgenciaRepository agenciaRepository) {
        this.agenciaRepository = agenciaRepository;
    }

    public void cadastrar(Agencia agencia) {
        AgenciaHttp agenciaHttp =
                situacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());
        if (agenciaHttp != null && agenciaHttp.getSituacaoCadastral().equals(SituacaoCadastral.ATIVO)) {
            agenciaRepository.persist(agencia);
        } else {
            throw new AgenciaNaoAtivaOuNaoEncontradaException();
        }
    }

    public Agencia buscarPorId(Long id) {
        return agenciaRepository.findById(id);
    }

    public void deletar(Long id) {
        agenciaRepository.deleteById(id);
    }

    public void alterar(Agencia agencia) {
        deletar(agencia.getId());
        cadastrar(agencia);
    }
}
