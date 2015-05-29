package pt.uc.dei.aor.paj.fachada;

import java.util.List;

import javax.ejb.Local;

import pt.uc.dei.aor.paj.Music;
import pt.uc.dei.aor.paj.User;

@Local
public interface IntMusicFachada {
	public abstract String save(String title, String artist, String album, String path, int year, User user);

	public abstract Music update(Music music);

	public abstract void delete(Music music);

	public abstract Music find(int entityID);

	public abstract List<Music> findAll();
}
