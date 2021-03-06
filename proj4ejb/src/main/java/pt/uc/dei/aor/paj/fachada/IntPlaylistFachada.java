package pt.uc.dei.aor.paj.fachada;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Local;

import pt.uc.dei.aor.paj.Music;
import pt.uc.dei.aor.paj.Playlist;
import pt.uc.dei.aor.paj.Utilizador;


@Local
public interface IntPlaylistFachada {

	public abstract Playlist update(Playlist playlist);

	public abstract List<Playlist> findByNameUtilizador(String name,String username );
	public abstract List<Playlist> findByUtilizador(String username );
	public abstract List<Playlist> orderByName(String username, String order );
	public abstract List<Playlist> orderByDate(String username, String order);
	public abstract List<Playlist> orderBySize(String username,  String order );
	public abstract void deleteListUti(Utilizador utilizador);
	public abstract String adicionarMusic(List<Music> musicPlay, Playlist playlist);
	public abstract List<Playlist> findAll();
	public abstract String save(String name, LocalDate date, String username);
	public abstract String delete(String name, String username);
	public List<Music> findMusicByPlaylist(int id);

}