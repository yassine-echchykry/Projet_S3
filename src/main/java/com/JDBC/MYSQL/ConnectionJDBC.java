package com.JDBC.MYSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

public class ConnectionJDBC {

	public ConnectionJDBC() {
		// TODO Auto-generated constructor stub
	}

	/* La liste qui contiendra tous les résultats des tests  */
	/*hhhhhhhhhhhhhhhhhhhhhhhhh*/
		    private List<String> notifications = new ArrayList<String>();

		    public List<String> executerTests( HttpServletRequest request ) {
		    	 /* Chargement du driver JDBC pour MySQL */
		        try {
		        	notifications.add( "Chargement du driver..." );
		            Class.forName( "com.mysql.jdbc.Driver" );
		            notifications.add( "Driver OK !" );
		        } catch ( ClassNotFoundException e ) {
		        	notifications.add( "Erreur lors du chargement : le driver n'a pas été ajouté au classpath ! <br/>"
		                    + e.getMessage() );
		        }

		        /* Connexion à la base de données */
		        String url = "jdbc:mysql://localhost:3306/picalti";
		        String utilisateur = "root";
		        String motDePasse = "";
		        Connection connexion = null;
		        Statement statement = null;
		        ResultSet resultat = null;
		        try {
		        	notifications.add( "Connexion à la base de données..." );
		            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		            notifications.add( "Connexion OK !" );
		           	           	           
		            /* create statement Object */
		            statement = connexion.createStatement();
		            notifications.add( "Objet requête créé avec succès !" );

		            /* Lancement d'une requête d'écriture */
		           int statut = statement.executeUpdate( "INSERT INTO person (id,nom,prenom,email,age) VALUES ('1','Ech-chykry','Yassine','Yassine.ech@mail.com','21');");

		            /* Formatage pour affichage dans la JSP finale. */
		           notifications.add( "Résultat de la requête d'insertion : " + statut + "." );
		            
		            /* Exécution d'une requête Executequery()==> de lecture */
		            resultat = statement.executeQuery( "SELECT id, nom,prenom,email, age FROM person;" );
		            notifications.add( "Requête \"SELECT id, nom,prenom,email, age FROM person;\" effectuée !" );
		                                                               
		            /* Récupération et traitement des données résultat de la requête */
		            while ( resultat.next() ) {
		                int idPerson = resultat.getInt( "id" );
		                String nomPerson = resultat.getString( "nom" );
		                String prenomPerson = resultat.getString( "prenom" );
		                String emailPerson = resultat.getString( "email" );
		                int agePerson=resultat.getInt("age");

		                /* Formatage des données pour affichage dans la JSP finale. */
		                notifications.add( "Données retournées par la requête : id = " + idPerson +", nom = " + nomPerson +", prenom = " + prenomPerson + ", email = " + emailPerson
		                        + ", age = "
		                        + agePerson +  "." );
		            }
		        } catch ( SQLException e ) {
		            notifications.add( "Erreur lors de la connexion : <br/>"
		                    + e.getMessage() );
		        } finally {
		            notifications.add( "Closing ResultSet Object." );
		            if ( resultat != null ) {
		                try {
		                    resultat.close();
		                } catch ( SQLException ignore ) {
		                }
		            }
		            notifications.add( "Closing  Statement Object." );
		            if ( statement != null ) {
		                try {
		                    statement.close();
		                } catch ( SQLException ignore ) {
		                }
		            }
		            notifications.add( "Closing connexion Object." );
		            if ( connexion != null ) {
		                try {
		                    connexion.close();
		                } catch ( SQLException ignore ) {
		                }
		            }
		        }

		        return notifications;
		    }
}
