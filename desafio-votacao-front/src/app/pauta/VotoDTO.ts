export interface VotoDTO {
  associadoId: number;
  sessaoId: number;
  opcao: Opcao;
}

enum Opcao{
  SIM = "SIM",NAO = "NÃO"
}
