package pt.uc.dei.aor.paj;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.paj.fachada.IntMusicFachada;


@Named
@RequestScoped
public class MusicWeb {

	@EJB
	private IntMusicFachada music;
	@Inject
	private Login login;
	private String artist;
	private String title;
	private String album;
	private int year;
	private String url;
	private String mensagem="";
	private List<Music> procuraMusic;
	private Playlist playlist;
	private  boolean table = false;


	public MusicWeb() {
		super();
	}
	
	public List<Music> findAll(){
		return music.findAll();
	}
	
	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public void save(){
		this.mensagem=music.save(title, artist, album, url, year, login.getUsername());
	}
	
	public void findByTitleArtist (){
		this.procuraMusic=music.findAllByTitleArtist(title, artist);
		if(procuraMusic.size()==0){
			this.mensagem="Critério(s) de procura sem resultados";
		}
	}
	
	public List<Music> findAllByUtilizador(){
		return music.findAllByUtilizador(login.getUsername());
	}
	
	public List<Music> getProcuraMusic() {
		return procuraMusic;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isTable() {
		return table;
	}

	public void setTable(boolean table) {
		this.table = table;
	}

	public void showTable(){
		this.table=true;
	}



}
