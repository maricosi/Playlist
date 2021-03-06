package pt.uc.dei.aor.paj.fachada;


import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.paj.Music;
import pt.uc.dei.aor.paj.Playlist;
import pt.uc.dei.aor.paj.Utilizador;
import pt.uc.dei.aor.paj.DAO.PlaylistDAO;
import pt.uc.dei.aor.paj.DAO.UserDAO;


@Stateless
public class PlaylistFachada implements IntPlaylistFachada{

	@EJB
	private PlaylistDAO playlistDAO;
	@EJB
	private UserDAO userDAO;
	private static final Logger logger = LoggerFactory.getLogger(PlaylistFachada.class);

	public String save(String name, LocalDate date, String username) {
		String mensagemRegistoPlaylist="";
		Utilizador utilizador=userDAO.findUsername(username).get(0);
		Playlist playlist =new Playlist(name,date,utilizador);
		try{
			isPlaylistWithAllData(playlist);
			List<Playlist> playlistName= playlistDAO.findNameUtilizador(name, utilizador);
			if(playlistName.size()==0){
				playlistDAO.save(playlist);
				mensagemRegistoPlaylist="Playlist criada com sucesso!!!";
			} else if (playlistName.size()==1){
				mensagemRegistoPlaylist="Já existe uma Playlist com esse nome!";
			}
			return mensagemRegistoPlaylist;
		}catch (IllegalArgumentException e){
			logger.error( e.getMessage());
			return e.getMessage();
		}
	}

	private boolean isPlaylistWithAllData(Playlist playlist){
		boolean hasError = false;
		String mensagemErro="";

		if(playlist == null){
			hasError = true;
		}

		if (playlist.getName() == null || "".equals(playlist.getName().trim())){
			hasError = true;
			mensagemErro=mensagemErro+"Nome ";
		}

		if(playlist.getDate()== null){
			hasError = true;
			mensagemErro=mensagemErro+"Date ";
		}

		if (hasError){
			throw new IllegalArgumentException("Prencha o(s) campo(s): " + mensagemErro + "!!!");
		}

		return !hasError;
	}


	public String delete(String name, String username) {
		String mensagem="";
		try{
			Utilizador user1=userDAO.findUsername(username).get(0);
			List<Playlist> playlist=playlistDAO.findNameUtilizador(name, user1);

			if(playlist.size()==0){
				mensagem= "Playlist inexistente!!!";
			} else if (playlist.size()==1){
				playlistDAO.delete(playlist.get(0).getId(),Playlist.class);
				mensagem="A playlist "+playlist.get(0).getName()+ "foi apagada com sucesso!!!";
			}
			return mensagem;

		}catch (IllegalArgumentException e){
			logger.error( e.getMessage());
			return e.getMessage();
		}
	}

	@Override
	public List<Playlist> findAll() {
		return playlistDAO.all();
	}

	@Override
	public List <Playlist> findByNameUtilizador(String name, String username) {
		Utilizador user1=userDAO.findUsername(username).get(0);
		return playlistDAO.findNameUtilizador(name, user1);
	}

	@Override
	public List<Playlist> findByUtilizador(String username) {
		Utilizador user1=userDAO.findUsername(username).get(0);
		return playlistDAO.findByUtilizador(user1);
	}

	@Override
	public List<Playlist> orderByName(String username, String order) {
		Utilizador user1=userDAO.findUsername(username).get(0);
		List<Playlist> playlist1= null;
		if (order.equals("ASC")){
			playlist1=playlistDAO.orderByNameASC(user1);
		}else if(order.equals("DESC")){
			playlist1=playlistDAO.orderByNameDESC(user1);
		}
		return playlist1;
	}

	@Override
	public List<Playlist> orderByDate(String username, String order) {
		Utilizador user1=userDAO.findUsername(username).get(0);
		List<Playlist> playlist1= null;
		if (order.equals("ASC")){
			playlist1=playlistDAO.orderByDateASC(user1);
		}else if(order.equals("DESC")){
			playlist1=playlistDAO.orderByDateDESC(user1);
		}
		return playlist1;
	}

	@Override
	public List<Playlist> orderBySize(String username, String order) {
		Utilizador user1=userDAO.findUsername(username).get(0);
		List<Playlist> playlist1= null;
		if (order.equals("ASC")){
			playlist1=playlistDAO.orderBySizeASC(user1);
		}else if(order.equals("DESC")){
			playlist1=playlistDAO.orderBySizeDESC(user1);
		}
		return playlist1;
	}

	@Override
	public Playlist update(Playlist playlist) {
		playlistDAO.update(playlist);
		return playlist;
	}

	@Override
	public void deleteListUti(Utilizador utilizador) {
		List<Playlist> playlist=findByUtilizador(utilizador.getUsername());
		for (Playlist p:playlist){
			playlistDAO.delete(p.getId(), Playlist.class);
		}

	}

	public String adicionarMusic(List<Music> musicPlay, Playlist playlist){
		String mensagemMusicPlaylist="";
		try{
			int numeroMusic=0;
			if(musicPlay==null){
				mensagemMusicPlaylist="Selecione as musicas!!";
			} else if(musicPlay!=null){
				for(Music m:musicPlay){
					if(m.isCheck()==true){
						playlist.getMusics().add(m);
						numeroMusic++;
					}
				}
				playlistDAO.update(playlist);
				playlist.setSize(numeroMusic);
				playlistDAO.update(playlist);
				mensagemMusicPlaylist=numeroMusic+" musica(s) adicionada(s) com sucesso à playlist "+playlist.getName()+ "!!";
			}
			return mensagemMusicPlaylist;

		}catch (IllegalArgumentException e){
			logger.error( e.getMessage());
			return e.getMessage();
		}

	}
	
	public String retirarMusic(List<Music> musicPlay, Playlist playlist){
		String mensagemMusicPlaylist="";
		try{
			int numeroMusic=0;
			if(musicPlay==null){
				mensagemMusicPlaylist="Selecione as musicas!!";
			} else if(musicPlay!=null){
				for(Music m:musicPlay){
					if(m.isCheck()==true){
						playlist.getMusics().add(m);
						numeroMusic++;
					}
				}
				playlistDAO.update(playlist);
				mensagemMusicPlaylist=numeroMusic+" musica(s) adicionada(s) com sucesso à playlist "+playlist.getName()+ "!!";
			}
			return mensagemMusicPlaylist;

		}catch (IllegalArgumentException e){
			logger.error( e.getMessage());
			return e.getMessage();
		}

	}
	
	
	

	public List<Music> findMusicByPlaylist(int id){
		return playlistDAO.findMusicsByPlaylist(id);
	}


}







