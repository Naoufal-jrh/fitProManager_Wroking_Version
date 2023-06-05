package Module;

import Controller.*;
import Controller.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Module {

    static public Connection connectToDb(){
        String jdbcURL = "jdbc:mysql://localhost:3306/gymdatabase";
        String username = "root";
        String password = "";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public void closeConnection(Connection conn , Statement stmt , ResultSet rs ){
        // Close the connection
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static public void closeConnection(Connection conn , Statement stmt){
        // Close the connection
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //________________________________________________________________________________________________________________

    // Membres :
    public static List<Membre> getMembres(String query){
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {

            // Establish the connection
            conn = Module.connectToDb();

            // Create a statement object
            stmt = conn.createStatement();

            // Execute a query to retrieve all books
            rs = stmt.executeQuery(query);

            // Create an array to store the books
            List<Membre> membres = new ArrayList<Membre>();

            // Process the result set and populate the array
            while (rs.next()) {
                int id = rs.getInt("idMembre");
                String nom = rs.getString("nom");
                String cin = rs.getString("cin");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                String adresse = rs.getString("adresse");
                String tel = rs.getString("tel");
                String sexe = rs.getString("sexe");
                int jourNaissance = rs.getInt("jourNaissance");
                int moisNaissance = rs.getInt("moisNaissance");
                int anneeNaissance = rs.getInt("anneeNaissance");
                int jourAdherence = rs.getInt("jourAdherence");
                int moisAdherence = rs.getInt("moisAdherence");
                int anneeAdherence = rs.getInt("anneeAdherence");
                Date dateNaissance = new Date(jourNaissance, moisNaissance, anneeNaissance);
                Date dateAdherence = new Date(jourAdherence, moisAdherence, anneeAdherence);

                Membre membre = new Membre( id ,nom, cin,prenom, email, adresse, tel, sexe, dateNaissance, dateAdherence);
                membres.add(membre);
            }
            return membres;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            // Close all resources
            closeConnection(conn, stmt, rs);
        }
        return new ArrayList<>();
    }

    public static void ajouterMembre(Membre membre) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = Module.connectToDb();

            // Prepare the SQL statement with parameters
            String sql = "INSERT INTO membre (nom, prenom, email, adresse, tel, sexe" +
                    ", jourNaissance, moisNaissance, anneeNaissance, jourAdherence, moisAdherence, anneeAdherence , cin)" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            // Set the parameter values for the book

            stmt.setString(1, membre.getNom());
            stmt.setString(2, membre.getPrenom());
            stmt.setString(3, membre.getEmail());
            stmt.setString(4, membre.getAdresse());
            stmt.setString(5, membre.getTel());
            stmt.setString(6, membre.getSexe());
            stmt.setInt(7, membre.getDateNaissance().getJour());
            stmt.setInt(8, membre.getDateNaissance().getMois());
            stmt.setInt(9, membre.getDateNaissance().getAnnée());
            stmt.setInt(10, membre.getDateAdherence().getJour());
            stmt.setInt(11, membre.getDateAdherence().getMois());
            stmt.setInt(12, membre.getDateAdherence().getAnnée());
            stmt.setString(13, membre.getCin());


            // Execute the INSERT statement
            stmt.executeUpdate();


        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                throw new SQLException("membre est déjà enregistré");

            } else {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e){
        }finally {
            // Close all resources
            closeConnection(conn, stmt);

        }
    }

    public static void modifierMembre(int id , Membre newmembre){

        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            // Establish the connection
            conn = Module.connectToDb();

            // Prepare the SQL statement with parameters
            String sql = "UPDATE membre SET nom = ?, prenom = ?, email = ?, adresse = ?, tel = ?, sexe = ?, " +
                    "jourNaissance = ?, moisNaissance = ?, anneeNaissance = ?, jourAdherence = ?, moisAdherence = ?, " +
                    "anneeAdherence = ? , cin = ?  WHERE idMembre = ?";
            stmt = conn.prepareStatement(sql);

            // Set the parameter values for the member
            stmt.setString(1, newmembre.getNom());
            stmt.setString(2, newmembre.getPrenom());
            stmt.setString(3, newmembre.getEmail());
            stmt.setString(4, newmembre.getAdresse());
            stmt.setString(5, newmembre.getTel());
            stmt.setString(6, newmembre.getSexe());
            stmt.setInt(7, newmembre.getDateNaissance().getJour());
            stmt.setInt(8, newmembre.getDateNaissance().getMois());
            stmt.setInt(9, newmembre.getDateNaissance().getAnnée());
            stmt.setInt(10, newmembre.getDateAdherence().getJour());
            stmt.setInt(11, newmembre.getDateAdherence().getMois());
            stmt.setInt(12, newmembre.getDateAdherence().getAnnée());
            stmt.setString(13, newmembre.getCin());
            stmt.setInt(14, id);


            // Execute the UPDATE statement
            stmt.executeUpdate();


        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            // Close all resources
            closeConnection(conn, stmt);
        }
    }

    public static void supprimerMembre(int id){

        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;

        try {

            // Establish the connection
            conn = Module.connectToDb();

            // Prepare the SQL statement with parameter
            String sql = "DELETE FROM membre WHERE idMembre = ?";
            String sql2 = "DELETE FROM inscription WHERE idmembre = ?";
            String sql3 = "DELETE FROM expiration WHERE idmembre = ?";
            stmt = conn.prepareStatement(sql);
            stmt2 = conn.prepareStatement(sql2);
            stmt3 = conn.prepareStatement(sql3);


            // Set the parameter value for the member's ID
            stmt.setInt(1, id);
            stmt2.setInt(1, id);
            stmt3.setInt(1, id);


            // Execute the DELETE statement
            stmt2.executeUpdate();
            stmt3.executeUpdate();
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            // Close all resources
            closeConnection(conn, stmt);

        }
    }

    // Inscriptions :
    public static List<Inscription> getInscriptions(String query){
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {

            // Establish the connection
            conn = Module.connectToDb();

            // Create a statement object
            stmt = conn.createStatement();


            // Execute a query to retrieve all books
            rs = stmt.executeQuery(query);

            // Create an array to store the books
            List<Inscription> inscriptions = new ArrayList<Inscription>();

            // Process the result set and populate the array
            while (rs.next()) {
                int idInscription = rs.getInt("idInscription");

                //Get offre
                int idOffre = rs.getInt("idOffre");
                Offre offre = getOffres("SELECT * FROM offre where idOffre =" + idOffre).get(0);

                //Get Membre
                int idMembre = rs.getInt("idMembre");
                Membre membre = getMembres("SELECT * FROM membre where idMembre=" + idMembre).get(0);

                //Get DateInscription
                int jourInsciption = rs.getInt("jourInscription");
                int moisInsciption = rs.getInt("moisInscription");
                int anneeInsciption = rs.getInt("anneeInscription");
                Date dateInscription = new Date(jourInsciption, moisInsciption, anneeInsciption);

                //Create inscription and add it :
                Inscription inscription = new Inscription(idInscription, offre, membre, dateInscription);
                inscriptions.add(inscription);

            }
            return inscriptions;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            // Close all resources
            closeConnection(conn, stmt, rs);
        }
        return new ArrayList<>();
    }

    public static void ajouterInsciption(int idMembre , int idOffre){

        Connection conn = null;
        PreparedStatement stmt = null;
        try {


            // Establish the connection
            conn = Module.connectToDb();
            // Prepare the SQL statement with parameters
            String sql = "INSERT INTO inscription ( jourInscription, moisInscription, anneeInscription," +
                    "idMembre , idOffre)" +
                    " VALUES (?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            //get membre
            Membre membre = getMembres("SELECT * FROM membre WHERE idMembre = "+idMembre).get(0);

            //get offre
            Offre offre = getOffres("SELECT * FROM offre WHERE idOffre = "+idOffre).get(0);

            //set Date
            LocalDate newDate = LocalDate.now(); // add duree offre to current date
            int jour = newDate.getDayOfMonth();
            int mois = newDate.getMonthValue();
            int annee = newDate.getYear();
            Date dateinscription = new Date(jour,mois,annee);

            //set inscription
            Inscription inscription = new Inscription(offre,membre,dateinscription);

            // Set the parameter values for the book

            stmt.setInt(1, inscription.getDateInscription().getJour());
            stmt.setInt(2, inscription.getDateInscription().getMois());
            stmt.setInt(3, inscription.getDateInscription().getAnnée());
            stmt.setInt(4, inscription.getMembre().getIdPersonne());
            stmt.setInt(5, inscription.getOffre().getIdOffre());

            // Execute the INSERT statement
            stmt.executeUpdate();
            //modifierExpiration(idMembre,idOffre);



        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            // Close all resources
            closeConnection(conn, stmt);
        }

    }
    //Offre :
    public static List<Offre> getOffres(String query) {

        Connection conn = null;
        Statement stmt = null;

        ResultSet rs = null;

        try {

            conn = Module.connectToDb();
            // Create a statement object
            stmt = conn.createStatement();

            // Execute a query to retrieve all books
            rs = stmt.executeQuery(query);

            // Create an array to store the books
            List<Offre> offres = new ArrayList<Offre>();

            // Process the result set and populate the array
            while (rs.next()) {

                int idOffre = rs.getInt("idOffre");
                String nomOffre = rs.getString("nomOffre");
                String prixOffre = rs.getString("prixOffre");
                int dureeOffre = rs.getInt("dureeOffre");
                int idCategorie = rs.getInt("idCategorie");
                String query2 = "SELECT * FROM categorie where idCategorie=" + idCategorie;
                Categorie categorie = Module.getCategorie(query2).get(0);

                System.out.println("id offre:"+idOffre);

                Offre offre = new Offre(idOffre, nomOffre, prixOffre, dureeOffre, categorie);
                offres.add(offre);

            }
            return offres;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            // Close all resources
            closeConnection(conn, stmt, rs);
        }
        return new ArrayList<>();


    }

    public static void ajouterOffre(Offre offre) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = Module.connectToDb();
            // Prepare the SQL statement with parameters
            String sql = "INSERT INTO offre (nomOffre, prixOffre, dureeOffre, idCategorie)" +
                    " VALUES (?,?,?,?)";
            stmt = conn.prepareStatement(sql);

            // Set the parameter values for the offre
            stmt.setString(1, offre.getNomOffre());
            stmt.setString(2, offre.getPrixOffre());
            stmt.setInt(3, offre.getDureeOffre());
            stmt.setInt(4, offre.getCategorie().getIdCategorie());

            // Execute the INSERT statement
            stmt.executeUpdate();

            // Close the statement
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            // Close all resources
            closeConnection(conn, stmt);
        }

    }

    public static void supprimerOffre(int id) {

        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;

        // Close all resources
        try {

            // Establish the connection
            conn = Module.connectToDb();

            // Prepare the SQL statement with parameter
            String sql = "DELETE FROM offre WHERE idOffre = ?";
            String sql2 = "DELETE FROM inscription WHERE idOffre = ?";


            stmt = conn.prepareStatement(sql);
            stmt2 = conn.prepareStatement(sql2);

            // Set the parameter value for the member's ID
            stmt.setInt(1, id);
            stmt2.setInt(1, id);

            // Execute the DELETE statement
            stmt2.executeUpdate();
            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            // Close all resources
            closeConnection(conn, stmt);
        }

    }

    public static void modifierOffre(int id, Offre newOffre) {

        Connection conn = null;
        PreparedStatement stmt = null;

        // Close all resources
        try {


            conn = Module.connectToDb();

            // Prepare the SQL statement with parameters
            String sql = "UPDATE offre SET nomOffre = ?, prixOffre = ?, dureeOffre = ?, idCategorie = ? WHERE idOffre = ?;";
            stmt = conn.prepareStatement(sql);

            // Set the parameter values for the member
            stmt.setString(1, newOffre.getNomOffre());
            stmt.setString(2, newOffre.getPrixOffre());
            stmt.setInt(3, newOffre.getDureeOffre());
            stmt.setInt(4, newOffre.getCategorie().getIdCategorie());
            stmt.setInt(5, id);

            // Execute the UPDATE statement
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            // Close all resources
            closeConnection(conn, stmt);
        }

    }

    //Categorie :
    public static void ajouterCategorie(Categorie categorie) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = Module.connectToDb();

            // Prepare the SQL statement with parameters
            String sql = "INSERT INTO categorie (nomCategorie)" +
                    " VALUES (?)";
            stmt = conn.prepareStatement(sql);

            // Set the parameter values for the offre
            stmt.setString(1, categorie.getNomCategorie());

            // Execute the INSERT statement
            stmt.executeUpdate();

            // Close the statement
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            // Close all resources
            closeConnection(conn, stmt);
        }

    }

    public static List<Categorie> getCategorie(String query) {

        Connection conn = null;
        Statement stmt = null;

        ResultSet rs = null;

        try {

            conn = Module.connectToDb();

            // Create a statement object
            stmt = conn.createStatement();

            // Execute a query to retrieve all books
            rs = stmt.executeQuery(query);

            // Create an array to store the books
            List<Categorie> categories = new ArrayList<Categorie>();

            // Process the result set and populate the array
            while (rs.next()) {

                int idCategorie = rs.getInt("idCategorie");
                String nomCategorie = rs.getString("nomCategorie");
                int nbrMembres=rs.getInt("nbrMembres");
                Categorie categorie = new Categorie(idCategorie, nomCategorie,nbrMembres);

                categories.add(categorie);
            }
            return categories;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            // Close all resources
            closeConnection(conn, stmt, rs);
        }

        return null;

    }


    //this method modifies member count per categ , to be called when client signs up or renews
    public static void modifierNbrInscrits(int id) {

        Connection conn = null;
        PreparedStatement stmt = null;

        // Close all resources
        try {
            conn = Module.connectToDb();

            // Prepare the SQL statement with parameters
            String sql = "UPDATE categorie SET nbrMembres = ? WHERE idCategorie = ?";
            stmt = conn.prepareStatement(sql);

            // Set the parameter values for the member
            stmt.setInt(1,getNbrMembreCategorie(id));
            stmt.setInt(2, id);

            System.out.println(getNbrMembreCategorie(id));


            // Execute the UPDATE statement
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            // Close all resources
            closeConnection(conn, stmt);
        }

    }

    public static void supprimerCategorie(int id) {

        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;

        // Close all resources
        try {

            // Establish the connection
            conn = Module.connectToDb();

            // Prepare the SQL statement with parameter
            String sql = "DELETE FROM categorie WHERE idCategorie = ?";
            String sql2 = "DELETE FROM offre WHERE idCategorie = ?";

            stmt = conn.prepareStatement(sql);
            stmt2 = conn.prepareStatement(sql2);

            // Set the parameter value for the member's ID
            stmt.setInt(1, id);
            stmt2.setInt(1, id);


            // Execute the DELETE statement
            stmt2.executeUpdate();
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            // Close all resources
            closeConnection(conn, stmt);

        }

    }

    //this function hashs the password that will be stored in the data base
    public static String hashPassword(String password) {
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Add password bytes to digest
            md.update(password.getBytes());

            // Get the hashed password bytes
            byte[] hashedBytes = md.digest();

            // Convert the hashed bytes to a hexadecimal representation
            StringBuilder sb = new StringBuilder();
            for (byte hashedByte : hashedBytes) {
                sb.append(Integer.toString((hashedByte & 0xff) + 0x100, 16).substring(1));
            }

            // Return the hashed password as a string
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    //this function check if a hash is identical to a string it will be used to check if the password entered in the login is in the database
    public static boolean isPasswordMatch(String password, String hashedPassword) {
        String hashedInput = hashPassword(password);
        return hashedInput.equals(hashedPassword);
    }

    //Expiration
    public static List<Expiration> getExpiration(String query) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = Module.connectToDb();
            // Create a statement object
            stmt = conn.createStatement();

            // Execute a query to retrieve all books
            rs = stmt.executeQuery(query);

            // Create an array to store the books
            List<Expiration> expirations = new ArrayList<Expiration>();

            // Process the result set and populate the array
            while (rs.next()) {
                int idExpiration = rs.getInt("idExpiration");
                int jourExpiration = rs.getInt("jourExpiration");
                int moisExpiration = rs.getInt("moisExpiration");
                int anneeExpiration = rs.getInt("anneeExpiration");
                int idMembre = rs.getInt("idMembre");
                int idCategorie = rs.getInt("idCategorie");

                List<Membre> membre = getMembres("SELECT * FROM membre WHERE idMembre=" + idMembre);
                List<Categorie> categorie = getCategorie("SELECT * FROM categorie WHERE idCategorie=" + idCategorie);

                Date DateExpiration = new Date(jourExpiration, moisExpiration, anneeExpiration);

                Expiration expiration = new Expiration(idExpiration, membre.get(0), categorie.get(0), DateExpiration);
                expirations.add(expiration);
            }
            return expirations;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close all resources
            closeConnection(conn, stmt, rs);
        }
        return null;

    }

    public static void ajouterExpiration(int idMembre,int idOffre) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = Module.connectToDb();
            // Prepare the SQL statement with parameters
            String sql = "INSERT INTO expiration (jourExpiration, moisExpiration, anneeExpiration, " +
                    "idMembre , idCategorie)" +
                    " VALUES (?,?,?,?,?)";

            int idCategorie = getOffres("SELECT * FROM offre WHERE idOffre=" +idOffre).get(0).getCategorie().getIdCategorie();
            int duree = getOffres("SELECT * FROM offre WHERE idOffre=" +idOffre).get(0).getDureeOffre();


            LocalDate newDate = LocalDate.now().plusMonths(duree); // add duree offre to current date

            int jourExpiration = newDate.getDayOfMonth();
            int moisExpiration = newDate.getMonthValue();
            int anneeExpiration = newDate.getYear();

            stmt = conn.prepareStatement(sql);
            // Set the parameter values for the offre
            stmt.setInt(1, jourExpiration);
            stmt.setInt(2, moisExpiration);
            stmt.setInt(3, anneeExpiration);
            stmt.setInt(4, idMembre);
            stmt.setInt(5, idCategorie);

            // Execute the INSERT statement
            stmt.executeUpdate();
            modifierNbrInscrits(idCategorie);
            ajouterInsciption(idMembre,idOffre);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            // Close all resources
            closeConnection(conn, stmt);
        }

    }
    public static void modifierExpiration(int idMembre , int idOffre){

        Connection conn = null;
        PreparedStatement stmt = null;

        // Close all resources
        try {
            conn = Module.connectToDb();

            // Prepare the SQL statement with parameters
            String sql = "UPDATE expiration SET jourExpiration= ?, moisExpiration = ? , anneeExpiration = ? " +
                    "WHERE idExpiration = ?";
            stmt = conn.prepareStatement(sql);

            //get dureeOffre by id :
            Offre offer = getOffres("SELECT * FROM offre WHERE idOffre = "+idOffre).get(0);

            //set offer and categ ids
            int dureeOffre = offer.getDureeOffre();
            int idCategorie=offer.getCategorie().getIdCategorie();

            //get idExpiration
            Expiration expiration= getExpiration("SELECT * FROM expiration WHERE idCategorie = "+idCategorie+
                    " AND idMembre = "+idMembre).get(0);
            int idExpiration = expiration.getIdExpiration();


            int  oldjour= expiration.getDateExpiration().getJour();
            int oldMois=expiration.getDateExpiration().getMois();
            int oldAnnee=expiration.getDateExpiration().getAnnée();
            System.out.println("month:"+oldAnnee);


            LocalDate newDate= LocalDate.of(oldAnnee,oldMois,oldjour).plusMonths(dureeOffre);
            System.out.println("newdate:"+newDate);

            int newJour=newDate.getDayOfMonth();
            int newMois=newDate.getMonthValue();
            int newYear=newDate.getYear();


            // Set the parameter values for the member
            Date dateExpiration =getExpiration("SELECT * FROM expiration WHERE idExpiration = " + idExpiration).get(0).getDateExpiration();



            stmt.setInt(1, newJour);
            stmt.setInt(2,newMois);
            stmt.setInt(3, newYear);
            stmt.setInt(4, idExpiration);



            // Execute the UPDATE statement
            stmt.executeUpdate();
            //modifierNbrInscrits(idCategorie);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            // Close all resources
            closeConnection(conn, stmt);
        }

    }
    public static void supprimerExpiration(int idMembre, int idCategorie){
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;

        // Close all resources
        try {

            // Establish the connection
            conn = Module.connectToDb();

            // Prepare the SQL statement with parameter
            String sql = "DELETE FROM expiration WHERE idMembre = ? AND idCategorie = ?";

            stmt = conn.prepareStatement(sql);

            // Set the parameter value for the member's ID
            stmt.setInt(1, idMembre);
            stmt.setInt(2, idCategorie);

            // Execute the DELETE statement
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            // Close all resources
            closeConnection(conn, stmt);

        }

    }


    //this method retrieves members for a given categ
    public static int getNbrMembreCategorie(int idCategorie) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int nb = 0;

        try {
            conn = Module.connectToDb();

            String query = "SELECT COUNT(idExpiration) AS nbrinscrits FROM expiration WHERE idCategorie =" + idCategorie;

            stmt = conn.createStatement();

            // Execute a query to retrieve all books
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                 nb = rs.getInt("nbrinscrits");
                System.out.println("nb"+nb);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            // Close all resources
            closeConnection(conn, stmt, rs);
            return nb;
        }
    }



    //this method shows a custom alert type with a custom message then  redirects the user
    public static void showCustomAlert(Alert.AlertType alertType, String message , String path,ActionEvent event){
        //create custom alert
        Alert alert = new Alert(alertType);
        //set alert text
        alert.setContentText(message);
        // create ok button
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        //add ok button to alert
        alert.getButtonTypes().setAll(okButton);

        //show alert and wait for user confirmation
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == okButton) {
                Navigation nv = new Navigation();
                try {
                  nv.goTo(event,path);
                }  catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

}










