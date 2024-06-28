export interface VotoDTO {
  associadoId: number;
  sessaoId: number;
  opcao: Opcao;
}

export enum Opcao{
  SIM = "SIM",NAO = "NÃO"
}
