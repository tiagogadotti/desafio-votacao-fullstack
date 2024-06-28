package com.desafio.votacao.associado;

import com.desafio.votacao.cpf.CpfService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AssociadoService {

    private final AssociadoRepository associadoRepository;
    private final CpfService cpfService;

    public AssociadoService(AssociadoRepository associadoRepository, CpfService cpfService) {
        this.associadoRepository = associadoRepository;
        this.cpfService = cpfService;
    }

    public Associado findById(Long id) throws NoSuchElementException {
        return associadoRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Associado save(Associado associado) {
        boolean aptoParaVotar = cpfService.ableToVote().get("status").equals("ABLE_TO_VOTE");
        associado.setAptoParaVotar(aptoParaVotar);
        if (!cpfService.validar(associado.getCpf())){
            throw new IllegalArgumentException("CPF inválido");
        }
        return associadoRepository.save(associado);
    }

    public List<Associado> findAll() {
        return associadoRepository.findAll();
    }
}
