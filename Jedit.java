// Jedit.java
// Jedit est un éditeur de texte très minimaliste en mode ligne
// Fait par Melissa Boucher et Francis Thibodeau 
// 13 Fevrier 2015
import java.io.*;
import javax.swing.JOptionPane;
public class Jedit
{
	// Déclaration des variables constantes
	private static final int MIN_PARAM = 0;
	private static final int MAX_PARAM = 2; 
	private static final char OUI = 'O';
	private static final char NON = 'N';
	
	//////////////////////////////////////////////////////////////////////////////////
	// Vérification du nombre de parametre entrer par l'utilisateur.
	// Il doit y avoir 1 ou 2 parametre.
	// L'utilisateur peut entrer une seule option : -n qui numerote les lignes.
	// Si la commande de l'utilisateur est valide, et que l'option est innexistante ou valide, le fichier est créer.
	// Si une valeure est invalide, le fichier n'es pas créé.
	// La fonction retourne un bool True si tout est valide.
	//////////////////////////////////////////////////////////////////////////////////
	public static boolean verifiernbreparametre(String args[])
	{
		boolean paramValide = true; 
		// si on a entre 1 et 2 parametre 
		if (args.length == MIN_PARAM || args.length > MAX_PARAM)
		{
			paramValide = false;
			JOptionPane.showMessageDialog( null,
										   "Il doit y avoir minimum 1 parametre et maximum 2.\nFichier non cree.",
										   "Erreur", JOptionPane.WARNING_MESSAGE );
		}
		// verification du 1er parametre s'il y en a 2. 
		else if (!args[MIN_PARAM].equals("-n") && args.length == MAX_PARAM)
		{
			paramValide = false;	
			JOptionPane.showMessageDialog( null,
										   "Vous avez entre une option invalide.\nFichier non cree.",
										   "Erreur", JOptionPane.WARNING_MESSAGE );
		}
		return paramValide;
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	// Cette fonction informe qu'un fichier du meme nom existe et traite la requete de l'utilisateur.
	// La fonction prend la reponse de l'utilisateur, la met en majuscule et traite l'entrée.
	// Si l'entrée est O, le fichier existant sera modifié.
	// Si l'entrée est N, le fichier existant ne sera pas modifié.
	// Si l'entrée est autre chose que O/N, l'entrée est invalide.
	//////////////////////////////////////////////////////////////////////////////////
	public void fichierexiste(String nomFichier, String args[])
	{
		char resultat;
		
		System.out.println("Un fichier portant le nom " + nomFichier + " existe deja.");
		System.out.println("Voulez vous le remplacer ? O(oui) ou N(non)");
		
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			resultat = (char)reader.read();
			
			// si l'utilisateur veux ecraser le fichier
			if(Character.toUpperCase(resultat) == OUI)
			{
				System.out.println("Le fichier sera modifie.");
				ecriredansfichier(args, nomFichier);
			}
			// si l'utilisateur ne veux pas l'ecraser
			else if(Character.toUpperCase(resultat) == NON)
			{
				System.out.println("Le fichier ne sera pas modifie.");
			}
			// si l'utilisateur entre autre chose
			else
			{
				System.out.println("Choix invalide.");
			}
		}
		catch(IOException e)
		{
			System.err.println(e);
			System.exit(1);
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	// Cette fonction permet a l'utilisateur d'insérer ce qu'il a écrit en console dans une fichier text.
	// Si les données founies par l'utilisateur sont valides et qu'il demande la numération des lignes(-n) les lignes sont numérotées.
	// Cette fonction retire aussi les espaces en trop au début et à la fin du string.
	//////////////////////////////////////////////////////////////////////////////////
	public void ecriredansfichier(String args[], String nomFichier)
	{
		boolean ecrireNumero = true; 
		if(args.length == 1)
			ecrireNumero = false;
		
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter writer = new PrintWriter( new BufferedWriter( new FileWriter(nomFichier)));
			
			int numLigne = 1;
			String ligne = reader.readLine();
			
			while(!ligne.trim().isEmpty())
			{
				if(numLigne != 1)
					writer.print("\n");
				if(ecrireNumero) // si l'utilisateur veux des numero de ligne
					writer.print(numLigne + "- "); // on écrit le numero
				writer.print(ligne); // on écrit la ligne
				
				numLigne ++; //incrementation
				ligne = reader.readLine(); // on change de ligne
			}
			writer.close();
		}
		catch(IOException e)
		{
			System.err.println(e);
			System.exit(1);
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	// Cette fonction vérifie si un fichier du meme nom que celui fournit par l'utilisateur existe.
	// Si le fichier existe la fonction appelle fichierexiste
	// Sinon, la fonction appelle ecriredansfichier
	//////////////////////////////////////////////////////////////////////////////////
	public void editer(String args[])
	{
		String nomDuFichier = args[0];
        File fichierUtilise;
		
		// si l'utilisateur a entré une option, le nom du fichier devrait etre en 2e position
        if (args.length > 1) 
			nomDuFichier = args[1]; 
			
		fichierUtilise = new File(nomDuFichier);
		// methode qui verifie si le fichier existe
		if(fichierUtilise.exists())
			fichierexiste(nomDuFichier, args);
		else
			ecriredansfichier(args, nomDuFichier);
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	// Main
	//////////////////////////////////////////////////////////////////////////////////
	public static void main( String args[] )
	{
		Jedit app = new Jedit();
		
		if(verifiernbreparametre(args))
			app.editer( args );
	}
}