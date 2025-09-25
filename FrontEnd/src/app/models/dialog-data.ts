export type TipoOggetto = 'foto' | 'disegno' | 'illustrazione';

export interface DialogData {
  type: TipoOggetto;
  oggetto: any;
}