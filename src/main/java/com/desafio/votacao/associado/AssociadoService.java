package com.desafio.votacao.associado;

import com.desafio.votacao.cpf.CpfService;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.http.ResponseEntity;
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
        return associadoRepository.findById(id).get();
    }

    public Associado save(Associado associado) {
        boolean aptoParaVotar = cpfService.ableToVote(associado.getCpf()).get("status").equals("ABLE_TO_VOTE");
        associado.setAptoParaVotar(aptoParaVotar);
        return associadoRepository.save(associado);
    }

    public List<Associado> findAll() {
        return associadoRepository.findAll();
    }
}
