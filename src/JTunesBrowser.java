import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileFilter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class JTunesBrowser extends JFrame {

	private static final long serialVersionUID = 236228633408355518L;

	private SongLibrary songLibrary = new SongLibrary();

	private JList<String> artistList;
	private JList<String> songList;
	private JTextField searchField;
	private JRadioButton artistRadio;
	private JRadioButton songRadio;

	public JTunesBrowser(String name) {
		super(name);
		setSize(600, 400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setUpWindow();
		resetWindow();
	}

	public static void main(String[] args) {
		JTunesBrowser frame = new JTunesBrowser("jTunes Browser");
		frame.setVisible(true);
	}

	private void resetWindow() {
		searchField.setText("");
		artistRadio.setSelected(true);
		songRadio.setSelected(false);

		String[] artists = songLibrary.getArtistList().toArray(new String[0]);
		String[] songs = songLibrary.getSongNameList().toArray(new String[0]);
		artistList.setListData(artists);
		songList.setListData(songs);
	}

	private void searchSongs(String searchTerm) {
		List<Song> songs = songLibrary.getListFromSongSearch(searchTerm);
		String[] artists = songLibrary.getArtistList(songs).toArray(
				new String[0]);
		String[] songNames = songLibrary.getSongNameList(songs).toArray(
				new String[0]);
		artistList.setListData(artists);
		songList.setListData(songNames);
	}

	private void searchArtists(String searchTerm) {
		List<Song> songs = songLibrary.getListFromArtistSearch(searchTerm);
		String[] artists = songLibrary.getArtistList(songs).toArray(
				new String[0]);
		String[] songNames = songLibrary.getSongNameList(songs).toArray(
				new String[0]);
		artistList.setListData(artists);
		songList.setListData(songNames);
	}

	public void setUpWindow() {

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constra = new GridBagConstraints();

		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(layout);
		panel.setBackground(Color.DARK_GRAY);

		ImageIcon logo = new ImageIcon("logo.gif");
		JLabel logoLabel = new JLabel(logo);
		constra.insets = new Insets(2, 2, 2, 2);
		constra.gridy = 0;
		constra.gridwidth = 2;
		constra.gridheight = 1;
		constra.weightx = 50;
		constra.weighty = 5;
		constra.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(logoLabel, constra);
		panel.add(logoLabel);

		ImageIcon openIcon = new ImageIcon("Open16.gif");
		JButton openButton = new JButton("Open...", openIcon);
		ActionListener openL = new OpenListener(this);
		openButton.addActionListener(openL);
		constra.gridx = 3;
		constra.gridwidth = 1;
		constra.weightx = 25;
		layout.setConstraints(openButton, constra);
		panel.add(openButton);

		JLabel searchLabel = new JLabel("Search");
		searchLabel.setForeground(Color.WHITE);
		constra.gridx = 0;
		constra.gridy = 1;
		constra.weightx = 40;
		layout.setConstraints(searchLabel, constra);
		panel.add(searchLabel);

		searchField = new JTextField();
		searchField.setBorder(BorderFactory.createLineBorder(Color.red, 3));
		searchField.setBackground(Color.LIGHT_GRAY);
		constra.gridy = 2;
		layout.setConstraints(searchField, constra);
		panel.add(searchField);

		JButton searchButton = new JButton("Search");
		ActionListener searchL = new SearchListener(this);
		searchButton.addActionListener(searchL);
		searchButton.setForeground(Color.RED);
		constra.gridx = 1;
		constra.weightx = 10;
		layout.setConstraints(searchButton, constra);
		panel.add(searchButton);

		artistRadio = new JRadioButton("By Artist");
		artistRadio.setBackground(Color.DARK_GRAY);
		artistRadio.setForeground(Color.WHITE);
		artistRadio.setSelected(true);
		constra.gridx = 2;
		constra.weightx = 25;
		layout.setConstraints(artistRadio, constra);
		panel.add(artistRadio);

		songRadio = new JRadioButton("By Song Title");
		songRadio.setBackground(Color.DARK_GRAY);
		songRadio.setForeground(Color.WHITE);
		constra.gridx = 3;
		layout.setConstraints(songRadio, constra);
		panel.add(songRadio);

		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(artistRadio);
		bGroup.add(songRadio);

		JLabel artistLabel = new JLabel("Artists");
		artistLabel.setForeground(Color.WHITE);
		constra.gridy = 3;
		constra.gridx = 0;
		constra.weightx = 40;
		layout.setConstraints(artistLabel, constra);
		panel.add(artistLabel);

		JLabel songsLabel = new JLabel("Songs");
		songsLabel.setForeground(Color.WHITE);
		constra.gridx = 2;
		constra.weightx = 25;
		layout.setConstraints(songsLabel, constra);
		panel.add(songsLabel);

		artistList = new JList<String>();
		MouseListener artistL = new ArtistMouseListener(this);
		artistList.addMouseListener(artistL);
		artistList.setBackground(Color.LIGHT_GRAY);
		artistList.setForeground(Color.WHITE);
		JScrollPane artScrollPane = new JScrollPane(artistList,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		constra.gridx = 0;
		constra.gridy = 4;
		constra.gridwidth = 2;
		constra.weightx = 50;
		constra.weighty = 80;
		constra.fill = GridBagConstraints.BOTH;
		layout.setConstraints(artScrollPane, constra);
		panel.add(artScrollPane);

		songList = new JList<String>();
		MouseListener songL = new SongMouseListener(this);
		songList.addMouseListener(songL);
		songList.setBackground(Color.LIGHT_GRAY);
		songList.setForeground(Color.WHITE);
		JScrollPane songScrollPane = new JScrollPane(songList,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		constra.gridx = 2;
		layout.setConstraints(songScrollPane, constra);
		panel.add(songScrollPane);
	}

	private static class OpenListener implements ActionListener {

		private JTunesBrowser parent;

		public OpenListener(JTunesBrowser parent) {
			this.parent = parent;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser fileDialog = new JFileChooser();
			fileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int retVal = fileDialog.showOpenDialog(parent);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				parent.songLibrary.clear();
				
				File folder = fileDialog.getSelectedFile();
				File[] musicFiles = getMusicFiles(folder);
				for (File mf : musicFiles) {
					String[] parts = mf.getName().split("-", 3);
					Song s = null;
					if (parts.length == 3) {
						s = new Song(parts[2].substring(0,
								parts[2].lastIndexOf('.')).trim(),
								parts[1].trim());
					} else {
						s = new Song(parts[1].substring(0,
								parts[1].lastIndexOf('.')).trim(),
								parts[0].trim());
					}
					parent.songLibrary.add(s);
				}
				parent.resetWindow();
			}
		}

		private static File[] getMusicFiles(File startFile) {
			File[] retVal = startFile.listFiles(new MusicFileFilter());

			File[] subDirs = startFile.listFiles(new DirectoryFilter());
			for (File dir : subDirs) {
				File[] more = getMusicFiles(dir);
				File[] bigArr = new File[retVal.length + more.length];
				System.arraycopy(retVal, 0, bigArr, 0, retVal.length);
				System.arraycopy(more, 0, bigArr, retVal.length, more.length);
				retVal = bigArr;
			}
			return retVal;
		}

		private static class DirectoryFilter implements FileFilter {

			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}

		}

		private static class MusicFileFilter implements FileFilter {

			@Override
			public boolean accept(File pathname) {
				if (pathname.isFile()) {
					String name = pathname.getName();
					String type = name.substring(name.lastIndexOf('.') + 1);
					return type.equalsIgnoreCase("txt") && name.contains("-");
				} else {
					return false;
				}
			}

		}

	}

	private static class SearchListener implements ActionListener {

		private JTunesBrowser parent;

		public SearchListener(JTunesBrowser parent) {
			this.parent = parent;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String searchTerm = parent.searchField.getText();
			if (parent.artistRadio.isSelected()) {
				parent.searchArtists(searchTerm);
			} else if (parent.songRadio.isSelected()) {
				parent.searchSongs(searchTerm);
			}
		}

	}

	private static class ArtistMouseListener extends MouseAdapter {

		private JTunesBrowser parent;
		
		public ArtistMouseListener(JTunesBrowser parent) {
			this.parent = parent;
		}

		public void mouseClicked(MouseEvent mouseEvent) {
			JList<String> theList = parent.artistList;
			if (mouseEvent.getClickCount() == 2) {
				int index = theList.locationToIndex(mouseEvent.getPoint());
				if (index >= 0) {
					String o = theList.getModel().getElementAt(index);
					parent.searchArtists(o);
				}
			}
		}

	}

	private static class SongMouseListener extends MouseAdapter {
		
		private JTunesBrowser parent;
		
		public SongMouseListener(JTunesBrowser parent) {
			this.parent = parent;
		}
		
		public void mouseClicked(MouseEvent mouseEvent) {
			JList<String> theList = parent.songList;
			if (mouseEvent.getClickCount() == 2) {
				int index = theList.locationToIndex(mouseEvent.getPoint());
				if (index >= 0) {
					String o = theList.getModel().getElementAt(index);
					parent.searchArtists(o);
				}
			}
		}
	}
}
