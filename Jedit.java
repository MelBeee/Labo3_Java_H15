// Jedit.java
// Jedit est un éditeur de texte très minimaliste en mode ligne
// Fait par Melissa Boucher et Francis Thibodeau 
// 13 Fevrier 2015

public class Jedit
{
   public void verifieroption(String args[])
   {
      Boolean optionNumero = false; 
       String option = "" ;
         String nomFichier = "";
         String contenu = "";
         
         if(args.length == 1)
         {
            nomFichier = args[0];
            for(int i = 1; i < args.length ; i ++)
            {
               contenu += args[i];
            }
         }
         else
         {
            optionNumero = true; 
            option = args[0];
            nomFichier = args[1];
            for(int i = 2; i < args.length ; i ++)
            {
               contenu += args[i];
            }
         }
         ecriredansfichier(nomFichier, contenu ,optionNumero);
   }
   
   public void ecriredansfichier(String nomFichier, String contenu, Boolean numerote)
   {
      System.out.println(contenu);
   }
   
   public void editer(String args[])
   {
      if(args.length > 0)
		{
        verifieroption(args);
		}
		else
		{
         // Si l'utilisateur a entré zero ou plus de deux parametre
			System.out.println( "Entrez minimalement un parametre et maximalement 2 parametres.");
		}
   }
   
   public static void main( String args[] )
   {
      Jedit app = new Jedit();
      app.editer( args );
   }
}