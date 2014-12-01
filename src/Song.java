public class Song {

	private String songTitle;
	private String artist;

	public Song(String aSongTitle, String anArtistName) {
		songTitle = aSongTitle;
		artist = anArtistName;
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

}