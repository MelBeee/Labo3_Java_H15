// Jedit.java
// Jedit est un éditeur de texte très minimaliste en mode ligne
// Fait par Melissa Boucher et Francis Thibodeau 
// 13 Fevrier 2015

public class Jedit
{
	private final int MIN_PARAM = 0;
	private final int MAX_PARAM = 2; 
	private final char OUI = 'O';
	private final char NON = 'N';
	
	public boolean verifiernbreparametre(String args[])
	{
		boolean paramValide = true; 

		if (args.length == MIN_PARAM || args.length > MAX_PARAM)
		{
			paramValide = false;
			System.out.println("Il doit y avoir minimum 1 parametre et maximum 2 parametres, fichier non cree.");
		}
		else if (args[MIN_PARAM] != "-n" && args.length == MAX_PARAM)
		{
			System.out.println("Vous avez entre une option invalide, fichier non cree.");
			paramValide = false;	
		}
		return paramValide;
	}
	
	public boolean fichierexiste(String nomFichier)
	{
		boolean continuer = true; 
		char resultat;
		
		System.out.println("Un fichier portant le nom " + nomFichier + " existe deja.");
		System.out.println("Voulez vous le remplacer ? O(oui) ou N(non)");
		
		try
		{
			do
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				resultat = reader.read();
				
				if(Character.toUpperCase(resultat).equals(OUI))
				{
					System.out.println("Le fichier sera modifie.")
				}
				else if(Character.toUpperCase(resultat).equals(NON))
				{
					System.out.println("Le fichier ne sera pas modifie.")
					continuer = false; 
				}
				else
				{
					System.out.println("Choix invalide, recommencez.")
				}
				
			}while(Character.toUpperCase(resultat).equals(OUI) || 
				   Character.toUpperCase(resultat).equals(NON));
			
			
		}
		catch(IOException e)
		{
			System.err.println(e);
			System.exit(1);
		}
		
		return continuer; 
	}

	public void ecriredansfichier(String args[], String nomFichier)
	{
		boolean ecrireNumero = true; 
		if(args.length == 1)
			ecrireNumero = false;
		
		try
		{
		
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
		boolean continuer = true;

        if (args.length > 1) // si l'utilisateur a entré une option, le nom du fichier devrait etre en 2e position
			nomFichier = args[1]; 
			
		fichierUtilise = new File(nomDuFichier);
		if(fichierUtilise.exists())
			continuer = fichierexiste(nomDuFichier));
		else if(!fichierUtilise.exists() || continuer)
			ecriredansfichier(args, nomDuFichier);
	}

	public static void main( String args[] )
	{
		Jedit app = new Jedit();
		
		if(verifiernbreparametre(args[])
			app.editer( args );
	}
}