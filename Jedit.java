// Jedit.java
// Jedit est un éditeur de texte très minimaliste en mode ligne
// Fait par Melissa Boucher et Francis Thibodeau 
// 13 Fevrier 2015
import java.io.*;
public class Jedit
{
	private static final int MIN_PARAM = 0;
	private static final int MAX_PARAM = 2; 
	private static final char OUI = 'O';
	private static final char NON = 'N';
	
	public static boolean verifiernbreparametre(String args[])
	{
		boolean paramValide = true; 

		if (args.length == MIN_PARAM || args.length > MAX_PARAM)
		{
			paramValide = false;
			System.out.println("Il doit y avoir minimum 1 parametre et maximum 2 parametres, fichier non cree.");
		}
		else if (!args[MIN_PARAM].equals("-n") && args.length == MAX_PARAM)
		{
			System.out.println("Vous avez entre une option invalide, fichier non cree.");
			paramValide = false;	
		}
		return paramValide;
	}
	
	public void fichierexiste(String nomFichier, String args[])
	{
		char resultat;
		
		System.out.println("Un fichier portant le nom " + nomFichier + " existe deja.");
		System.out.println("Voulez vous le remplacer ? O(oui) ou N(non)");
		
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			resultat = (char)reader.read();
			
			if(Character.toUpperCase(resultat) == OUI)
			{
				System.out.println("Le fichier sera modifie.");
				ecriredansfichier(args, nomFichier);
			}
			else if(Character.toUpperCase(resultat) == NON)
			{
				System.out.println("Le fichier ne sera pas modifie.");
			}
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
				if(ecrireNumero)
					writer.print(numLigne + "- ");
				writer.print(ligne + "\n");
				
				numLigne ++; 
				ligne = reader.readLine();
			}
			writer.close();
		}
		catch(IOException e)
		{
			System.err.println(e);
			System.exit(1);
		}
		
		
	}

	public void editer(String args[])
	{
		String nomDuFichier = args[0];
        File fichierUtilise;

        if (args.length > 1) // si l'utilisateur a entré une option, le nom du fichier devrait etre en 2e position
			nomDuFichier = args[1]; 
			
		fichierUtilise = new File(nomDuFichier);
		if(fichierUtilise.exists())
			fichierexiste(nomDuFichier, args);
		else
			ecriredansfichier(args, nomDuFichier);
	}

	public static void main( String args[] )
	{
		Jedit app = new Jedit();
		
		if(verifiernbreparametre(args))
			app.editer( args );
	}
}