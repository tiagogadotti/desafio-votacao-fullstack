package com.desafio.votacao.pauta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PautaController.class)
class PautaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PautaService pautaService;

    private Pauta pauta;

    @BeforeEach
    void setUp() {
        pauta = new Pauta();
        pauta.setId(1L);
        pauta.setTitulo("Pauta de Teste");
        pauta.setDescricao("Descrição da Pauta de Teste");
    }

    @Test
    void deveEncontrarPautaPorId() throws Exception {
        when(pautaService.findById(anyLong())).thenReturn(pauta);

        mockMvc.perform(get("/api/v1/pauta")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":1,\"titulo\":\"Pauta de Teste\",\"descricao\":\"Descrição da Pauta de Teste\"}"));
    }

    @Test
    void deveEncontrarTodasAsPautas() throws Exception {
        List<Pauta> pautas = List.of(pauta);
        when(pautaService.findAll()).thenReturn(pautas);

        mockMvc.perform(get("/api/v1/pautas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"id\":1,\"titulo\":\"Pauta de Teste\",\"descricao\":\"Descrição da Pauta de Teste\"}]"));
    }

    @Test
    void deveEncontrarTodasAsInfosDasPautas() throws Exception {
        PautaInfoDTO pautaInfoDTO = new PautaInfoDTO();
        pautaInfoDTO.setPautaId(1L);
        pautaInfoDTO.setPautaTitulo("Pauta de Teste");
        pautaInfoDTO.setPautaDescricao("Descrição da Pauta de Teste");
        pautaInfoDTO.setSessaoId(1L);
        pautaInfoDTO.setSessaoDuracao(60);
        pautaInfoDTO.setSessaoInicio(null);

        List<PautaInfoDTO> pautaInfos = List.of(pautaInfoDTO);
        when(pautaService.findAllInfo()).thenReturn(pautaInfos);

        mockMvc.perform(get("/api/v1/pautas/info"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"pautaId\":1,\"pautaTitulo\":\"Pauta de Teste\",\"pautaDescricao\":\"Descrição da Pauta de Teste\",\"sessaoId\":1,\"sessaoDuracao\":60,\"sessaoInicio\":null,\"sessaoTotalVotosSim\":null,\"sessaoTotalVotosNao\":null}]"));
    }

    @Test
    void deveSalvarPauta() throws Exception {
        when(pautaService.save(any(Pauta.class))).thenReturn(pauta);

        mockMvc.perform(post("/api/v1/pauta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"Pauta de Teste\",\"descricao\":\"Descrição da Pauta de Teste\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":1,\"titulo\":\"Pauta de Teste\",\"descricao\":\"Descrição da Pauta de Teste\"}"));
    }
}
