package Management;

import FOH.JDBC;
import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Abstract class representing booking information sent to management
public abstract class BookingstoManagement {
     private int bookingID; // unique identifier
     private String name; // the name of the person the booking is under
     private String phoneNumber; // the telephone number provided when making a booking 
     private String type; // the type of booking (phone, walkin, online, ..., etc)
     private int noOfCovers; // the number of people the booking is for
     private Connection connection;

     /**
     * Constructor to initialise the BookingsToManagement
     * @param bookingID
     */
    public BookingstoManagement(int bookingID) {
        this.bookingID = bookingID;
        this.connection = connection;
    }

    /**
     * Getter method that returns bookingID
     * @return bookingID
     */
    public int getBookingID() {
        return bookingID;}

    /**
     * Getter method that returns name
     * @return name
     */
    public String getName() {
        String name = null;
        String query = "SELECT name FROM bookings WHERE bookingID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1,this.bookingID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name; }
    
    /**
     * Getter method that returns number
     * @return number
     */   
    public String getPhoneNumber() {
        String phoneNumber = null;
        String query = "SELECT phone_number FROM bookings WHERE booking_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, this.bookingID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                phoneNumber = rs.getString("phone_number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phoneNumber;
    }

    public String getType() {
        String type = null;
        String query = "SELECT type FROM bookings WHERE booking_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, this.bookingID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                type = rs.getString("type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }

    public int getNoOfCovers() {
        int noOfCovers = 0;
        String query = "SELECT no_of_covers FROM bookings WHERE booking_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, this.bookingID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                noOfCovers = rs.getInt("no_of_covers");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noOfCovers;
    }
}