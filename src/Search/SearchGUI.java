package Search;
import java.awt.*;
import javax.swing.*;
import Course.Course;
import Course.Lecture;

/**
 * Deprecated class used to test the Search class
 * @author Hanna Vigil, Jonathan Easterman
 * @version February 5, 2015
 */
public class SearchGUI {
    public static void main(String[] args){
        //Make frame
        JFrame frame = new JFrame();
        frame. setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE);
        frame. setSize(910,650);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        SimpleSearch s = new SimpleSearch();
        mainPanel.add(s.getDisplay(), BorderLayout.SOUTH);
        frame.add(mainPanel);
        frame. setVisible(true);
    }


    /*
	       	//Array of all the different departmens on GOLD
	    	String [] subject = {"ANTH" , "ART", "ART CS", "ARTHI", "ARTST", "AS AM", "ASTRO", "BIOL",
				"BIOL CS", "BMSE","BL ST", "CH E", "CHEM CS", "CHEM", "CH ST", "CHIN", "CLASS",
				"COMM", "C LIT", "CMPSC", "CMPSCCS", "CMPTG", "CMPTGCS", "CNCSP", "DANCE", "DYNS",
				"EARTH", "EACS", "EEMB", "ECON", "ED", "ECE", "ENGR", "ENGL", "ESM", "ENV S", "ESS",
				"ES", "FEMST", "FAMST", "FLMST", "FR", "GEN S", "GEN SCS", "GEOG", "GER", "GPS", "GLOBL",
				"GREEK", "HEB", "HIST", "INT", "INT CS", "ITAL", "JAPAN", "KOR", "LATIN", "LAIS", "LING",
				"LIT", "LIT CS", "MARSC", "MATRL", "MATH", "MATH CS", "ME", "MAT", "ME ST", "MES",
				"MS", "MCDB", "MUS", "MUS CS", "MUS A", "PHIL", "PHYS", "PHYS CS", "POL S", "PORT", "PSY", "RG ST",
				"RENST", "SLAV", "SOC", "SPAN", "SHS", "PSTAT", "TMP", "THTR", "WRIT", "W&L", "W&L CS"};
			
			/* Different quarters with their corresponding number ID (used by previous programmers
			to identify each quarter 
			Vector quarter = new Vector();
			quarter.addElement( new Item("1", "Winter"));
			quarter.addElement( new Item("2", "Spring"));
			quarter.addElement( new Item("3", "Summer"));
			quarter.addElement( new Item("4", "Fall"));
			
			//Array of years
			String [] year = {"2016", "2015", "2014"};
			
			//Array of Course Levels
			String [] level = {"Undergraduate", "Graduate", "ALL"};
			
			
			//Creates ComboBoxes of the aforementioned search criteria
			JComboBox subjectBox = new JComboBox(subject);
			subjectBox.setEditable(false);
			
			JComboBox quarterBox = new JComboBox(quarter);
			quarterBox.setEditable(false);
			
			JComboBox yearBox = new JComboBox(year);
			yearBox.setEditable(false);
			
			JComboBox levelBox = new JComboBox(level);
			levelBox.setEditable(false);
			
			//Search Button
			JButton search = new JButton("SEARCH");
			
			
			//Creates textArea that displays your search results
			JTextArea textbox = new JTextArea(20, 40);
			textbox.setEditable(false);
			
			//Redirects terminal output to GUI
			PrintStream stream = new PrintStream(new CustomOutputStream(textbox));
			System.setOut(stream);
			System.setErr(stream);
		
			//Makes textarea scrollable
			JScrollPane scrollbar = new JScrollPane(textbox);


	constraints.gridwidth = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(0, 15, 0, 15);
			panel.add(subjectBox, constraints);
			constraints.gridx = 1;
			panel.add(quarterBox,constraints);
			constraints.gridx = 2;
			panel.add(yearBox, constraints);
			constraints.gridx = 3;
			panel.add(levelBox, constraints);

			//constraints for third row of search button
			//then add them to pane
			constraints.insets = new Insets(0, 0, 0, 0);
			constraints.gridy = 2;
			constraints.gridx = 1;
			constraints.gridwidth = 2;
			panel.add(search, constraints);

			//constraints for displayed text field (scrollbar)
			//then add them to pane
			constraints.gridx = 1;
			constraints.gridy = 3;
			constraints.gridheight = 5;
			constraints.weighty = 0.5;
			constraints.ipady = 200;
			scrollbar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollbar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			constraints.fill = GridBagConstraints.BOTH;
			constraints.insets = new Insets(10, 0, 100, 0);
			panel.add(scrollbar, constraints);




			
			search.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ee) {
					try{
						//reset the textbox so it clears each time you press search
						textbox.setText(null);
						
						//instantiate a new Curriculum search object
						UCSBCurriculumSearch cssc = new UCSBCurriculumSearch();
						
						//get the values of the selections
						String dept = String.valueOf(subjectBox.getSelectedItem());
						
						Item quarter = (Item) quarterBox.getSelectedItem();
						String quarter2 = quarter.getId();
						
						String year = String.valueOf(yearBox.getSelectedItem());
						String lev = String.valueOf(levelBox.getSelectedItem());
						
						
						String qtr = year + quarter2;
						
						//search with the corresponding selections in the gui
						cssc.loadCourses(dept, qtr, lev);
						cssc.printLectures();


						//set scrollbar to top of scrollpane
						javax.swing.SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								scrollbar.getVerticalScrollBar().setValue(0);
							}
						});
					}
					catch (Exception e){
						System.err.println(e);
						e.printStackTrace();
					}
				}
			    }
			
			//setup the JFrame
			frame.setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE) ;
			frame.getContentPane().add(panel);
			frame.setSize(1280,720);
			frame.setVisible(true);
			
			
		}catch(Exception e){
			System.err.println(e);
			e.printStackTrace();
}
    */

}	

