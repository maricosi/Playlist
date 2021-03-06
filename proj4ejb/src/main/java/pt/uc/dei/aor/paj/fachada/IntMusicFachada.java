package pt.uc.dei.aor.paj.fachada;

import java.util.List;

import javax.ejb.Local;

import pt.uc.dei.aor.paj.Music;
import pt.uc.dei.aor.paj.Utilizador;

@Local
public interface IntMusicFachada {
	public abstract String save(String title, String artist, String album, String path, int year, String username);
	public abstract String update(Music music);
	public abstract String delete(Music music);
	public abstract Music find(int entityID);
	public abstract List<Music> findAll();
	public abstract List<Music> findAllByUtilizador(String username);
	public abstract List<Music> findAllByTitleArtist(String title, String artist);
	public abstract String idMusicsUtilizadorNull(Utilizador utilizador);
	public abstract String idMusicUtilizadorNull(Music music);
}
