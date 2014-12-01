import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SongLibrary {
	private List<Song> songList = new ArrayList<Song>();

	public void add(Song song) {
		songList.add(song);
	}
	
	public void clear() {
		songList.clear();
	}

	public List<Song> getSongLibrary() {
		return songList;
	}

	public List<String> getSongNameList() {
		return getSongNameList(getSongLibrary());
	}
	
	public List<String> getArtistList() {
		return getArtistList(getSongLibrary());
	}
	
	public List<String> getSongNameList(List<Song> songs) {
		List<String> list = new ArrayList<String>();
		for (Song song : songs) {
			if (!list.contains(song.getSongTitle()))
				list.add(song.getSongTitle());
		}
		return list;
	}

	public List<String> getArtistList(List<Song> songs) {
		List<String> list = new ArrayList<String>();
		for (Song song : songs) {
			if (!list.contains(song.getArtist()))
				list.add(song.getArtist());
		}
		return list;
	}

	public List<Song> getListFromSongSearch(String searchTerm) {
		List<Song> list = new ArrayList<Song>();
		for (Song song : songList) {
			if (matches(song.getSongTitle(), searchTerm))
				list.add(song);
		}
		return list;
	}

	public List<Song> getListFromArtistSearch(String searchTerm) {
		List<Song> list = new ArrayList<Song>();
		for (Song song : songList) {
			if (matches(song.getArtist(), searchTerm))
				list.add(song);
		}
		return list;
	}

	public boolean matches(String input, String regex) {
		if(regex == null || regex.isEmpty()) {
			return true;
		}
		return Pattern.matches(regex, input);
	}
}
