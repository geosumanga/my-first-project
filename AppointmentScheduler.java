import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AppointmentScheduler extends JFrame {
    private JTextField titleField;
    private JTextField dateField;
    private JTextField timeField;
    private JTextArea appointmentListArea;
    private ArrayList<Appointment> appointments;

    public AppointmentScheduler() {
        appointments = new ArrayList<>();
        setTitle("Appointment Scheduler");
        setSize(510, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); 

        JLabel titleLabel = new JLabel("Appointment Title:");
        titleLabel.setBounds(20, 20, 150, 25);

        titleField = new JTextField(20);
        titleField.setBounds(180, 20, 298, 25);
        
        JLabel dateLabel = new JLabel("Date (DD-MM-YYYY):");
        dateLabel.setBounds(20, 60, 150, 25);
        
        dateField = new JTextField(10);
        dateField.setBounds(180, 60, 298, 25);
        
        JLabel timeLabel = new JLabel("Time (HH:MM):");
        timeLabel.setBounds(20, 100, 150, 25);
        
        timeField = new JTextField(5);
        timeField.setBounds(180, 100, 298, 25);

        JButton addButton = new JButton("Add Appointment");
        addButton.setBounds(20, 140, 139, 30);

        JButton updateButton = new JButton("Update Appointment");
        updateButton.setBounds(169, 140, 149, 30);

        JButton deleteButton = new JButton("Delete Appointment");
        deleteButton.setBounds(329, 140, 148, 30);

        appointmentListArea = new JTextArea(10, 30);
        appointmentListArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(appointmentListArea);
        scrollPane.setBounds(20, 190, 458, 150);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAppointment();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAppointment();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAppointment();
            }
        });

        add(titleLabel);
        add(titleField);
        add(dateLabel);
        add(dateField);
        add(timeLabel);
        add(timeField);
        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(scrollPane);

        setVisible(true);
    }

    private void addAppointment() {
        String title = titleField.getText();
        String date = dateField.getText();
        String time = timeField.getText();
        appointments.add(new Appointment(title, date, time));
        refreshAppointmentList();
    }

    private void updateAppointment() {
        String title = titleField.getText();
        String date = dateField.getText();
        String time = timeField.getText();
        for (Appointment appointment : appointments) {
            if (appointment.getTitle().equals(title)) {
                appointment.setDate(date);
                appointment.setTime(time);
                break;
            }
        }
        refreshAppointmentList();
    }

    private void deleteAppointment() {
        String title = titleField.getText();
        appointments.removeIf(appointment -> appointment.getTitle().equals(title));
        refreshAppointmentList();
    }

    private void refreshAppointmentList() {
        appointmentListArea.setText("");
        for (Appointment appointment : appointments) {
            appointmentListArea.append(appointment.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        new AppointmentScheduler();
    }
}

class Appointment {
    private String title;
    private String date;
    private String time;

    public Appointment(String title, String date, String time) {
        this.title = title;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return title + " on " + date + " at " + time;
    }
}