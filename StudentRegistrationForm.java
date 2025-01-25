import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class StudentRegistrationForm extends JFrame implements ActionListener {
    // Components for the main frame
    private JTextField idField, nameField, fatherNameField, phoneNumberField, dobField, genderField, courseField, sectionField, admissionDateField, emailField, addressField;
    private JButton addButton, databaseButton, exitButton;
    private Map<Integer, Student> registrations; // Map to store registrations with unique IDs
    private int idCounter; // Counter to generate unique IDs

    // Components for the database page
    private JFrame databaseFrame;
    private JTable table;
    private JTextField deleteIdField;
    private JButton deleteButton;

    public StudentRegistrationForm() {
        setTitle("Student Registration Management System");
        setSize(600, 400); // Adjusted size for better visibility
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components for the main frame
        idField = new JTextField(10);
        nameField = new JTextField(20);
        fatherNameField = new JTextField(20);
        phoneNumberField = new JTextField(20);
        dobField = new JTextField(10);
        genderField = new JTextField(10);
        courseField = new JTextField(20);
        sectionField = new JTextField(10);
        admissionDateField = new JTextField(10);
        emailField = new JTextField(20);
        addressField = new JTextField(20);
        addButton = new JButton("Add");
        databaseButton = new JButton("Database");
        exitButton = new JButton("Exit");

        // Set colors for text fields
        idField.setForeground(Color.BLUE);
        nameField.setForeground(Color.BLUE);
        fatherNameField.setForeground(Color.BLUE);
        phoneNumberField.setForeground(Color.BLUE);
        dobField.setForeground(Color.BLUE);
        genderField.setForeground(Color.BLUE);
        courseField.setForeground(Color.BLUE);
        sectionField.setForeground(Color.BLUE);
        admissionDateField.setForeground(Color.BLUE);
        emailField.setForeground(Color.BLUE);
        addressField.setForeground(Color.BLUE);

        // Set colors for buttons
        addButton.setBackground(Color.GREEN);
        databaseButton.setBackground(Color.YELLOW);
        exitButton.setBackground(Color.RED);
        addButton.setForeground(Color.WHITE);
        databaseButton.setForeground(Color.WHITE);
        exitButton.setForeground(Color.WHITE);

        // Set layout for the main frame
        setLayout(new BorderLayout());

        // Panel for title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Student Registration Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridLayout(11, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new JLabel("ID: "));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name: "));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Father's Name: "));
        inputPanel.add(fatherNameField);
        inputPanel.add(new JLabel("Phone Number: "));
        inputPanel.add(phoneNumberField);
        inputPanel.add(new JLabel("Date of Birth (YYYY-MM-DD): "));
        inputPanel.add(dobField);
        inputPanel.add(new JLabel("Gender: "));
        inputPanel.add(genderField);
        inputPanel.add(new JLabel("Course: "));
        inputPanel.add(courseField);
        inputPanel.add(new JLabel("Section: "));
        inputPanel.add(sectionField);
        inputPanel.add(new JLabel("Admission Date (YYYY-MM-DD): "));
        inputPanel.add(admissionDateField);
        inputPanel.add(new JLabel("Email: "));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Address: "));
        inputPanel.add(addressField);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(databaseButton);
        buttonPanel.add(exitButton);

        // Add components to the main frame
        add(titlePanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners for the main frame buttons
        addButton.addActionListener(this);
        databaseButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Initialize registrations map and ID counter
        registrations = new HashMap<>();
        idCounter = 1;

        // Create the database frame
        createDatabaseFrame();
    }

    // Method to create the database frame
    private void createDatabaseFrame() {
        databaseFrame = new JFrame("Registration Database");
        databaseFrame.setSize(800, 400);
        databaseFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Initialize components for the database frame
        deleteIdField = new JTextField(10);
        deleteButton = new JButton("Delete");

        // Set colors for text field and button
        deleteIdField.setForeground(Color.BLUE);
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);

        // Panel for delete components
        JPanel deletePanel = new JPanel();
        deletePanel.add(new JLabel("Enter ID to delete: "));
        deletePanel.add(deleteIdField);
        deletePanel.add(deleteButton);

        // Create a table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Father's Name");
        model.addColumn("Phone Number");
        model.addColumn("Date of Birth");
        model.addColumn("Gender");
        model.addColumn("Course");
        model.addColumn("Section");
        model.addColumn("Admission Date");
        model.addColumn("Email");
        model.addColumn("Address");

        // Populate the table model with registrations data
        for (Student student : registrations.values()) {
            Object[] row = {student.getId(), student.getName(), student.getFatherName(),
                            student.getPhoneNumber(), student.getDob(), student.getGender(),
                            student.getCourse(), student.getSection(), student.getAdmissionDate(),
                            student.getEmail(), student.getAddress()};
            model.addRow(row);
        }

        // Create the table
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Set layout for the database frame
        databaseFrame.setLayout(new BorderLayout());

        // Add components to the database frame
        databaseFrame.add(scrollPane, BorderLayout.CENTER);
        databaseFrame.add(deletePanel, BorderLayout.SOUTH);

        // Add action listener for the delete button
        deleteButton.addActionListener(this);
    }

    // Action performed when buttons are clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // Get values from text fields
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String fatherName = fatherNameField.getText();
            String phoneNumber = phoneNumberField.getText();
            String dob = dobField.getText();
            String gender = genderField.getText();
            String course = courseField.getText();
            String section = sectionField.getText();
            String admissionDate = admissionDateField.getText();
            String email = emailField.getText();
            String address = addressField.getText();

            // Create a new student object
            Student student = new Student(id, name, fatherName, phoneNumber, dob, gender, course, section, admissionDate, email, address);

            // Add the student to the registrations map with the unique ID
            registrations.put(id, student);

            // Increment the ID counter for the next registration
            idCounter = Math.max(idCounter, id) + 1;

            // Clear fields after submission
            clearFields();
        } else if (e.getSource() == databaseButton) {
            // Update the table model
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear previous data
            for (Student student : registrations.values()) {
                Object[] row = {student.getId(), student.getName(), student.getFatherName(),
                                student.getPhoneNumber(), student.getDob(), student.getGender(),
                                student.getCourse(), student.getSection(), student.getAdmissionDate(),
                                student.getEmail(), student.getAddress()};
                model.addRow(row);
            }
            // Show the database frame
            databaseFrame.setVisible(true);
        } else if (e.getSource() == deleteButton) {
            // Delete student by ID
            int id = Integer.parseInt(deleteIdField.getText());
            if (registrations.containsKey(id)) {
                registrations.remove(id);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0); // Clear previous data
                for (Student student : registrations.values()) {
                    Object[] row = {student.getId(), student.getName(), student.getFatherName(),
                                    student.getPhoneNumber(), student.getDob(), student.getGender(),
                                    student.getCourse(), student.getSection(), student.getAdmissionDate(),
                                    student.getEmail(), student.getAddress()};
                    model.addRow(row);
                }
                JOptionPane.showMessageDialog(databaseFrame, "Student with ID " + id + " deleted.");
            } else {
                JOptionPane.showMessageDialog(databaseFrame, "Student with ID " + id + " not found.");
            }
            deleteIdField.setText("");
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    // Method to clear text fields
    private void clearFields() {
        idField.setText(String.valueOf(idCounter));
        nameField.setText("");
        fatherNameField.setText("");
        phoneNumberField.setText("");
        dobField.setText("");
        genderField.setText("");
        courseField.setText("");
        sectionField.setText("");
        admissionDateField.setText("");
        emailField.setText("");
        addressField.setText("");
    }

    public static void main(String[] args) {
        // Create an instance of the form
        StudentRegistrationForm form = new StudentRegistrationForm();
        // Make the form visible
        form.setVisible(true);
    }
}

// Student class to represent a student
class Student {
    private int id;
    private String name;
    private String fatherName;
    private String phoneNumber;
    private String dob;
    private String gender;
    private String course;
    private String section;
    private String admissionDate;
    private String email;
    private String address;

    public Student(int id, String name, String fatherName, String phoneNumber, String dob, String gender, String course, String section, String admissionDate, String email, String address) {
        this.id = id;
        this.name = name;
        this.fatherName = fatherName;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.gender = gender;
        this.course = course;
        this.section = section;
        this.admissionDate = admissionDate;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getCourse() {
        return course;
    }

    public String getSection() {
        return section;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
