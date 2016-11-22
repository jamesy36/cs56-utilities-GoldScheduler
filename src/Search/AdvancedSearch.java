package Search;
import edu.ucsb.cs56.projects.scrapers.ucsb_curriculum.* ;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.SQLException;
import Course.Course;
import Course.Lecture;
import Schedule.Scheduler;
import connection.courseInfo.CourseConnection;
import java.util.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.PrintStream;


// This class will allow the user to select from: Department, Professor, or General Educatio  and indicate from a combobox, populated by category, what they are searching for.
 
public class AdvancedSearch{
    /* private JPanel display;
    private JScrollPane scrollableDisplay;
    private JPanel control;
    private JPanel cDisplay;
    private Scheduler schedule;
    private final Color darkerColor = new Color(235,215,128);
    private final Color lighterColor = new Color(236,226,178);
    private String[] searchOptions = {"Department", "Professor", "General Education"};
    private ArrayList<JCheckBox> geChecksList;
    private HashMap<JCheckBox, String> geChecks;
    //CONSTRUCTORS
    public AdvancedSearch(){
	   this.schedule = new Scheduler();
        geChecksList = new ArrayList<JCheckBox>();
        geChecks = new HashMap<JCheckBox, String>();
    }
    /**
     * @param s Schedule saved in database
    
    public AdvancedSearch(Scheduler s){
        this.schedule = s;
        geChecksList = new ArrayList<JCheckBox>();
        geChecks = new HashMap<JCheckBox, String>();
    }
    /**
     *@return returns the full set display with both the control and course panels
    public JPanel getDisplay(){
        this.setDisplay();
        return this.display;
    }
    /**
     *@return returns the full display in a scrollPane
     
    public JScrollPane getScrollDisplay(){
        this.scrollableDisplay = new JScrollPane(this.getDisplay());
        return this.scrollableDisplay;
    }
    /**
     *Initializes the display
     
    public void setDisplay(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(this.getControl(), BorderLayout.NORTH);
        panel.add(this.getCourses(), BorderLayout.SOUTH);
        this.display = panel;
    }
    //SCHEDULE
   
     *This will get the schedule display
     * @return the panel with the schedule display
    
    public JPanel displaySchedule(){
        Scheduler s = this.schedule;
        JPanel display = new JPanel();
        display.add(s.getMain());
        return display;
    }
    
     *@return returns the schedule.
    
    public Scheduler getSchedule(){
        return this.schedule;
    }
    
     *sets the schedule to an empty one.
     
    public void resetSchedule(){
        this.schedule = new Scheduler();
    }
    //COURSE DISPLAY
    
     *Sets courses to a blank screen
     
    public void setCourses(){
        JPanel blank = new JPanel();
        blank.setPreferredSize(new Dimension(600,533));
        blank.setBackground(this.darkerColor);
        this.cDisplay = blank;
    }
    
     *Sets course display according to an unsorted courseList
     * @param courseList the array of courses to be put into display
     
    public void setCourses(ArrayList<Course> courseList){
        this.setCoursesBy3DArray(SimpleSearch.getGroupedResults(SimpleSearch.groupCourseIDResults(courseList)));
    }
    
     *Sets the course display according to an ArrayList of Courses.
     *@param courseList a 3D ArrayList sorted to make it easy to dispay the course results
     
    public void setCoursesBy3DArray(ArrayList<ArrayList<ArrayList<Course>>> courseList){
        JPanel courses = new JPanel();
        courses.setBackground(this.darkerColor);
        courses.setLayout(new BoxLayout(courses, BoxLayout.Y_AXIS));
        int numResults = courseList.size();
        if(numResults == 0){
            courses.setBackground(this.darkerColor);
            JLabel noResults = new JLabel("There are no courses that match what you're looking for");
            Font font = noResults.getFont();
            Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
            noResults.setFont(boldFont);
            courses.add(noResults);
            this.cDisplay = courses;
        }
        //Sets up panel as a grid by how many courses there are
        JPanel[] panels = new JPanel[numResults];
        for(int index = 0 ; index<numResults; index++){
            panels[index] = new JPanel();
            panels[index].setBackground(this.darkerColor);
        }
        //Puts them into a display
        for(int n = 0; n<numResults; n++){
            JPanel coursePanel = new JPanel();
            panels[n].add(coursePanel);
            Course currentCourse;
            Lecture thisLecture;
            Lecture thisSection;
            /*rows: 1. title
             2. header
             3. Lecture info
             4+. Section info
             columns: 4
             (Days, times, instrucors, [location,] addButton)
             
            int rows = 2;
            int columns = 4;
            int numLects = courseList.get(n).size();
            int totalNumSects = 0;
            for(int i = 0; i<numLects; i++){
                for(Course thisCourse: courseList.get(n).get(i)){
                    if(thisCourse.getSect()!=null){
                        totalNumSects += 1;
                    }
                }
            }
            rows+= numLects;
            rows+= totalNumSects;
            coursePanel.setPreferredSize(new Dimension(600,33*rows));
            coursePanel.setLayout(new GridLayout(rows, columns));
            JPanel[][] panelNum = new JPanel[rows][columns];
            for(int y = 0 ; y<rows; y++){
                for(int x = 0; x<columns; x++){
                    panelNum[y][x] = new JPanel();
                    panelNum[y][x].setBackground(this.lighterColor);
                    coursePanel.add(panelNum[y][x]);
                }
            }
            currentCourse = courseList.get(n).get(0).get(0);
            //Row 1: Title and view button
            JLabel t = new JLabel(currentCourse.courseID);
            Font font = t.getFont();
            Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
            t.setFont(boldFont);
            JButton view = new JButton("View");
            view.addActionListener(new viewListener(currentCourse,this, courseList));
            panelNum[0][0].add(t);
            panelNum[0][1].add(view);
            //Row 2: Header
            JLabel d = new JLabel("Day(s)");
            JLabel times = new JLabel("Times");
            JLabel inst = new JLabel("Instructor");
            JLabel loc = new JLabel("Location");
            d.setFont(boldFont);
            times.setFont(boldFont);
            inst.setFont(boldFont);
            loc.setFont(boldFont);
            panelNum[1][0].add(d);
            panelNum[1][1].add(times);
            panelNum[1][2].add(inst);
            //Row 3: Lecture info
            int currentRow = 2;
            for(int i = 0; i<numLects; i++){
                currentCourse = courseList.get(n).get(i).get(0);
                thisLecture = currentCourse.getLect();
                JLabel lectDay = new JLabel(thisLecture.dayStringShort());
                JLabel lectTime = new JLabel(thisLecture.timeString());
                JLabel lectInstructor = new JLabel(thisLecture.professor);
                JLabel lectLocation = new JLabel(thisLecture.location);
                lectDay.setFont(boldFont);
                lectTime.setFont(boldFont);
                lectInstructor.setFont(boldFont);
                lectLocation.setFont(boldFont);
                panelNum[currentRow][0].add(lectDay);
                panelNum[currentRow][1].add(lectTime);
                panelNum[currentRow][2].add(lectInstructor);
                currentRow++;
                for(Course c:courseList.get(n).get(i)){
                    currentCourse = c;
                    thisLecture = currentCourse.getLect();
                    JButton addToSchedule = new JButton("Add");
                    addToSchedule.addActionListener(new addListener(this.schedule,currentCourse));
                    //If there is a section the add button corresponds to each section
                    if(currentCourse.getSect()==null){
                        panelNum[currentRow-1][3].add(addToSchedule);
                    }
                    else{
                        thisSection = currentCourse.getSect();
                        //Row 4+: Section Info
                        JLabel sectDay = new JLabel(thisSection.dayStringShort());
                        JLabel sectTime = new JLabel(thisSection.timeString());
                        JLabel sectInstructor = new JLabel("N/A");
                        JLabel sectLocation = new JLabel(thisSection.location);
                        panelNum[currentRow][0].add(sectDay);
                        panelNum[currentRow][1].add(sectTime);
                        panelNum[currentRow][2].add(sectInstructor);
                        panelNum[currentRow][3].add(addToSchedule);
                        currentRow++;
                    }
                }
            }
        }
        for(int index = 0 ; index<numResults; index++){
            courses.add(panels[index]);
        }
        this.cDisplay = courses;
    }
    
     *@return sets panel to a blank panel and returns it
     
    public JPanel getCourses(){
        this.setCourses();
        return this.cDisplay;
    }
   
     *@param i indicator that you don't want to call set courses
     *@return gets the current course display
    
    public JPanel getCourses(int i){
        return this.cDisplay;
    }
   
     *@param list and ArrayList of unsorted courses
     *@return Calls the setCourses using an arrayList of Courses and returns the resulting panel.
    
    public JPanel getCourses(ArrayList<Course> list){
        this.setCoursesBy3DArray(SimpleSearch.getGroupedResults(SimpleSearch.groupCourseIDResults(list)));
        return this.cDisplay;
    }
    
     *@param list a 3D ArrayList of sorted courses
     *@return A panel that uses the sorted ArrayList to display the courses
    
    public JPanel getCoursesBy3DArray(ArrayList<ArrayList<ArrayList<Course>>> list){
        this.setCoursesBy3DArray(list);
        return this.cDisplay;
    }
    //CONTROL
   
     *Calls setControl for a blank panel to display
     * @return the Control panel
    
    public JPanel getControl() {
        this.setControl();
        return this.control;
    }
   
     *@param i indicator that you don't want to call set courses
     *@return gets the current control display
    
    public JPanel getControl(int i) {
        return this.control;
    }
   
     *Sets the display panel and then returns it.
     *Creates the display panel that includes a control panel that you can type a keyword into
   

    public void setControl(){
        JPanel controlPanel = new JPanel();
        int len = this.searchOptions.length;
        controlPanel.setPreferredSize(new Dimension(500,66));
        controlPanel.setLayout(new GridLayout(2, 1));
        JPanel[] panelHolder = new JPanel[2];
        for(int i = 0; i<2; i++){
            panelHolder[i] = new JPanel();
            panelHolder[i].setBackground(this.darkerColor);
            controlPanel.add(panelHolder[i]);
        }
        panelHolder[1].setLayout(new GridLayout(1,len));
        JPanel[] bottomHolder = new JPanel[len];
        for(int j = 0; j<len; j++){
            bottomHolder[j] = new JPanel();
            bottomHolder[j].setBackground(this.darkerColor);
            panelHolder[1].add(bottomHolder[j]);
        }
        //make labels
        JLabel label = new JLabel("Select an option to search by:");
        panelHolder[0].add(label);
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(darkerColor);
        panelHolder[0].add(menuPanel);
        //make buttons
        JRadioButton [] radioButtons = new JRadioButton[len];
        ButtonGroup options = new ButtonGroup();
        for(int i=0;i<len;i++){
            radioButtons[i] = new JRadioButton(this.searchOptions[i]);
            options.add(radioButtons[i]);
            radioButtons[i].addActionListener(new radioListener(menuPanel, this,
                                                                radioButtons[i].getText()));
            bottomHolder[i].add(radioButtons[i]);
        }
        this.control = controlPanel;
    }
    //GETTING RESULTS
    /**
     *@param s Indicates which option to populate the combobox with
     *@return a String array to select from that relates to the option chosen
    
    public String[] getList(String s){
        if(s=="Department"){
            String[] m;
			try {
				m = connection.courseInfo.CourseConnection.getMajor();
			} catch (SQLException e) {
				m = new String[0];
			}
            return m;
        }
        else if(s=="Professor"){
            String[] m;
			try {
				m = connection.courseInfo.CourseConnection.getProfessor();
			} catch (SQLException e) {
				m = new String[0];
			}
            return m;
        }
        else { //s==GE
           String [] m= {"B", "C", "D", "E", "F", "G", "H", "ETH", "EUR", "QNT", "NWC", "WRT"};
            return m;
        }
    }
   
     *@param key A keyword taken from the dropdown menu that represents what the user is looking for
     *@param option The button clicked indicating which category the keyword belongs to
     * @return the arrayList of courses returned from the database
    
    public ArrayList<Course> getResults(ArrayList<String> key, ArrayList<String>  option){
        ArrayList<Course> courseList = null;
		try {
			courseList = connection.courseInfo.CourseConnection.getResults(key, option);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return courseList;
    }
    //ACTION LISTENER CLASSES
    
     *Class to view a specific course upon a button being pressed
     
    class viewListener implements ActionListener{
        private Course c1;
        private AdvancedSearch p;
        private ArrayList<ArrayList<ArrayList<Course>>> cList;
        public viewListener(Course cIn1, AdvancedSearch p, ArrayList<ArrayList<ArrayList<Course>>> cList){
            this.c1 = cIn1;
            this.p = p;
            this.cList = cList;
        }
        @Override
        public void actionPerformed(ActionEvent e){
            this.p.display.removeAll();
            this.p.display.revalidate();
            this.p.display.repaint();
            this.p.display.add(this.c1.getPanel(), BorderLayout.NORTH);
            JPanel buttonPanel = new JPanel();
            JButton back = new JButton("Back");
            buttonPanel.add(back);
            buttonPanel.setBackground(Color.LIGHT_GRAY);
            this.p.display.add(buttonPanel, BorderLayout.SOUTH);
            back.addActionListener(new backListener(this.p,this.cList));
            this.p.scrollableDisplay.removeAll();
            this.p.scrollableDisplay.revalidate();
            this.p.scrollableDisplay.repaint();
            this.p.scrollableDisplay.add(this.p.display);
        }
    }
    /**
     *Allows the user to return to the populated search view after viewing a specific course
     
    class backListener implements ActionListener{
        private AdvancedSearch outer;
        private ArrayList<ArrayList<ArrayList<Course>>> cList1;
        public backListener(AdvancedSearch outerIn, ArrayList<ArrayList<ArrayList<Course>>> cList1){
            this.outer = outerIn;
            this.cList1 = cList1;
        }
        @Override
        public void actionPerformed(ActionEvent e){
            this.outer.display.removeAll();
            this.outer.display.revalidate();
            this.outer.display.repaint();
            this.outer.display.add(this.outer.getControl(), BorderLayout.NORTH);
            this.outer.display.add(new JScrollPane(this.outer.getCoursesBy3DArray(this.cList1)), BorderLayout.CENTER);
            this.outer.scrollableDisplay.removeAll();
            this.outer.scrollableDisplay.revalidate();
            this.outer.scrollableDisplay.repaint();
            this.outer.scrollableDisplay.add(this.outer.display);
        }
    }
    /**
     *Populates a combobox according to a selcted radioButton
    
    class radioListener implements ActionListener{
        private JPanel p;
        private AdvancedSearch a;
        private String optionString;
        public radioListener(JPanel p, AdvancedSearch a, String optionString){
            this.p = p;
            this.a = a;
            this.optionString = optionString;
        }
        @Override
        public void actionPerformed(ActionEvent e){
            String [] menuList = getList(optionString);
            JPanel newPanel = new JPanel();
            if(optionString.equals("General Education")){
                newPanel.setLayout(new BoxLayout(newPanel,BoxLayout.Y_AXIS));
                JCheckBox temp;
                geChecksList = new ArrayList<JCheckBox>();
                for(String s:menuList){
                    temp = new JCheckBox(s);
                    geChecksList.add(temp);
                    geChecks.put(temp, s);
                    newPanel.add(temp);
                }
                JButton submitButton = new JButton("Submit");
                submitButton.addActionListener(new submitListener(this.a.getCourses(0),this.a));
                newPanel.add(submitButton);
            }
            this.p.removeAll();
            this.p.revalidate();
            this.p.repaint();
            if(!optionString.equals("General Education")){
                JComboBox cMenu = new JComboBox(menuList);
                cMenu.addActionListener(new menuListener(this.a, optionString));
                this.p.add(cMenu);
            }
            this.a.display.removeAll();
            this.a.display.revalidate();
            this.a.display.repaint();
            this.a.display.add(this.a.getControl(0), BorderLayout.NORTH);
            this.a.display.add(new JScrollPane(this.a.getCourses(0)), BorderLayout.CENTER);
            if(optionString.equals("General Education")){
                this.a.display.add(new JScrollPane(newPanel), BorderLayout.EAST);
            }
        }
    }
    /**
     *Searches the database according to the choices selected
     
    class submitListener implements ActionListener{
        private AdvancedSearch a;
        private JPanel courseResultsPanel;
        public submitListener(JPanel courseResultsPanel, AdvancedSearch a){
            this.courseResultsPanel = courseResultsPanel;
            this.a = a;
        }
        @Override
        public void actionPerformed(ActionEvent e){
            ArrayList<Course> result = new ArrayList<Course>();
            ArrayList<String> keyArray = new ArrayList<String>();
            ArrayList<String> optionArray = new ArrayList<String>();
            String [] menuList = getList("General Education");
            //TODO a bug caused by not refresh the checkbox
            for(JCheckBox check: this.a.geChecksList){
                if(check.isSelected()){
                	optionArray.add("General Education");
                    keyArray.add(this.a.geChecks.get(check));
                }
            }
            result = getResults(keyArray, optionArray);
            this.a.display.removeAll();
            this.a.display.revalidate();
            this.a.display.repaint();
            this.a.display.add(this.a.getControl(0), BorderLayout.NORTH);
            this.a.display.add(new JScrollPane(getCourses(result)), BorderLayout.CENTER);
            JPanel newPanel = new JPanel();
            newPanel.setLayout(new BoxLayout(newPanel,BoxLayout.Y_AXIS));
            JCheckBox temp;
            geChecksList = new ArrayList<JCheckBox>();
            for(String s:menuList){
                temp = new JCheckBox(s);
                geChecksList.add(temp);
                geChecks.put(temp, s);
                newPanel.add(temp);
            }
            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(new submitListener(this.a.getCourses(0),this.a));
            newPanel.add(submitButton);
            this.a.display.add(new JScrollPane(newPanel), BorderLayout.EAST);
            this.a.scrollableDisplay.removeAll();
            this.a.scrollableDisplay.revalidate();
            this.a.scrollableDisplay.repaint();
            this.a.scrollableDisplay.add(this.a.display);
        }
    }
    /**
     *Searches the database according to the choice selected from the combobox
    
    class menuListener implements ActionListener{
        private AdvancedSearch a;
        private String optionString;
        public menuListener(AdvancedSearch a, String optionString){
            this.a = a;
            this.optionString = optionString;
        }
        @Override
        public void actionPerformed(ActionEvent e){
            ArrayList<Course> result = new ArrayList<Course>();
            JComboBox comboBox = (JComboBox) e.getSource();
            String selectedItem = (String)comboBox.getSelectedItem();
            ArrayList<String> selectedArray = new ArrayList<String>();
            ArrayList<String> option = new ArrayList<String>();
            selectedArray.add(selectedItem);
            option.add(this.optionString);
            result = getResults(selectedArray, option);
            this.a.display.removeAll();
            this.a.display.revalidate();
            this.a.display.repaint();
            this.a.display.add(this.a.getControl(0), BorderLayout.NORTH);
            this.a.display.add(new JScrollPane(getCourses(result)), BorderLayout.CENTER);
            this.a.scrollableDisplay.removeAll();
            this.a.scrollableDisplay.revalidate();
            this.a.scrollableDisplay.repaint();
            this.a.scrollableDisplay.add(this.a.display);
        }
    }
    /**
     *Allows the user to add classes to their schedule
     
    class addListener implements ActionListener{
        private Scheduler sch;
        private Course c;
        private JPanel display;
        public addListener(Scheduler sch, Course c){
            this.sch = sch;
            this.c = c;
        }
        @Override
        public void actionPerformed(ActionEvent e){
            this.sch.add(c);
        }
    }
}
*/

	static  JFrame frame;

	
	public static void main (String [] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				displayJFrame();
			}
		} );
	
	}
	
	static void displayJFrame() {
		try{
			
			frame = new JFrame();
			
			/* 
			 TODO: scrape the subject, years, and course levels so
			 that if the website makes changes, it reflects in the program
			*/
			
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
			to identify each quarter */
			Vector quarter = new Vector();
			quarter.addElement( new ItemGolder("1", "Winter"));
			quarter.addElement( new ItemGolder("2", "Spring"));
			quarter.addElement( new ItemGolder("3", "Summer"));
			quarter.addElement( new ItemGolder("4", "Fall"));
			
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
			
			
			JPanel panel = new JPanel();
			panel.setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();

			//set constraints and add Piclabel at top of panel
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.weightx = .5;
			constraints.weighty = .5;
			constraints.gridwidth = 4;

			//constraints for second row of subject, quarter, year, and level boxes
			//then add them to pane
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
			constraints.gridx = 0;
			constraints.gridy = 3;
			constraints.gridheight = 8;
			constraints.gridwidth = 5;
			constraints.weighty = 0.5;
			constraints.ipady = 200;
			constraints.ipadx = 800;
			scrollbar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollbar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			constraints.fill = GridBagConstraints.VERTICAL;
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
						
						ItemGolder quarter = (ItemGolder) quarterBox.getSelectedItem();
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
			} );
			
			//setup the JFrame
			frame.setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE) ;
			frame.getContentPane().add(panel);
			frame.setSize(1280, 720);
			frame.setVisible(true);
			
			
		}catch(Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
	}
}






