package Modeloa;

import java.time.LocalDate;

/**
 * Partidua klaseak ligako partida bat irudikatzen du.
 * <p>
 * Partida batek honako informazioa gordetzen du:
 * </p>
 * <ul>
 * <li>Id_Par (Partiduaren ID-a datu-basean, eguneraketak egiteko)</li>
 * <li>Talde lokala</li>
 * <li>Talde bisitaria</li>
 * <li>Talde bakoitzaren emaitza (Integer, null izan daiteke jokatu ez bada)</li>
 * <li>Partidaren data</li>
 * <li>Partidaren ordua</li>
 * </ul>
 *
 * @author ZureIzena
 * @version 1.1
 */
public class Partidua {
	
	private int IdPar; 
	private String TaldeLokala;
	private String TaldeBisitari;
	private Integer ResultLokala; 
	private Integer ResulBisitari; 
	private LocalDate Data;
	private String Ordua;
	
	// Hasierako konstruktorea (Datu baseko datak eta orduak erabiltzeko)
	public Partidua(String taldeLokala, String taldeBisitari, Integer resultLokala, Integer resulBisitari, LocalDate data,
			String ordua) {
		super();
		this.TaldeLokala = taldeLokala;
		this.TaldeBisitari = taldeBisitari;
		this.ResultLokala = resultLokala;
		this.ResulBisitari = resulBisitari;
		this.Data = data;
		this.Ordua = ordua;
	}
	
	// Konstruktore berria (Pantailarako, DAO-k erabiltzen duena)
	public Partidua(int idPar, String taldeLok, String taldeBis, Integer resultLok, Integer resultBis) {
		this.IdPar = idPar; 
		this.TaldeLokala = taldeLok;
		this.TaldeBisitari = taldeBis;
		this.ResultLokala = resultLok; 
		this.ResulBisitari = resultBis; 
	}

	// Getters eta Setters ID-arentzat
	public int getIdPar() {
		return IdPar;
	}
	public void setIdPar(int idPar) {
		IdPar = idPar;
	}

	// Gainontzeko Getters eta Setters
	public String getTaldeLokala() {
		return TaldeLokala;
	}
	public void setTaldeLokala(String taldeLokala) {
		TaldeLokala = taldeLokala;
	}
	public String getTaldeBisitari() {
		return TaldeBisitari;
	}
	public void setTaldeBisitari(String taldeBisitari) {
		TaldeBisitari = taldeBisitari;
	}
	public Integer getResultLokala() {
		return ResultLokala;
	}
	public void setResultLokala(Integer resultLokala) {
		ResultLokala = resultLokala;
	}
	public Integer getResulBisitari() {
		return ResulBisitari;
	}
	public void setResulBisitari(Integer resulBisitari) {
		ResulBisitari = resulBisitari;
	}
	public LocalDate getData() {
		return Data;
	}
	public void setData(LocalDate data) {
		Data = data;
	}
	public String getOrdua() {
		return Ordua;
	}
	public void setOrdua(String ordua) {
		Ordua = ordua;
	}
}