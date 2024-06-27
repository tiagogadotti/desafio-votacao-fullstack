export interface PautaInfo {
  pautaId: number,
  pautaTitulo: string,
  pautaDescricao: string,
  sessaoId: number,
  sessaoInicio: Date,
  sessaoDuracao: number,
  sessaoTotalVotosSim: number,
  sessaoTotalVotosNao: number
}
