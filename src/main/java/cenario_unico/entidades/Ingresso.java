package cenario_unico.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ingresso {
    private int sala;
    private Date data;
    private String conteudo;
    private Pessoa dono;

    public Ingresso(int sala, Date data, String conteudo) {
        this.sala = sala;
        this.data = data;
        this.conteudo = conteudo;
    }

    public int getSala() {
        return sala;
    }

    public Date getData() {
        return data;
    }

    public String getConteudo() {
        return conteudo;
    }

    public Pessoa getDono() {
        return dono;
    }

    public void setDono(Pessoa dono) {
        this.dono = dono;
    }

    @Override
    public String toString() {
        String str = "Ingresso: Sala: " + sala + ", Data: " + dateToString(data) + ", Conteudo: " + conteudo;

        if (dono == null) {
            return str;
        } else {
            return str + ", Dono: " + dono.toString();
        }
    }

    private String dateToString(Date data){
        String formato = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
        return simpleDateFormat.format(data);
    }
}
